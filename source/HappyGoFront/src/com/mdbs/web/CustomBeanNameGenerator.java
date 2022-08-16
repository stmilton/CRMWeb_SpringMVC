/*
 * Created on : 2011/4/29
 */
package com.mdbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 *
 * @author Ringo
 */
public class CustomBeanNameGenerator implements BeanNameGenerator {
	private static final Logger logger = LoggerFactory.getLogger(CustomBeanNameGenerator.class);

	@Override
	public String generateBeanName(BeanDefinition definition,
			BeanDefinitionRegistry registry) {
		String fqn = definition.getBeanClassName();
		logger.trace(fqn);
		return fqn;
	}
}
