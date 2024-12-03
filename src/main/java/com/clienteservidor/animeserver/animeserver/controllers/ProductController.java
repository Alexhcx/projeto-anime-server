package com.clienteservidor.animeserver.animeserver.controllers;

import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
