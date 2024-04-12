package com.levimartines.rinhabackend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contagem-pessoas")
@RequiredArgsConstructor
public class ContagemController {

	private final Service service;

	@GetMapping
	public ResponseEntity<?> count() {
		return ResponseEntity.ok(service.count());
	}

}
