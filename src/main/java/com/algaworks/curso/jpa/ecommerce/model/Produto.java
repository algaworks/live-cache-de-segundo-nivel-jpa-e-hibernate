package com.algaworks.curso.jpa.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto extends EntidadeAbstrata {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nome;
	
	private String descricao;
	
	@Column(nullable = false)
	private BigDecimal preco;
	
	@ManyToMany
	@JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(
			name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias;
}
