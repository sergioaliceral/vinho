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

class BuscarRecomendacoesParaTodosClientesUseCaseTest {

	@Test
	void executeTest() {

		// given
		var expectedCodigoTinto = 1;
		var expectedTipoTinto = "Tinto";
		var expectedPrecoTinto = new BigDecimal("100.00");
		var expectedSafraTinto = "2018";
		var expectedAnoTinto = 2023;

		var expectedCodigoBranco = 2;
		var expectedTipoBranco = "Branco";
		var expectedPrecoBranco = new BigDecimal("80.00");
		var expectedSafraBranco = "2019";
		var expectedAnoBranco = 2022;

		var vinhoClient = new VinhoClientInMemory();
		vinhoClient.adicionarVinho(new VinhoDto(expectedCodigoTinto, expectedTipoTinto, expectedPrecoTinto,
				expectedSafraTinto, expectedAnoTinto));
		vinhoClient.adicionarVinho(new VinhoDto(expectedCodigoBranco, expectedTipoBranco, expectedPrecoBranco,
				expectedSafraBranco, expectedAnoBranco));

		var expectedNome = "João";
		var expectedCpf = "12345678900";

		// Comprou mais Tinto (3 tintos, 2 brancos)
		var compras = List.of(
				new CompraItemDto(String.valueOf(expectedCodigoTinto), 1),
				new CompraItemDto(String.valueOf(expectedCodigoTinto), 2),
				new CompraItemDto(String.valueOf(expectedCodigoBranco), 2)
			);

		var clienteClient = new ClienteClientInMemory();
		clienteClient.adicionarCliente(new ClienteDto(expectedNome, expectedCpf, compras));

		var buscarVinhosService = new BuscarVinhosService(vinhoClient);
		var buscarClientesService = new BuscarClientesService(clienteClient);
		var buscarComprasService = new BuscarComprasService(buscarClientesService, buscarVinhosService);
		var buscarComprasPorClienteService = new BuscarComprasPorClienteService(buscarComprasService);

		// when
		var resultado = new BuscarRecomendacoesParaTodosClientesUseCase(buscarComprasPorClienteService).execute();

		// then
		assertEquals(1, resultado.size());

		var recomendacao = resultado.get(0);
		assertEquals(expectedNome, recomendacao.nome());
		assertEquals(expectedCpf, recomendacao.cpf());
		assertEquals(expectedTipoTinto, recomendacao.tipoRecomendado());
	}

}
