package com.clienteservidor.animeserver.animeserver.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clienteservidor.animeserver.animeserver.dto.ItemEstoqueDTO;
import com.clienteservidor.animeserver.animeserver.dto.ProdutosEstoqueDTO;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.services.ProductService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/anime/api/produtos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> cadastrarProduto(@RequestBody ProductModel produto,
            @RequestParam Long fornecedorId) {
        try {
            ProductModel createdProduct = productService.cadastrarProduto(produto, fornecedorId);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> atualizarProduto(@PathVariable Long id, @RequestBody ProductModel produto) {
        try {
            produto.setId(id);
            ProductModel updatedProduct = productService.atualizarProduto(produto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/estoque")
    @Transactional
    public ResponseEntity<?> atualizarEstoque(@RequestBody ProdutosEstoqueDTO produtosEstoqueDTO) {
        try {
            for (ItemEstoqueDTO item : produtosEstoqueDTO.itens()) {
                ProductModel produto = productService.buscarProdutoPorId(item.productId());
                if (produto == null) {
                    return new ResponseEntity<>("Produto não encontrado: " + item.productId(), HttpStatus.NOT_FOUND);
                }

                String qtdEstoque = produto.getQtdEstoque();
                if (qtdEstoque == null || qtdEstoque.trim().isEmpty()) {
                    return new ResponseEntity<>("Estoque inválido para o produto: " + produto.getNome(),
                            HttpStatus.BAD_REQUEST);
                }

                int estoqueAtual;
                try {
                    estoqueAtual = Integer.parseInt(qtdEstoque.trim());
                } catch (NumberFormatException e) {
                    return new ResponseEntity<>("Formato inválido no estoque do produto: " + produto.getNome(),
                            HttpStatus.BAD_REQUEST);
                }

                int novaQtdEstoque = estoqueAtual - item.quantidade();
                if (novaQtdEstoque < 0) {
                    return new ResponseEntity<>("Estoque insuficiente para o produto: " + produto.getNome(),
                            HttpStatus.BAD_REQUEST);
                }

                produto.setQtdEstoque(String.valueOf(novaQtdEstoque));
                productService.atualizarProduto(produto);

                System.out
                        .println("Produto atualizado: " + produto.getNome() + ", Estoque restante: " + novaQtdEstoque);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro interno: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            productService.deletarProduto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<ProductModel> mostrarTodosOsProdutos() {
        return productService.mostrarTodosOsProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> buscarProdutoPorId(@PathVariable Long id) {
        try {
            ProductModel product = productService.buscarProdutoPorId(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
