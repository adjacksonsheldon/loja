package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "valor_total")
	@Builder.Default
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@Builder.Default
	private LocalDate data = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@Builder.Default
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	
	public void adicionarItem(ItemPedido item) {
		itens.add(item);
		item.setPedido(this);
		this.valorTotal = this.valorTotal.add(item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
	}
}
