package br.com.digio.vinho.usecase;

import static java.util.Comparator.comparing;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digio.vinho.dtos.ClienteCompraResumoDto;
import br.com.digio.vinho.dtos.CompraDetalhadaDto;
import br.com.digio.vinho.service.BuscarComprasPorClienteService;

@Component
public class BuscarClientesFieisUseCase {

	private static final int TOP_CLIENTES = 3;

	private final BuscarComprasPorClienteService buscarComprasPorClienteService;

	public BuscarClientesFieisUseCase(BuscarComprasPorClienteService buscarComprasPorClienteService) {
		this.buscarComprasPorClienteService = buscarComprasPorClienteService;
	}

	public List<ClienteCompraResumoDto> execute() {
		
		return buscarComprasPorClienteService.execute().entrySet().stream().map(entry -> {
			
			var compras = entry.getValue();
			var nome = compras.stream().findFirst().map(CompraDetalhadaDto::nomeCliente).orElse("Desconhecido");
			var valorTotal = compras.stream().map( CompraDetalhadaDto::valorTotal ).reduce( BigDecimal.ZERO, BigDecimal::add );
			return new ClienteCompraResumoDto(nome, entry.getKey(), compras.size(), valorTotal);
			
		}).sorted(comparing(ClienteCompraResumoDto::valorTotal).reversed()).limit(TOP_CLIENTES).toList();
	}

}
