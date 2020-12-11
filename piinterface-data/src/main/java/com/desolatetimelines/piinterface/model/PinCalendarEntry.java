package com.desolatetimelines.piinterface.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
@Entity
@Table(name = "PIN_CALENDAR")
public class PinCalendarEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	public PinCalendarEntry(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PIN_ID")
	private Pin pin;

	@Column(name = "DAY")
	private Integer day;

	@Column(name = "MONTH")
	private Integer month;

	@Column(name = "YEAR")
	private Integer year;

	@Column(name = "HOUR")
	private Integer hour;

	@Column(name = "MINUTE")
	private Integer minute;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACTION_ID")
	private PinCalendarAction action;
}
