package com.levimartines.rinhabackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levimartines.rinhabackend.exceptions.BadRequestException;
import com.levimartines.rinhabackend.converters.StringListConverter;
import com.levimartines.rinhabackend.exceptions.UnprocessableEntityException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Getter
@NoArgsConstructor
public class Pessoa implements Serializable {

	@Id
	@Column(name = "id")
	private UUID id = UUID.randomUUID();

	@Column(name = "nome")
	@Size(max = 100)
	@NotNull
	private String nome;

	@Column(unique = true, name = "apelido")
	@Size(max = 32)
	@NotNull
	private String apelido;

	@Column(name = "nascimento")
	@NotNull
	@NotEmpty
	private String nascimento;

	@Convert(converter = StringListConverter.class)
	@Column(name = "stack")
	private List<String> stack;

	@Column(name = "busca_trgm")
	@Generated
	@JsonIgnore
	public String busca;

	public void valid() {
		if (nome == null || apelido == null || nascimento == null) {
			throw new UnprocessableEntityException();
		}
		if (nome.isBlank() || nome.isEmpty() || nome.length() > 100) {
			throw new UnprocessableEntityException();
		}
		if (apelido.isBlank() || apelido.isEmpty() || apelido.length() > 32) {
			throw new UnprocessableEntityException();
		}
		if (isNumeric(nome) || isNumeric(apelido)) {
			throw new BadRequestException();
		}
		if (birthDateInvalid() || stackInvalid()) {
			throw new BadRequestException();
		}
	}

	public boolean stackInvalid() {
		if (stack != null) {
			for (String s : stack) {
				if (s == null || s.length() > 32 || isNumeric(s)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean birthDateInvalid() {
		try {
			LocalDate.parse(nascimento);
		} catch (DateTimeParseException e) {
			return true;
		}
		return false;
	}

	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
}
