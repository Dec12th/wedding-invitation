package com.baily.template.common;

/**
 * @ClassName: Constants
 * @Description:
 * @author:YB
 * @date:2018年01月02日 15:59
 */
public class Constants {

    //写库对应的数据源key
    public static final String MASTER_DATASOURCE = "master";
    public static final String SLAVE1_DATASOURCE = "slave1";

    //slave模式编号
    public static final String SLAVE_DATASOURCE_CODE = "replica-set-slave-999999-host";

    public static final String COMMON_REDIS_TOOL_BEAN_ID = "redisTool";

    /**
     * 默认编码格式
     */
    public static final String CHARSET_DEFAULT = "utf-8";

    /**
     * session在redis中的过期时间,单位:秒
     */
    public static final int SESSION_REDIS_TIMEOUT = 1800;
}
