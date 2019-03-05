package com.bestaone.aiwan.auth.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Long> delete(@PathVariable Long id) {
		return ResponseEntity.ok(id);
	}

}
