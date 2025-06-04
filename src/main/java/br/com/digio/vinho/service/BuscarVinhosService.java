package br.com.digio.vinho.service;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.digio.vinho.client.dtos.VinhoDto;
import br.com.digio.vinho.client.service.VinhoClient;

@Service
public class BuscarVinhosService {

	private final VinhoClient vinhoClient;

	public BuscarVinhosService(VinhoClient vinhoClient) {
		this.vinhoClient = vinhoClient;
	}

	public Map<String, VinhoDto> execute() {
		var vinhos = vinhoClient.getVinhos();
		return vinhos.stream().collect(toMap(v -> valueOf(v.codigo()), v -> v));
	}

}