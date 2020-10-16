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
@Table(name = "UI_BUTTON")
public class UiButton implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	public UiButton(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	private String title;

	@Column(name = "ICON_ID")
	private Long iconId;

	@Column(name = "BUTTON_ORDER")
	private Long order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TYPE_ID")
	private UiButtonType type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LINKED_TO_PIN_ID")
	private Pin linkedToPin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LINKED_TO_PIN_GROUP_ID")
	private PinGroup linkedToPinGroup;

}
