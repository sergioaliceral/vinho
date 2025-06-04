package br.com.digio.vinho.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.digio.vinho.client.config.ClienteClientFallback;
import br.com.digio.vinho.client.dtos.ClienteDto;

@FeignClient(name = "clienteClient", url = "${feign.clientes.url}", fallback = ClienteClientFallback.class)
public interface ClienteClient {

	@GetMapping("/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
	List<ClienteDto> getClientes();

}