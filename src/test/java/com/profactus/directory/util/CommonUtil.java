package com.profactus.directory.util;

public class CommonUtil {
	public static String format(String str) {
		return str.replaceAll("[\\[\\]]", "");
	}
}
