package br.com.digio.vinho.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.digio.vinho.client.config.VinhoClientFallback;
import br.com.digio.vinho.client.dtos.VinhoDto;

@FeignClient(name = "vinhoClient", url = "${feign.vinhos.url}", fallback = VinhoClientFallback.class)
public interface VinhoClient {

	@GetMapping("/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
	List<VinhoDto> getVinhos();

}