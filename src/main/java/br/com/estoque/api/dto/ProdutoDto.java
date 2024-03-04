package br.com.estoque.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDto(@NotNull @NotBlank String nome,@NotNull Double preco,@NotNull Integer quantidade,@NotNull Long idFabricante,@NotNull Long idModelo) {

}
