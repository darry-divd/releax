package com.example.demo.http;

import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;

/**
 * 阿里加密请求基类
 * @author 师伟凡	2018-3-7
 */
public abstract class AbstractRequest {

	private final static ExecutableClient GLOBAL_EXECUTABLE_CLIENT;

	static {
		GLOBAL_EXECUTABLE_CLIENT = ExecutableClient.getInstance();
		//从配置文件中获取相应配置信息
		String accessKey = "easy_home-O24nOx6IIMexM9VFbMfm";
		String secretKey = "dZau3RgImNCf9LY382sXEYnDXRdCPMxZE8KZKRYP";
		String protocal ="https";
		String domainName ="s-api.alibaba-inc.com";
		GLOBAL_EXECUTABLE_CLIENT.setProtocal(protocal);
		GLOBAL_EXECUTABLE_CLIENT.setDomainName(domainName);
		GLOBAL_EXECUTABLE_CLIENT.setAccessKey(accessKey);
		GLOBAL_EXECUTABLE_CLIENT.setSecretKey(secretKey);
		GLOBAL_EXECUTABLE_CLIENT.init();
	}

	public ExecutableClient executableClient;

	public AbstractRequest() {
		this.executableClient = GLOBAL_EXECUTABLE_CLIENT;
	}
	
}
