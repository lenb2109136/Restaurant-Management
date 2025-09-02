package com.example.nienluannganh.objectcontroller.responseentity;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.nienluannganh.model.LoaiMonAn;

public class response {
	private HttpStatus status;
	private String message;
	private Object data;
	
	public response(HttpStatus status, String message, Object l) {
        this.status = status;
        this.message = message;
        this.data = l;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
