package br.com.estoque.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.api.dto.ProdutoDto;
import br.com.estoque.api.filter.Filter;
import br.com.estoque.api.model.Produto;
import br.com.estoque.api.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	/*
	 * Response - pegar dados da minha classe
	 * Request - trazer dados para minha classe
	 * */
	@PostMapping("/salvar")
	public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoDto produtoDto){
		var produto = new Produto();
		
		BeanUtils.copyProperties(produtoDto, produto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findById(id));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Produto>>findByProdutos(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Object> updateProduto(@PathVariable("id") Long id, @RequestBody ProdutoDto produtoDto){
		var pesquisaId = produtoRepository.findById(id).orElse(null);
		
		if(pesquisaId == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado em nosso sistema !");
		}else {
			Filter.copyNonNullProperties(produtoDto, pesquisaId);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoRepository.save(pesquisaId));
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deleteProduto(@PathVariable("id") Long id){
		Optional<Produto> pesquisaId = produtoRepository.findById(id);
		
		if(pesquisaId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado em nosso sistema !");
		}else {
			produtoRepository.delete(pesquisaId.get());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produto Deletado com sucesso !");
		}
	}
}
