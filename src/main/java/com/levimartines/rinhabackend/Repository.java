package com.levimartines.rinhabackend;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface Repository extends JpaRepository<Pessoa, UUID> {

	@Query("SELECT p FROM Pessoa p WHERE p.apelido LIKE :t OR p.nome LIKE :t OR cast(p.stack as string) LIKE %:t%")
	Page<Pessoa> searchAllByTerm(String t, Pageable p);
}
