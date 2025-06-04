package br.com.digio.vinho.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.digio.vinho.client.dtos.VinhoDto;

public class VinhoClientInMemory implements VinhoClient {

	private final Map<Integer, VinhoDto> vinhos = new HashMap<>();

	public void adicionarVinho(VinhoDto vinho) {
		vinhos.put(vinho.codigo(), vinho);
	}

	@Override
	public List<VinhoDto> getVinhos() {
		return new ArrayList<>(vinhos.values());
	}

}
