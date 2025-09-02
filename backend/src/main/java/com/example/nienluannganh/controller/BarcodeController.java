package com.example.nienluannganh.controller;

import org.springframework.http.MediaType;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.service.GenderService;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api/code")
public class BarcodeController {
	 @Autowired
	    private GenderService genderService;

	    @GetMapping(value = "/qrcode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	    public ResponseEntity<byte[]> getQRCode(@PathVariable("id") Integer id) throws WriterException, IOException {
	        byte[] qrImage = genderService.generateQRCodeAsBytes(id);
	        return ResponseEntity.ok(qrImage);
	    }

	    @GetMapping(value = "/barcode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	    public ResponseEntity<byte[]> getBarcode(@PathVariable("id") Integer id) throws WriterException, IOException {
	        byte[] barcodeImage = genderService.generateBarcodeAsBytes(id);
	        return ResponseEntity.ok(barcodeImage);
	    }
}
