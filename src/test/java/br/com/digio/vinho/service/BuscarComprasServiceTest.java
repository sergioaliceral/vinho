package br.com.digio.vinho.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.digio.vinho.client.dtos.ClienteDto;
import br.com.digio.vinho.client.dtos.CompraItemDto;
import br.com.digio.vinho.client.dtos.VinhoDto;
import br.com.digio.vinho.client.service.ClienteClientInMemory;
import br.com.digio.vinho.client.service.VinhoClientInMemory;
import br.com.digio.vinho.dtos.CompraDetalhadaDto;

class BuscarComprasServiceTest {

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

		var expectedNome = "João";
		var expectedCpf = "12312312379";
		var expectedComprasQuantidade = 10;
		var expectedCompras = List.of(new CompraItemDto("1", expectedComprasQuantidade));
		var cliente = new ClienteDto(expectedNome, expectedCpf, expectedCompras);

		var clienteClient = new ClienteClientInMemory();
		clienteClient.adicionarCliente(cliente);

		var buscarVinhosService = new BuscarVinhosService(vinhoClient);
		var buscarClientesService = new BuscarClientesService(clienteClient);

		// when
		var resultado = new BuscarComprasService(buscarClientesService, buscarVinhosService).execute();

		// then
		assertEquals(1, resultado.size());
		CompraDetalhadaDto compraDetalhada = resultado.get(0);
		assertEquals(expectedNome, compraDetalhada.nomeCliente());
		assertEquals(expectedCpf, compraDetalhada.cpf());
		assertEquals(expectedTipo, compraDetalhada.tipoVinho());
		assertEquals(expectedSafra, compraDetalhada.safra());
		assertEquals(expectedAnoCompra, compraDetalhada.anoCompra());
		assertEquals(expectedComprasQuantidade, compraDetalhada.quantidade());
		assertEquals(expectedPreco, compraDetalhada.precoUnitario());
		assertEquals(expectedPreco.multiply(BigDecimal.valueOf(expectedComprasQuantidade)), compraDetalhada.valorTotal());
	}

}
