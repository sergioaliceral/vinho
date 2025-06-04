package br.com.digio.vinho.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digio.vinho.dtos.ClienteCompraResumoDto;
import br.com.digio.vinho.dtos.CompraDetalhadaDto;
import br.com.digio.vinho.dtos.RecomendacaoDto;
import br.com.digio.vinho.usecase.BuscarClientesFieisUseCase;
import br.com.digio.vinho.usecase.BuscarMaiorCompraPorAnoUseCase;
import br.com.digio.vinho.usecase.BuscarRecomendacoesParaTodosClientesUseCase;
import br.com.digio.vinho.usecase.ListarComprasOrdenadasUseCase;

@RestController
@RequestMapping("api")
public class CompraController {

	private final BuscarClientesFieisUseCase buscarClientesFieisUseCase;
	private final ListarComprasOrdenadasUseCase listarComprasOrdenadasUseCase;
	private final BuscarMaiorCompraPorAnoUseCase buscarMaiorCompraPorAnoUseCase;
	private final BuscarRecomendacoesParaTodosClientesUseCase buscarRecomendacoesParaTodosClientesUseCase;

	public CompraController(
			BuscarClientesFieisUseCase buscarClientesFieisUseCase,
			ListarComprasOrdenadasUseCase listarComprasOrdenadasUseCase,
			BuscarMaiorCompraPorAnoUseCase buscarMaiorCompraPorAnoUseCase,
			BuscarRecomendacoesParaTodosClientesUseCase buscarRecomendacoesParaTodosClientesUseCase) {
		this.listarComprasOrdenadasUseCase = listarComprasOrdenadasUseCase;
		this.buscarMaiorCompraPorAnoUseCase = buscarMaiorCompraPorAnoUseCase;
		this.buscarClientesFieisUseCase = buscarClientesFieisUseCase;
		this.buscarRecomendacoesParaTodosClientesUseCase = buscarRecomendacoesParaTodosClientesUseCase;
	}

	@GetMapping("compras")
	public ResponseEntity<List<CompraDetalhadaDto>> listarComprasOrdenadas() {
		var compraDetalhadaDto = listarComprasOrdenadasUseCase.execute();
		return ResponseEntity.ok(compraDetalhadaDto);
	}

	@GetMapping("maior-compra/{ano}")
	public ResponseEntity<CompraDetalhadaDto> buscarMaiorCompraPorAno(@PathVariable Integer ano) {
		return buscarMaiorCompraPorAnoUseCase.execute(ano).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("clientes-fieis")
	public ResponseEntity<List<ClienteCompraResumoDto>> buscarClientesFieis() {
		var top3 = buscarClientesFieisUseCase.execute();
		return ResponseEntity.ok(top3);
	}

	@GetMapping("recomendacao/cliente/tipo")
	public ResponseEntity<List<RecomendacaoDto>> recomendarTipoPorCliente() {
		var recomendacaoVinho = buscarRecomendacoesParaTodosClientesUseCase.execute();
		return ResponseEntity.ok(recomendacaoVinho);
	}

}
