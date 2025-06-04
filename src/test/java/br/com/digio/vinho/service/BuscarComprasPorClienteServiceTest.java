package br.com.digio.vinho.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.digio.vinho.client.dtos.ClienteDto;
import br.com.digio.vinho.client.dtos.CompraItemDto;
import br.com.digio.vinho.client.dtos.VinhoDto;
import br.com.digio.vinho.client.service.ClienteClientInMemory;
import br.com.digio.vinho.client.service.VinhoClientInMemory;

class BuscarComprasPorClienteServiceTest {

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
		var expectedCompras = List.of(new CompraItemDto("1", 10), new CompraItemDto("1", 20));
		var cliente = new ClienteDto(expectedNome, expectedCpf, expectedCompras);

		var clienteClient = new ClienteClientInMemory();
		clienteClient.adicionarCliente(cliente);

		var buscarVinhosService = new BuscarVinhosService(vinhoClient);
		var buscarClientesService = new BuscarClientesService(clienteClient);
		var buscarComprasService = new BuscarComprasService(buscarClientesService, buscarVinhosService);

		// when
		var compraDetalhadaMap = new BuscarComprasPorClienteService(buscarComprasService).execute();

		// then
		assertEquals(1, compraDetalhadaMap.size());
		assertTrue(compraDetalhadaMap.containsKey(expectedCpf));

		var compraDetalhadaList = compraDetalhadaMap.get(expectedCpf);
		assertEquals(2, compraDetalhadaList.size());

		var compraDetalhada = compraDetalhadaList.get(0);
		assertEquals(expectedNome, compraDetalhada.nomeCliente());
		assertEquals(expectedCpf, compraDetalhada.cpf());
	}

}
