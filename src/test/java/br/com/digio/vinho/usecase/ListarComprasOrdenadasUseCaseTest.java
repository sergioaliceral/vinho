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
import br.com.digio.vinho.service.BuscarComprasService;
import br.com.digio.vinho.service.BuscarVinhosService;

class ListarComprasOrdenadasUseCaseTest {

	@Test
	void executeTest() {

		// given
		var expectedCodigoVinho = 1;
		var expectedTipoVinho = "Tinto";
		var expectedPreco = new BigDecimal("100.00");
		var expectedSafra = "2018";
		var expectedAnoCompra = 2023;

		var vinhoClient = new VinhoClientInMemory();
		vinhoClient.adicionarVinho(new VinhoDto(expectedCodigoVinho, expectedTipoVinho, expectedPreco, expectedSafra, expectedAnoCompra));

		var clienteClient = new ClienteClientInMemory();
		
		var nomeCliente1 = "João";
		var cpfCliente1 = "123";
		var quantidade1 = 1; // total: 100
		var cliente1 = new ClienteDto(nomeCliente1, cpfCliente1, List.of(new CompraItemDto(String.valueOf(expectedCodigoVinho), quantidade1)));
		clienteClient.adicionarCliente(cliente1);
		
		var nomeCliente2 = "Maria";
		var cpfCliente2 = "456";
		var quantidade2 = 3; // total: 300		
		var cliente2 = new ClienteDto(nomeCliente2, cpfCliente2, List.of(new CompraItemDto(String.valueOf(expectedCodigoVinho), quantidade2)));
		clienteClient.adicionarCliente(cliente2);

		var buscarVinhosService = new BuscarVinhosService(vinhoClient);
		var buscarClientesService = new BuscarClientesService(clienteClient);
		var buscarComprasService = new BuscarComprasService(buscarClientesService, buscarVinhosService);
		
		// when
		var resultado = new ListarComprasOrdenadasUseCase(buscarComprasService).execute();

		// then
		assertEquals(2, resultado.size());

		var primeiraCompra = resultado.get(0);
		var segundaCompra = resultado.get(1);

		assertEquals(cpfCliente1, primeiraCompra.cpf()); // João: 100
		assertEquals(cpfCliente2, segundaCompra.cpf()); // Maria: 300

		assertEquals(new BigDecimal("100.00"), primeiraCompra.valorTotal());
		assertEquals(new BigDecimal("300.00"), segundaCompra.valorTotal());
	}
	
}
