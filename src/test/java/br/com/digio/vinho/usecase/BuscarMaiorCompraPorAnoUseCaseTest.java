package br.com.digio.vinho.usecase;

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
import br.com.digio.vinho.service.BuscarClientesService;
import br.com.digio.vinho.service.BuscarComprasService;
import br.com.digio.vinho.service.BuscarVinhosService;

class BuscarMaiorCompraPorAnoUseCaseTest {

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

		var clienteClient = new ClienteClientInMemory();
		
		var expectedNome = "João";
		var expectedCpf = "123";
		var expectedQuantidade = 5;
		var compra1 = new CompraItemDto("1", expectedQuantidade); // total = 500.00
		var cliente1 = new ClienteDto(expectedNome, expectedCpf, List.of(compra1));
		clienteClient.adicionarCliente(cliente1);
		
		var compra2 = new CompraItemDto("1", 1); // total = 100.00
		var cliente2 = new ClienteDto("Carlos", "456", List.of(compra2));
		clienteClient.adicionarCliente(cliente2);

		var buscarVinhosService = new BuscarVinhosService(vinhoClient);
		var buscarClientesService = new BuscarClientesService(clienteClient);
		var buscarComprasService = new BuscarComprasService(buscarClientesService, buscarVinhosService);

		// when
		var resultado = new BuscarMaiorCompraPorAnoUseCase(buscarComprasService).execute(expectedAnoCompra);

		// then
		assertTrue(resultado.isPresent());

		var compraDetalhada = resultado.get();
		assertEquals(expectedNome, compraDetalhada.nomeCliente());
		assertEquals(expectedCpf, compraDetalhada.cpf());
		assertEquals(expectedTipo, compraDetalhada.tipoVinho());
		assertEquals(expectedSafra, compraDetalhada.safra());
		assertEquals(expectedAnoCompra, compraDetalhada.anoCompra());
		assertEquals(expectedQuantidade, compraDetalhada.quantidade());
		assertEquals(expectedPreco, compraDetalhada.precoUnitario());
		assertEquals(expectedPreco.multiply(BigDecimal.valueOf(expectedQuantidade)), compraDetalhada.valorTotal());
	}

}
