package com.hs.platform.station.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.hs.platform.station.Constants.*;

public class DataSourceUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceUtil.class);
    private DataSource druidMysqlSource;

    private static String maxActive = "20";//PROP_MAXACTIVE = "maxActive";
    private static String minIdle = "3";//PROP_MINIDLE = "minIdle";
    private static String initialSize = "5";//PROP_INITIALSIZE = "initialSize";
    private static String maxWait = "60000";//PROP_MAXWAIT = "maxWait";

    private static String timeBetweenEvictionRunsMillis = "60000";//PROP_TIMEBETWEENEVICTIONRUNSMILLIS = "timeBetweenEvictionRunsMillis";
    private static String minEvictableIdleTimeMillis = "300000";//PROP_MINEVICTABLEIDLETIMEMILLIS = "minEvictableIdleTimeMillis";

    private static String testWhileIdle = "true";//PROP_TESTWHILEIDLE = "testWhileIdle";
    private static String testOnBorrow = "false";//PROP_TESTONBORROW = "testOnBorrow";
    private static String testOnReturn = "false";//PROP_TESTONRETURN = "testOnReturn";

    private static String poolPreparedStatements = "true";// PROP_POOLPREPAREDSTATEMENTS = "poolPreparedStatements";
    private static String maxOpenPreparedStatements = "20"; //  PROP_MAXOPENPREPAREDSTATEMENTS = "maxOpenPreparedStatements"

    private static class SingletonHolder {
        private static DataSourceUtil instance = new DataSourceUtil();
    }


    private DataSourceUtil() {
        if (druidMysqlSource == null) {
            Map dbMap = new HashMap();

            dbMap.put(DruidDataSourceFactory.PROP_MAXACTIVE, maxActive);
            dbMap.put(DruidDataSourceFactory.PROP_MINIDLE, minIdle);
            dbMap.put(DruidDataSourceFactory.PROP_INITIALSIZE, initialSize);
            dbMap.put(DruidDataSourceFactory.PROP_MAXWAIT, maxWait);
            dbMap.put(DruidDataSourceFactory.PROP_TIMEBETWEENEVICTIONRUNSMILLIS, timeBetweenEvictionRunsMillis);
            dbMap.put(DruidDataSourceFactory.PROP_MINEVICTABLEIDLETIMEMILLIS, minEvictableIdleTimeMillis);
            dbMap.put(DruidDataSourceFactory.PROP_TESTWHILEIDLE, testWhileIdle);
            dbMap.put(DruidDataSourceFactory.PROP_TESTONBORROW, testOnBorrow);
            dbMap.put(DruidDataSourceFactory.PROP_TESTONRETURN, testOnReturn);
            dbMap.put(DruidDataSourceFactory.PROP_POOLPREPAREDSTATEMENTS, poolPreparedStatements);
            dbMap.put(DruidDataSourceFactory.PROP_MAXOPENPREPAREDSTATEMENTS, maxOpenPreparedStatements);
            try {
                String driverClassName = driver_class_name;
                String username = db_username;
                String password = db_password;
                String url = db_url;
                dbMap.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, driverClassName);
                dbMap.put(DruidDataSourceFactory.PROP_USERNAME, username);
                dbMap.put(DruidDataSourceFactory.PROP_PASSWORD, password);
                dbMap.put(DruidDataSourceFactory.PROP_URL, url);
                druidMysqlSource = DruidDataSourceFactory.createDataSource(dbMap);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static final DataSource getDataSource() {
        return SingletonHolder.instance.druidMysqlSource;
    }
}
