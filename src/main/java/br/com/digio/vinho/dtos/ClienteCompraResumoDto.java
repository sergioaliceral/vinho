package br.com.digio.vinho.dtos;

import java.math.BigDecimal;

public record ClienteCompraResumoDto(String nome, String cpf, Integer totalCompras, BigDecimal valorTotal) { }
