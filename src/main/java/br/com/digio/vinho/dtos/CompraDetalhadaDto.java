package br.com.digio.vinho.dtos;

import java.math.BigDecimal;

public record CompraDetalhadaDto(
		String nomeCliente, 
		String cpf, 
		String tipoVinho, 
		String safra, 
		Integer anoCompra,
		Integer quantidade, 
		BigDecimal precoUnitario, 
		BigDecimal valorTotal) { }
