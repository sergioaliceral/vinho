package br.com.digio.vinho.usecase;

import static java.util.Comparator.comparing;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.digio.vinho.dtos.CompraDetalhadaDto;
import br.com.digio.vinho.service.BuscarComprasService;

@Component
public class BuscarMaiorCompraPorAnoUseCase {

	private final BuscarComprasService buscarComprasService;

	public BuscarMaiorCompraPorAnoUseCase(BuscarComprasService buscarComprasService) {
		this.buscarComprasService = buscarComprasService;
	}

	public Optional<CompraDetalhadaDto> execute(Integer ano) {
		return buscarComprasService.execute().stream().filter( c -> c.anoCompra().equals(ano) ).max( comparing(CompraDetalhadaDto::valorTotal) );
	}

}
