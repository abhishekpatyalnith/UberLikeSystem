package com.castle.utility;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Utility {
	

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	public Date getExpiryDate(Date date, String seconds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, Integer.valueOf(seconds));

		return calendar.getTime();
	}

	public String intToIp(long i) {
		return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
	}

	public Long ipToInt(String addr) {
		String[] addrArray = addr.split("\\.");

		long num = 0;
		for (int i = 0; i < addrArray.length; i++) {
			int power = 3 - i;

			num += ((Integer.parseInt(addrArray[i]) % 256 * Math.pow(256, power)));
		}
		return num;
	}

	public boolean isExpired(Date now, Date expiryTime) {
		int i = now.compareTo(expiryTime);
		if (i < 0) {
			return false;
		}
		return true;
	}

	public String getNextIpToAllocate(String lastAllocatedIp) {
		Long ip = ipToInt(lastAllocatedIp);
		logger.info("numeral ip " + ip);
		ip++;
		String allocatedIp = intToIp(ip);
		logger.info("next ip " + allocatedIp);
		return allocatedIp;
	}
	
	/*public String generateApplicationNumber(){
		String seqId=mongoDao.getNextSequence("ip").toString();
		String applicationNumber="17";
		Integer counter=10-seqId.length();
		for(int i=0;i<counter;i++){
			applicationNumber+="0";
		}
		return applicationNumber+seqId;
	}*/

}
