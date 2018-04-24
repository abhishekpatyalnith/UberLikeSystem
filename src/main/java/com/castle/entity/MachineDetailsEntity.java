package com.castle.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "machine_details")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "MAC_ADDRESS")
	private String macAddress;

	@Column(name = "ALLOCATED_IP")
	private String allocatedIp;

	@Column(name = "ALLOCATED_DATE")
	private Date allocatedDate;

	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Column(name = "IS_EXPIRED")
	private boolean isExpired;

}
