package com.desolatetimelines.piinterface.piclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.desolatetimelines.piinterface.piclient.model.PIInstance;
import com.desolatetimelines.piinterface.piclient.model.PIInstancePin;

@Component
public class PiClientService {
	private static final String PI_PORT_NUMBER = "4001";

	private final RestTemplate restTemplate = new RestTemplate();

	/**
	 * Returns the status information of the PI instance listening at the given address.
	 * Returns null in case of network errors or if the PI instace is down.
	 */
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

	/**
	 * Switches the pin identified by the given board id of the PI instance
	 * listening at the given address to the given state and returns the status.
	 * Returns null in case of network errors or if the PI instace is down.
	 */
	public PIInstancePin switchPinByBoardId(String ipAddress, Long boardId, int toState) {
		if (ipAddress == null) {
			throw new IllegalArgumentException("IP address not provided");
		}

		if (boardId == null) {
			throw new IllegalArgumentException("Pin ID not provided");
		}

		if (toState != 0 && toState != 1) {
			throw new IllegalArgumentException("Illegal pin state. Must be 0 or 1.");
		}

		try {
			String url = "http://" + ipAddress + ":" + PI_PORT_NUMBER + "/switchPinByBoardId";
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
		        .queryParam("boardId", boardId)
		        .queryParam("state", toState);
			
			System.out.println(builder.buildAndExpand().toUri()); // TODO: remove
			
			PIInstancePin ret = restTemplate.getForObject(builder.buildAndExpand().toUri(), PIInstancePin.class);
			return ret;
		} catch (ResourceAccessException raEx) {
			return null;
		}
	}
	
	/**
	 * Switches the state of the pin identified by the given board id of the PI
	 * instance listening at the given address to 1 for the given amount of
	 * milliseconds and then returns the status.
	 * Returns null in case of network errors or if the PI instace is down.
	 */
	public PIInstancePin clickPinByBoardId(String ipAddress, Long boardId, int pressTimeMS) {
		if (ipAddress == null) {
			throw new IllegalArgumentException("IP address not provided");
		}

		if (boardId == null) {
			throw new IllegalArgumentException("Pin ID not provided");
		}

		if (pressTimeMS < 25 || pressTimeMS > 5000) {
			throw new IllegalArgumentException("Illegal pressTimeMS. Must be between 25 and 5000");
		}

		try {
			String url = "http://" + ipAddress + ":" + PI_PORT_NUMBER + "/clickPinByBoardId";
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
		        .queryParam("boardId", boardId)
		        .queryParam("pressTimeMS", pressTimeMS);
			
			System.out.println(builder.buildAndExpand().toUri()); // TODO: remove
			
			PIInstancePin ret = restTemplate.getForObject(builder.buildAndExpand().toUri(), PIInstancePin.class);
			return ret;
		} catch (ResourceAccessException raEx) {
			return null;
		}
	}
}
