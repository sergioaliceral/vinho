package br.com.digio.vinho.client.dtos;

import java.util.List;

public record ClienteDto(String nome, String cpf, List<CompraItemDto> compras) { }
