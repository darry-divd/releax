package com.example.demo.http;

import com.alibaba.xxpt.gateway.shared.client.constants.HttpConstants;
import com.alibaba.xxpt.gateway.shared.client.http.PostClient;

import java.util.Map;

/**
 * 封装阿里加密POST请求
 * @author 师伟凡	2018-3-7
 */
public class HttpPostRequest  {

	public 	HttpPostRequest() {
		super();
	}
	
	/**
	 * 执行POST请求
	 * @param uri	域名地址
	 * @param api	接口地址	
	 * @param parameter	发送参数
	 * @return
	 */
	public String execute(String uri,String api,Map<String,String> parameter) {
		PostClient client = PostClient.newInstance(this.executableClient, uri, api, "1.0", HttpConstants.HTTP_CLIENT_TIMEOUT);
		//添加参数
		for(Map.Entry<String, String> entry : parameter.entrySet()) {
			client.addParameter(entry.getKey(), entry.getValue());
		}
		String result = client.post();
		return result;
	}
	
}
