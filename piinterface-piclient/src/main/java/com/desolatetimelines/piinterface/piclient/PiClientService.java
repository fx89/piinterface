package com.desolatetimelines.piinterface.piclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.desolatetimelines.piinterface.piclient.model.PIInstance;

@Component
public class PiClientService {
	private static final String PI_PORT_NUMBER = "4001";

	private final RestTemplate restTemplate = new RestTemplate();

	public PIInstance getInfo(String ipAddress) {

		if (ipAddress == null) {
			throw new IllegalArgumentException("IP address not provided");
		}

		try {
			String url = "http://" + ipAddress + ":" + PI_PORT_NUMBER + "/info";
			PIInstance ret = restTemplate.getForObject(url, PIInstance.class);
			ret.setAddress(ipAddress);
			return ret;
		} catch (ResourceAccessException raEx) {
			return null;
		}
	}
}
