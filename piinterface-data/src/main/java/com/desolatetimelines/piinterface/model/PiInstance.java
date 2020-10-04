package com.desolatetimelines.piinterface.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "PI_INSTANCE")
public class PiInstance implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	public PiInstance(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAST_REGISTERED_ADDRESS")
	private String lastRegisteredAddress;

	@Column(name = "IS_OFFLINE")
	private Boolean isOffline;
}
