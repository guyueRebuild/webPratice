package com.iTeam.util;

import java.util.UUID;

public class CodecUtil {
	
	public static String createUUID(){
		return UUID.randomUUID().toString();
	}
}