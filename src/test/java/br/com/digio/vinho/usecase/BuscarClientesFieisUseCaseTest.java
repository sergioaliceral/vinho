package br.com.digio.vinho.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.digio.vinho.client.dtos.ClienteDto;
import br.com.digio.vinho.client.dtos.CompraItemDto;
import br.com.digio.vinho.client.dtos.VinhoDto;
import br.com.digio.vinho.client.service.ClienteClientInMemory;
import br.com.digio.vinho.client.service.VinhoClientInMemory;
import br.com.digio.vinho.service.BuscarClientesService;
import br.com.digio.vinho.service.BuscarComprasPorClienteService;
import br.com.digio.vinho.service.BuscarComprasService;
import br.com.digio.vinho.service.BuscarVinhosService;

class BuscarClientesFieisUseCaseTest {

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

		var expectedNome1 = "João";
		var expectedCpf1 = "123";
		var expectedQuantidade1 = 10;
		var expectedValorTotal1 = expectedPreco.multiply(BigDecimal.valueOf(10));

		var expectedNome2 = "Maria";
		var expectedCpf2 = "456";
		var expectedQuantidade2 = 9;
		var expectedValorTotal2 = expectedPreco.multiply(BigDecimal.valueOf(9));

		var expectedNome3 = "Carlos";
		var expectedCpf3 = "789";
		var expectedQuantidade3 = 8;
		var expectedValorTotal3 = expectedPreco.multiply(BigDecimal.valueOf(8));

		var expectedNome4 = "Lucas";
		var expectedCpf4 = "999";
		var expectedQuantidade4 = 7;

		var cliente1 = new ClienteDto(expectedNome1, expectedCpf1, List.of(new CompraItemDto("1", expectedQuantidade1)));
		var cliente2 = new ClienteDto(expectedNome2, expectedCpf2, List.of(new CompraItemDto("1", expectedQuantidade2)));
		var cliente3 = new ClienteDto(expectedNome3, expectedCpf3, List.of(new CompraItemDto("1", expectedQuantidade3)));
		var cliente4 = new ClienteDto(expectedNome4, expectedCpf4, List.of(new CompraItemDto("1", expectedQuantidade4)));

		var clienteClient = new ClienteClientInMemory();
		clienteClient.adicionarCliente(cliente4);
		clienteClient.adicionarCliente(cliente3);
		clienteClient.adicionarCliente(cliente2);
		clienteClient.adicionarCliente(cliente1);

		var buscarVinhosService = new BuscarVinhosService(vinhoClient);
		var buscarClientesService = new BuscarClientesService(clienteClient);
		var buscarComprasService = new BuscarComprasService(buscarClientesService, buscarVinhosService);
		var buscarComprasPorClienteService = new BuscarComprasPorClienteService(buscarComprasService);

		// when
		var resultado = new BuscarClientesFieisUseCase(buscarComprasPorClienteService).execute();

		// then
		assertEquals(3, resultado.size());

		assertEquals(expectedNome1, resultado.get(0).nome());
		assertEquals(expectedValorTotal1, resultado.get(0).valorTotal());

		assertEquals(expectedNome2, resultado.get(1).nome());
		assertEquals(expectedValorTotal2, resultado.get(1).valorTotal());

		assertEquals(expectedNome3, resultado.get(2).nome());
		assertEquals(expectedValorTotal3, resultado.get(2).valorTotal());
	}

}
