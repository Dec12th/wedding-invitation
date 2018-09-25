package com.baily.template.common.config.db.multidatasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;


/**
 * @ClassName: MultiDataSourceAspect
 * @Description:
 * @author:YB
 * @date:2018年01月03日 13:59
 */
@Configuration
@Aspect
@Order(-1)// 调节优先级，保证该AOP在@Transactional之前执行
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MultiDataSourceAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String POINT_PACKAGE = "execution(* com.lakala.finance.*.service.*.impl.*.*(..))";

    /**
     * 功能：设置数据源:不拦截private方法
     * 1.DataSource注解优先级最高。manual为true时，需要方法的第一个参数为数据源的key。默认为false。
     * value为注解时设置的数据源key，对方法的参数没有要求。
     * 2.Transactional的Spring事务注解为次优先级。readOnly为true时为从机模式，否则为主机模式
     * 3.上述情况都不满足的情况则不分配数据源（即当前线程不分配数据源资源），如果在执行方法时用到数据源则选择默认的数据源。
     * 此种请款是考虑到被拦截方法可能只是业务处理，不需要与数据库交互，避免线程较多。
     *
     * @param point 方法切点
     */
    @Before(POINT_PACKAGE)
    public void before(JoinPoint point) {
//		if(DynamicDataSourceHolder.getDataSouce() != null) {
//			return;	//当前线程持有数据源时，不切换数据源
//		}
        // 方法名
        String methodName = point.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);
            logger.debug("------" + method.getName());
            // 指定某个数据源
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = method.getAnnotation(DataSource.class);
                // 获取注解指定值
                String value = dataSource.value();
                // 程序人工指定则从方法参数的第一个选取。默认不特殊制定
                if (dataSource.manual()) {
                    // 方法参数的第一个值为数据源name
                    value = point.getArgs()[0] + "";
                }
                // 放入动态数据源容器DynamicDataSourceHolder中
                DynamicDataSourceHolder.putDataSource(value);
                return;    //避免多模式混用
                // 获取spring注解
            } else if (method.isAnnotationPresent(Transactional.class)) {
                Transactional trans = method.getAnnotation(Transactional.class);
                // 只读，则设置从机模式;否则设置主机模式
                if (trans.readOnly()) {
                    // 只读则取从机，放入动态数据源容器DynamicDataSourceHolder中
                    DynamicDataSourceHolder.putSlaveDataSource();
                } else {
                    // 非只读则去主机，放入动态数据源容器DynamicDataSourceHolder中
                    DynamicDataSourceHolder.putMasterDataSource();
                }
                return;    //避免多模式混用
            }
        } catch (Exception e) {
            logger.error("动态切换数据源异常", e);
        }
    }
}
