package br.com.digio.vinho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.digio.vinho.client.dtos.ClienteDto;
import br.com.digio.vinho.client.service.ClienteClient;

@Service
public class BuscarClientesService {

	private final ClienteClient clienteClient;

	public BuscarClientesService(ClienteClient clienteClient) {
		this.clienteClient = clienteClient;
	}

	public List<ClienteDto> execute() {
		return clienteClient.getClientes();
	}

}
