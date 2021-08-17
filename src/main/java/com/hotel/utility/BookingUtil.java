package com.hotel.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hotel.constants.BookingConstants;

public class BookingUtil {
	public static String validateDate(String dateStr) {
		String errorMsg = BookingConstants.NO_ERROR;
		try {
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(dateStr, fmt);
		} catch (DateTimeParseException e) {
			errorMsg = BookingConstants.E0003;
			e.printStackTrace();
		} catch (Exception e) {
			errorMsg = BookingConstants.E0002;
			e.printStackTrace();
		}
		System.out.println("validateDate() returnVal: " + errorMsg);
		return errorMsg;
	}

	public static String validateName(String name) {
		String errorMsg = BookingConstants.NO_ERROR;
		name = name != null ? name.trim() : null;
		if (name == null || name.length() == 0) {
			errorMsg = BookingConstants.E0004;
		} else {
			if (name.length() <= 50) {
				String regx = "^[a-zA-Z\\s]+$";
				Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(name);
				if (!matcher.find()) {
					errorMsg = BookingConstants.E0006;
				}
			} else {
				errorMsg = BookingConstants.E0005;
			}
		}
		return errorMsg;
	}
}
