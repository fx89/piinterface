package com.desolatetimelines.piinterface.service.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
	private int type;

	private Date date;

	private String title;

	private String message;

	public Notification(int type, String title, String message) {
		this.type = type;
		this.title = title;
		this.message = message;
		this.date = new Date();
	}

	public Notification(String title, String message) {
		this.type = 0;
		this.title = title;
		this.message = message;
		this.date = new Date();
	}
}
