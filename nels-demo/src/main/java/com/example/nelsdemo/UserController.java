package com.example.nelsdemo;

import java.util.Arrays;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
		String filename = "tutorials.csv";
		InputStreamResource file = new InputStreamResource(CSVHelper
				.tutorialsToCSV(Arrays.asList(new User("1", "abkp009@gmail.com"), new User("2", "kumar@gmail.com"))));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

}