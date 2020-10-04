package com.desolatetimelines.piinterface.model;

import java.io.Serializable;

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
@Table(name = "PIN")
public class Pin implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	public Pin(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PI_INSTANCE_ID")
	private PiInstance piInstance;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BOARD_ID")
	private Long boardId;

	@Column(name = "GPIO_ID")
	private Long gpioId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OPERATING_MODE_ID")
	private PinOperatingMode operatingMode;

	@Column(name = "DELAY_MS")
	private Integer delayMs;

	@Column(name = "IS_AVAILABLE")
	private Boolean isAvailable;
}
