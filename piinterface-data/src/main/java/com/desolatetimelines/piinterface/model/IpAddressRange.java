package com.desolatetimelines.piinterface.model;

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
@Table(name = "IP_ADDRESS_RANGE")
public class IpAddressRange {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	public IpAddressRange(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	private String name;

	@Column(name = "PREFIX")
	private String prefix;

	@Column(name = "RANGE_START")
	private String rangeStart;

	@Column(name = "RANGE_END")
	private String rangeEnd;
}
