package br.com.digio.vinho.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.digio.vinho.client.dtos.ClienteDto;
import br.com.digio.vinho.client.dtos.CompraItemDto;
import br.com.digio.vinho.client.service.ClienteClientInMemory;

class BuscarClientesServiceTest {

	@Test
	void executeTest() {

		// given
		var expectedNome = "João";
		var expectedCpf = "12312312379";
		var expectedCompras = List.of(new CompraItemDto("1", 1));
		var cliente = new ClienteDto(expectedNome, expectedCpf, expectedCompras);

		var clienteClient = new ClienteClientInMemory();
		clienteClient.adicionarCliente(cliente);

		// when
		var clienteList = new BuscarClientesService(clienteClient).execute();

		// then
		assertEquals(1, clienteList.size());
		var c = clienteList.get(0);
		assertEquals(expectedNome, c.nome());
		assertEquals(expectedCompras, c.compras());
	}

}
