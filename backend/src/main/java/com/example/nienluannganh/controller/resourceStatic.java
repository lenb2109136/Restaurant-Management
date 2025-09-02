package com.example.nienluannganh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("datasource")
public class resourceStatic {
	@Value("${app.nameSource}")
	private String resource;
	@GetMapping("/{name}")
	public void returnDataSource(@PathVariable("name") String name, HttpServletRequest rq,HttpServletResponse ep) {
		System.out.println(name);
		try {
			File f= new File("C://Users//ADMIN//Documents//A- CT263//images//"+name);

			OutputStream o= ep.getOutputStream();
			InputStream ip= new FileInputStream(f);
			o.write(ip.readAllBytes());
			o.flush();
			ip.close();
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
