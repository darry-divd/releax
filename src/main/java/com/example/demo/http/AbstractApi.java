package com.example.demo.http;

import com.fasterxml.jackson.databind.ObjectMapper;

public  class AbstractApi {

	/**
	 * 可通过阿里网关的POST请求工具
	 */
	public static HttpPostRequest postRequest;
	
	/**
	 * 可通过阿里网关的GET请求工具
	 */
	public HttpGetRequest getRequest;

	public ObjectMapper objectMapper;
	
	public AbstractApi() {
		postRequest = new HttpPostRequest();
		getRequest = new HttpGetRequest();
		objectMapper = new ObjectMapper();
	}
	
}
