package com.example.nienluannganh.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class ConText {
	@Autowired
	public ApplicationContext context;
}
