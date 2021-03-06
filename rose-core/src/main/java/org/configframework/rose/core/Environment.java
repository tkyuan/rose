/**
 * 
 */
package org.configframework.rose.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuantengkai
 * 环境信息
 */
public class Environment {
	
	private static final Logger logger = LoggerFactory.getLogger(Environment.class);
	
	private static final String EnvFilePath = "/data/webapps/appenv";
	
	private static final String RosePropertyPath = "classpath:META-INF/rose.properties";
	
	private static Properties props;
	
	static{
		props = new Properties();
//		InputStream in;
		try {
//			in = new FileInputStream(EnvFilePath);
//			props.load(in);
			props = PropertiesLoader.load(EnvFilePath);
			
			props.putAll(PropertiesLoader.load(RosePropertyPath));//如果有重复的key 会替换掉之前的值
			
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException,filePath:"+EnvFilePath);
		} catch (IOException e) {
			logger.error("load envFile IOException,filePath:"+ EnvFilePath);
		}
		
		
	}
	
	public static String getZkAddress(){
		return props.getProperty("zkserver");
	}
	
	public static String getAppname(){
		return props.getProperty("appname");
	}

}
