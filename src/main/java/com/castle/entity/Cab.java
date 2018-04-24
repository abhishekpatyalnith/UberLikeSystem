package com.castle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class Cab {

	private Integer id;
	private String cabType;
	private String licencseNumber;
	private Location location;
	private boolean isFree;
	private boolean isActive;

	private Integer riderId;
	
	Ride ride;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cabType == null) ? 0 : cabType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isFree ? 1231 : 1237);
		result = prime * result + ((licencseNumber == null) ? 0 : licencseNumber.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cab other = (Cab) obj;
		if (cabType != other.cabType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive != other.isActive)
			return false;
		if (isFree != other.isFree)
			return false;
		if (licencseNumber == null) {
			if (other.licencseNumber != null)
				return false;
		} else if (!licencseNumber.equals(other.licencseNumber))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
}
