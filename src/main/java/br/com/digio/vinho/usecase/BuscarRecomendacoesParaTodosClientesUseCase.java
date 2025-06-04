package br.com.digio.vinho.usecase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.digio.vinho.dtos.CompraDetalhadaDto;
import br.com.digio.vinho.dtos.RecomendacaoDto;
import br.com.digio.vinho.service.BuscarComprasPorClienteService;

@Component
public class BuscarRecomendacoesParaTodosClientesUseCase {

	private final BuscarComprasPorClienteService buscarComprasPorClienteService;

	public BuscarRecomendacoesParaTodosClientesUseCase(BuscarComprasPorClienteService buscarComprasPorClienteService) {
		this.buscarComprasPorClienteService = buscarComprasPorClienteService;
	}

	public List<RecomendacaoDto> execute() {
		return buscarComprasPorClienteService.execute().entrySet().stream().map( this::gerarRecomendacao ).toList();
	}

	private RecomendacaoDto gerarRecomendacao(Map.Entry<String, List<CompraDetalhadaDto>> entry) {
		
		var cpf = entry.getKey();
		var compras = entry.getValue();
		var nome = compras.stream().findFirst().map(CompraDetalhadaDto::nomeCliente).orElse("Desconhecido");

		var tipoMaisComprado = compras.stream().collect(
				
				Collectors.groupingBy( CompraDetalhadaDto::tipoVinho, Collectors.summingInt(CompraDetalhadaDto::quantidade)) 
		
			).entrySet().stream().max( Map.Entry.comparingByValue() ).map( Map.Entry::getKey ).orElse("Desconhecido");

		return new RecomendacaoDto(nome, cpf, tipoMaisComprado);
	}

}
