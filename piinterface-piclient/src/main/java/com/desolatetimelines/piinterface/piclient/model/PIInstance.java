package com.desolatetimelines.piinterface.piclient.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PIInstance implements Serializable {
	private String instanceName;

	private Iterable<PIInstancePin> availablePins;

	private String address;
}
