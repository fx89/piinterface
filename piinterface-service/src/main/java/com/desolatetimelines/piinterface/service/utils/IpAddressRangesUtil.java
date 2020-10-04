package com.desolatetimelines.piinterface.service.utils;

import com.desolatetimelines.piinterface.model.IpAddressRange;

public class IpAddressRangesUtil {

	public static enum IpAddressType {
		v4,
		v6
	}

	public static int parseIpAddressDigit(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("Bad input string. Must have 1 to 3 chars.");
		}

		int nbr;

		IpAddressType digitType = getMostLikelyIpAddressDigitType(str);

		switch (digitType) {
			case v4:
				try {
					nbr = Integer.parseInt(str, 10);
					if (nbr > 255) {
						throw new IllegalArgumentException("Bad input string [" + str + "]. Must be less than or equal to 255. ");
					}
					break;
				} catch (NumberFormatException nfEx) {
					throw new IllegalArgumentException("Bad input string [" + str + "]. Must be a 1 to 3 digit base 10 number");
				}
			case v6:
				try {
					nbr = Integer.parseInt(str, 16);
					if (nbr > 256) {
						throw new IllegalArgumentException("Bad input string [" + str + "]. Must be less than or equal to 256. ");
					}
					break;
				} catch (NumberFormatException nfEx) {
					throw new IllegalArgumentException("Bad input string [" + str + "]. Must be a 2 digit HEX");
				}
			default:
				throw new IllegalArgumentException("Bad input string [" + str + "]. Must have 1 to 3 chars.");
		}

		if (nbr < 0) {
			throw new IllegalArgumentException("Bad input string [" + str + "]. Must be positive. ");
		}

		return nbr;
	}

	public static IpAddressType getIpAddessRangeType(IpAddressRange range) {
		IpAddressType rangeStartType = getMostLikelyIpAddressDigitType(range.getRangeStart());
		IpAddressType rangeEndType = getMostLikelyIpAddressDigitType(range.getRangeEnd());

		if (rangeStartType != rangeEndType) {
			throw new IllegalArgumentException("Range start and end values are not consistent. One seems to be part of an IPv4 address and the other seems to be part of an IPv6 address");
		}

		return rangeStartType;
	}

	private static IpAddressType getMostLikelyIpAddressDigitType(String digit) {
		if (digit == null || digit.length() == 0) {
			throw new IllegalArgumentException("Bad input string. Must have 2 or 3 chars.");
		}

		try {
			Integer.parseInt(digit, 10);
			return IpAddressType.v4;
		} catch (Exception ex4) {
			try {
				Integer.parseInt(digit, 16);
				return IpAddressType.v6;
			} catch (Exception ex6) {
				throw new IllegalArgumentException("Bad input string [" + digit + "]. Must be either base 10 or base 16");
			}
		}
	}

	public static String toIpAddressDigit(int nbr, IpAddressType addressType) {
		switch (addressType) {
			case v4:
				return Integer.toString(nbr);
			case v6:
				return Integer.toHexString(nbr);
			default:
				throw new IllegalArgumentException("Bad address type"); // Should never happen
		}
	}
}
