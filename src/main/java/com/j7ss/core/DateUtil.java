package com.j7ss.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String format(Date date){
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

}
