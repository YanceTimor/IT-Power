package com.yance520.itpower;

import com.alibaba.dubbo.container.Main;
import org.apache.log4j.Logger;

/**
 * 服务启动类
 */
public class ApiRpcServiceApplication {
	private Logger log=Logger.getLogger(this.getClass());

	public static void main(String[] args) {
		System.out.println(">>>>> power-rpc-service 正在启动 <<<<<");
		//new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		Main.main(args);
		System.out.println(">>>>> power-rpc-service 启动完成 <<<<<");
	}

}
