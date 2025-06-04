package br.com.digio.vinho.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.digio.vinho.dtos.CompraDetalhadaDto;

@Service
public class BuscarComprasPorClienteService {

	private final BuscarComprasService buscarComprasService;

	public BuscarComprasPorClienteService(BuscarComprasService buscarComprasService) {
		this.buscarComprasService = buscarComprasService;
	}

	public Map<String, List<CompraDetalhadaDto>> execute() {
		return buscarComprasService.execute().stream().collect( groupingBy(CompraDetalhadaDto::cpf) );
	}
	
}
