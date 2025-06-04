package br.com.digio.vinho.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.digio.vinho.client.dtos.VinhoDto;
import br.com.digio.vinho.client.service.VinhoClientInMemory;

class BuscarVinhosServiceTest {

	@Test
	void executeTest() {

		// given
		var expectedCodigo = 1;
		var expectedTipo = "Tinto";
		var expectedPreco = new BigDecimal("100.00");
		var expectedSafra = "2018";
		var expectedAnoCompra = 2023;
		var vinho = new VinhoDto(expectedCodigo, expectedTipo, expectedPreco, expectedSafra, expectedAnoCompra);

		var vinhoClient = new VinhoClientInMemory();
		vinhoClient.adicionarVinho(vinho);

		// when
		var vinhosMap = new BuscarVinhosService(vinhoClient).execute();

		// then
		assertEquals(1, vinhosMap.size());
		var vinhoDto = vinhosMap.get(String.valueOf(expectedCodigo));
		assertEquals(expectedTipo, vinhoDto.tipoVinho());
		assertEquals(expectedPreco, vinhoDto.preco());
		assertEquals(expectedSafra, vinhoDto.safra());
		assertEquals(expectedAnoCompra, vinhoDto.anoCompra());
	}

}
