package com.clienteservidor.animeserver.animeserver.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clienteservidor.animeserver.animeserver.ReportUtils;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/anime/api/relatorios")
public class ReportController {

    @Autowired
    private ReportUtils reportUtils;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/gerar") // http://localhost:8080/anime/api/relatorios/gerar?nomeRelatorio=Relatorio-produtos-modelo&tipoRelatorio=pdf
    public ResponseEntity<byte[]> gerarRelatorio(
            @RequestParam String nomeRelatorio,
            @RequestParam(required = false) String tipoRelatorio
    ) throws Exception {

        HashMap<String, Object> parametros = new HashMap<>();

        byte[] relatorioBytes = null;
        switch (tipoRelatorio != null ? tipoRelatorio.toLowerCase() : "pdf") {
            case "pdf" -> relatorioBytes = reportUtils.gerarRelatorioByte(parametros, servletContext, nomeRelatorio);
            default -> throw new IllegalArgumentException("Tipo de relatório inválido: " + tipoRelatorio);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF); 
        headers.setContentDispositionFormData("inline", nomeRelatorio + ".pdf");

        return new ResponseEntity<>(relatorioBytes, headers, HttpStatus.OK);
    }
}
