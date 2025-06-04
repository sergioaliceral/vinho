package br.com.digio.vinho.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.digio.vinho.client.dtos.ClienteDto;

public class ClienteClientInMemory implements ClienteClient {

	private final Map<String, ClienteDto> clientes = new HashMap<>();

	public void adicionarCliente(ClienteDto cliente) {
		clientes.put(cliente.cpf(), cliente);
	}

	@Override
	public List<ClienteDto> getClientes() {
		return new ArrayList<>(clientes.values());
	}

}
