package com.mycompany.webapp.service;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch13Service7InjectPropByXml {
	private int prop1;
	private double prop2;
	private boolean prop3;
	private String prop4;
	
	public Ch13Service7InjectPropByXml() {
		log.info("실행" + toString());
	}

	public void setProp1(int prop1) {
		log.info("실행" + toString());
		this.prop1 = prop1;
	}

	public void setProp2(double prop2) {
		log.info("실행" + toString());
		this.prop2 = prop2;
	}

	public void setProp3(boolean prop3) {
		log.info("실행" + toString());
		this.prop3 = prop3;
	}

	public void setProp4(String prop4) {
		log.info("실행" + toString());
		this.prop4 = prop4;
	}
	
	@Override
	public String toString() {
		String str = "{";
		str += "pro1:" + prop1 +", ";
		str += "pro2:" + prop2 +", ";
		str += "pro3:" + prop3 +", ";
		str += "pro4:" + prop4;
		str += "}";
		return str;
   }
}
