package com.clienteservidor.animeserver.animeserver.report;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.checkerframework.checker.units.qual.s;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.clienteservidor.animeserver.animeserver.AnimeserverApplication;
import com.clienteservidor.animeserver.animeserver.ReportUtils;

import jakarta.servlet.ServletContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnimeserverApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TesteReport {

  @Autowired
  private ReportUtils reportUtils;

  @Test
  public void testImpressaoUsuario() throws Exception {

    HashMap params = new HashMap();
    // params.put("\\", 1);
    MockHttpServletRequest request = new MockHttpServletRequest();

    String local = reportUtils.gerarRelatorioStringPath(params, request.getServletContext(),
        "Relatorio-usuarios-modelo");

        System.out.println(local);

  }
}
