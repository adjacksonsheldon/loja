package br.com.alura.loja.modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RelatorioVendasDTO {
	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;
	
}
