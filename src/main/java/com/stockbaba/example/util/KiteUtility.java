package com.stockbaba.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KiteUtility {

	@Value("${kite.dateFormat:yyyy-MM-dd}")
	private String dateFormat;
	
	private SimpleDateFormat sdf;
	
	public Date formatDate(String strDate) throws ParseException {
		sdf = new SimpleDateFormat(dateFormat);
		return sdf.parse(strDate);
	}
	
}
