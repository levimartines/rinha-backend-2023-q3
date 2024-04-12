package com.levimartines.rinhabackend;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Data
@NoArgsConstructor
public class Pessoa {

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

	public void valid() {
		if (nome == null || apelido == null || nascimento == null) {
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
