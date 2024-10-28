package com.jsp.pharmassist.config;

import java.time.Year;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String str = UUID.randomUUID().toString();
		return Year.now().toString()+str;
	}

}
