package com.example.calendar.config;

import java.io.IOException;
import java.util.Date;

import com.example.calendar.until.CommonFn;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
public class TimeZoneConvert extends StdSerializer<Date> {

	public TimeZoneConvert() {
		this(null);
	}

	public TimeZoneConvert(Class<Date> c) {
		super(c);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		try {
			gen.writeString(CommonFn.formatterDate(value));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

}
