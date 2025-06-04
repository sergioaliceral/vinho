package br.com.digio.vinho.client.config;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digio.vinho.client.dtos.VinhoDto;
import br.com.digio.vinho.client.service.VinhoClient;
import br.com.digio.vinho.exception.ExternalServiceException;

@Component
public class VinhoClientFallback implements VinhoClient {

	@Override
	public List<VinhoDto> getVinhos() {
		throw new ExternalServiceException("Serviço Vinho indisponível.");
	}

}
