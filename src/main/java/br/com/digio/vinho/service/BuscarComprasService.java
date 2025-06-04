package br.com.digio.vinho.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.digio.vinho.dtos.CompraDetalhadaDto;

@Service
public class BuscarComprasService {

	private final BuscarVinhosService buscarVinhosService;
	private final BuscarClientesService buscarClientesService;

	public BuscarComprasService(BuscarClientesService buscarClientesService, BuscarVinhosService buscarVinhosService) {
		this.buscarVinhosService = buscarVinhosService;
		this.buscarClientesService = buscarClientesService;
	}

	public List<CompraDetalhadaDto> execute() {

		var vinhoMap = buscarVinhosService.execute();

		return buscarClientesService.execute().stream().flatMap(

				cliente -> cliente.compras().stream().map(compra -> {
					
					var vinho = vinhoMap.get(compra.codigo());
					var total = vinho.preco().multiply(BigDecimal.valueOf(compra.quantidade()));
					
					return new CompraDetalhadaDto(cliente.nome(), cliente.cpf(), vinho.tipoVinho(), vinho.safra(), vinho.anoCompra(), compra.quantidade(), vinho.preco(), total);
				
				})).toList();
	}

}
