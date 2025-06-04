package br.com.digio.vinho.client.config;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digio.vinho.client.dtos.ClienteDto;
import br.com.digio.vinho.client.service.ClienteClient;
import br.com.digio.vinho.exception.ExternalServiceException;

@Component
public class ClienteClientFallback implements ClienteClient {

	@Override
	public List<ClienteDto> getClientes() {
		throw new ExternalServiceException("Serviço Cliente indisponível.");
	}

}