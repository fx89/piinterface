package com.desolatetimelines.piinterface.piclient.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PIInstancePin implements Serializable {
	private Long boardId;
	
	private int currentStatus;
	
	private Long gpioId;
	
	private int isSignalInverted;

	private String name;
}
