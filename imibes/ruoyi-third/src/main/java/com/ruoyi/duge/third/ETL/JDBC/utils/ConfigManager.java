package com.ruoyi.duge.third.ETL.JDBC.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件类
 * @author Administrator
 *
 */
public class ConfigManager {
	private static Properties props = null;

	public ConfigManager(){ }

	public ConfigManager(String pathName){
		loadProperties(pathName);
	}

	/**
	 * 加载Properties配置文件
	 * @param pathName 配置文件名
	 */
	public void loadProperties(String pathName){
		try {
			String url = this.getClass().getResource("").getPath().replaceAll("%20", " ");
			String path = url.substring(0, url.indexOf("target")) + "src/main/java/com/ruoyi/duge/third/ETL/JDBC/resources/" + pathName + ".properties";
			props = new Properties();
			props.load(new FileInputStream(path));
		} catch (IOException e) {
			throw new RuntimeException("数据库配置参数加载错误！", e);
		}
	}

	/**
	 * 根据key值返回对应的value值
	 * @param key 关键字
	 * @return value值
	 */
	public String getProperty(String key) {
		if(null != props){
			return props.getProperty(key);
		}
		return null;
	}
}
