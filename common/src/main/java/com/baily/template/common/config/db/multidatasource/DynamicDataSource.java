//package com.baily.template.common.config.db.multidatasource;
//
//
//import com.baily.template.common.Constants;
//import com.baily.template.common.config.jpa.MasterJpaRepositoryConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.util.ReflectionUtils;
//
//import javax.sql.DataSource;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.concurrent.atomic.AtomicInteger;
//
//
///**
// * @ClassName: DynamicDataSource
// * @Description:
// * 功能：获取动态数据源
// * 1.配置时，targetDataSources必须配置一个名为master的数据源，不能配置名为replica-set-slave-999999-host的数据源
// * 2.缓存第一次放入的数据源
// * @author:YB
// * @date:2018年01月02日 17:59
// */
////@Configuration("dataSource")
//@AutoConfigureBefore(MasterJpaRepositoryConfig.class)	//在动态数据源之前加载
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    /**
//     *  从库数量
//     */
//    private Integer slaveCount;
//
//    /**
//     * 计数器最大边界
//     */
//    private static final int COUNTER_UPPER_BOUND = 9999;
//
//    /**
//     * 轮询计数,初始为-1,AtomicInteger是线程安全的
//      */
//    private AtomicInteger counter = new AtomicInteger(-1);
//
//    /**
//     * 记录读库的name
//     */
//    private List<Object> slaveDataSourceList = new ArrayList<Object>(0);
//
//    @Autowired
//    @Qualifier("master")
//    private DataSource masterDataSource;
//
//    @Autowired
//    @Qualifier("slave1")
//    private DataSource slave1DataSource;
//
//	@Override
//	protected Object determineCurrentLookupKey() {
//		// 使用DynamicDataSourceHolder保证线程安全，并且得到当前线程中的数据源key
//        Object key = DynamicDataSourceHolder.getDataSouce();
//        if (key == null) {
//            //缓存从第一个会话中的数据源,more未主机模式，主机只有一台
//        	DynamicDataSourceHolder.putMasterDataSource();
//
//            //从机代号，则在从机集群中选出一个
//        } else if (Constants.SLAVE_DATASOURCE_CODE.equals(key)) {
//        	key = getSlaveDataSourceKey();
//            //缓存从第一个会话中的数据源
//        	DynamicDataSourceHolder.putDataSource(key==null?Constants.MASTER_DATASOURCE:key+"");
//        }
//        logger.debug("----datasource-key----" + key);
//        return key;
//	}
//
//	@SuppressWarnings("unchecked")
//    @Override
//    public void afterPropertiesSet() {
//		try {
//			setMultiDataSource();
//		} catch (Exception e) {
//			throw new IllegalArgumentException("Property 'targetDataSources' is required");
//		}
//        super.afterPropertiesSet();	//调用父类初始化方法，初始化属性
//        // 由于父类的resolvedDataSources属性是私有的子类获取不到，需要使用反射获取
//        Field field = ReflectionUtils.findField(AbstractRoutingDataSource.class, "resolvedDataSources");
//        // 设置可访问
//        field.setAccessible(true);
//        try {
//            Map<Object, DataSource> resolvedDataSources = (Map<Object, DataSource>) field.get(this);
//            // 读库的数据量等于数据源总数减去写库的数量
//            this.slaveCount = resolvedDataSources.size() - 1;
//            boolean hasMaster = false;
//            for (Entry<Object, DataSource> entry : resolvedDataSources.entrySet()) {
//                if (Constants.MASTER_DATASOURCE.equals(entry.getKey())) {
//                    //表明主机存在
//                	hasMaster = true;
//                    continue;
//                }
//                //非主机加入从机集合
//                slaveDataSourceList.add(entry.getKey());
//            }
//            if(!hasMaster)
//            	throw new Exception("No master found");
//        } catch (Exception e) {
//        	throw new IllegalArgumentException("Init dataSource error, please check your configuration", e);
//        }
//    }
//
//	/**
//	 * 功能：设置多数据源
//	 * @throws Exception
//	 */
//	private Map<Object, Object> setMultiDataSource() throws Exception {
//		Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
//		dataSourceMap.put(Constants.MASTER_DATASOURCE, masterDataSource);
//		dataSourceMap.put(Constants.SLAVE1_DATASOURCE, slave1DataSource);
//		super.setTargetDataSources(dataSourceMap);
//        //设置默认数据库为主库
//		super.setDefaultTargetDataSource(masterDataSource);
//		return dataSourceMap;
//	}
//
//	/**
//	 * 功能：轮询（或随机）选出从机
//	 * @return
//	 */
//    public Object getSlaveDataSourceKey() {
//        // 得到的下标为：0、1、2、3……
//        Integer index = counter.incrementAndGet() % slaveCount;
//        //超过计数器最大值，则将计数器重置
//        if (counter.get() >= COUNTER_UPPER_BOUND) {
//            // 还原默认值
//            counter.set(-1);
//        }
//    	return slaveDataSourceList.get(index);
//    }
//
//}
