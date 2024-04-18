package com.levimartines.rinhabackend.controllers;

import com.levimartines.rinhabackend.services.Service;
import com.levimartines.rinhabackend.entities.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

	private final Service service;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Pessoa pessoa) {
		service.validAndCreate(pessoa);
		URI uri = URI.create("/pessoas/" + pessoa.getId());
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable String id) {
		var p = service.findOne(id);
		if (p.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(p);
	}

	@GetMapping
	public ResponseEntity<?> findByTerm(@RequestParam(required = false) String t) {
		var p = service.findByTerm(t);
		return ResponseEntity.ok(p.getContent());
	}

}
