package br.com.digio.vinho.client.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VinhoDto(
		Integer codigo, 
		@JsonProperty("tipo_vinho") String tipoVinho, 
		BigDecimal preco, 
		String safra, 
		@JsonProperty("ano_compra") Integer anoCompra) { }
