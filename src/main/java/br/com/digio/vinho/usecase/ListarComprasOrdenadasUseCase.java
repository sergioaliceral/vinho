package br.com.digio.vinho.usecase;

import static java.util.Comparator.comparing;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digio.vinho.dtos.CompraDetalhadaDto;
import br.com.digio.vinho.service.BuscarComprasService;

@Component
public class ListarComprasOrdenadasUseCase {

	private final BuscarComprasService buscarComprasService;

	public ListarComprasOrdenadasUseCase(BuscarComprasService buscarComprasService) {
		this.buscarComprasService = buscarComprasService;
	}

	public List<CompraDetalhadaDto> execute() {
		return buscarComprasService.execute().stream().sorted( comparing(CompraDetalhadaDto::valorTotal) ).toList();
	}

}
