package io.github.rohangoyal2014.cowinnotifier.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
	
	public static final OkHttpClient client = new OkHttpClient();

	public static Pair<Integer, String> performGet(String url, Map<String, String> params, Map<String, String> headers) 
			throws IOException {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		for(Map.Entry<String, String> param : params.entrySet()) {
			urlBuilder.addQueryParameter(param.getKey(), param.getValue());
		}
		
		Request.Builder reqBuilder = new Request.Builder().url(urlBuilder.build());
		for(Map.Entry<String, String> header : headers.entrySet()) {
			reqBuilder.addHeader(header.getKey(), header.getValue());
		}
		
		LOGGER.info("Request:" + reqBuilder.build().toString());
		
		try (Response response = client.newCall(reqBuilder.build()).execute()) {
			LOGGER.info("Request was:" + response.request().toString());
			return Pair.of(response.code(), response.body().string());
		}
	}
	
}
