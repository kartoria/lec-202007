package kr.or.ddit.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
	
	protected void printInfo(String varName, Object var) {
		LOGGER.info("☆★☆★☆ {} >>> {} ☆★☆★☆", varName, var);
	}
}
