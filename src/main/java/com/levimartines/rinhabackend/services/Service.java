package com.levimartines.rinhabackend.services;

import com.levimartines.rinhabackend.entities.Pessoa;
import com.levimartines.rinhabackend.exceptions.BadRequestException;
import com.levimartines.rinhabackend.repositories.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Service {

	private final Repository repository;

	public void validAndCreate(Pessoa pessoa) {
		pessoa.valid();
		repository.save(pessoa);
	}

	public Optional<Pessoa> findOne(String id) {
		UUID uuid = UUID.fromString(id);
		return repository.findById(uuid);
	}

	public Page<Pessoa> findByTerm(String t) {
		if (t == null || t.isEmpty()) {
			throw new BadRequestException();
		}
		return repository.searchAllByTerm(t, PageRequest.of(0, 50));
	}

	public long count() {
		return repository.count();
	}
}
