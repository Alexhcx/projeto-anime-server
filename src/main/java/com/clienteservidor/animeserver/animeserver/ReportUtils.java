package com.clienteservidor.animeserver.animeserver;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtils {
  private static final String FOLDER_RELATORIOS = "Relatorios";
  private static final String PARAMETRO_PASTA_REPORT = "PARAMETRO_PASTA_REPORT";
  private static final String SEPARATOR = File.separator;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
  public String gerarRelatorioStringPath(HashMap parametroRelatorio, ServletContext servletContext, String nomeReport)
      throws Exception {

    String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
    parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARATOR);

    @SuppressWarnings("null")
    JasperPrint impressoraJasper = JasperFillManager
        .fillReport(caminhoPastaImagensReport + SEPARATOR + nomeReport + ".jasper", parametroRelatorio,
            jdbcTemplate.getDataSource().getConnection());

    String caminhoArquivoRelatorio = servletContext.getRealPath("") + SEPARATOR + "relatorio.pdf";

    JasperExportManager.exportReportToPdfFile(impressoraJasper, caminhoArquivoRelatorio);

    return caminhoArquivoRelatorio;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public String gerarRelatorioStringPath(HashMap parametroRelatorio, ServletContext servletContext, String nomeReport,
      List listaDados) throws Exception {

    String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
    parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARATOR);

    JasperPrint impressoraJasper = JasperFillManager
        .fillReport(caminhoPastaImagensReport + SEPARATOR + nomeReport + ".jasper",
            parametroRelatorio, new JRBeanCollectionDataSource(listaDados));

    String caminhoArquivoRelatorio = servletContext.getRealPath("") + SEPARATOR + "relatorio.pdf";

    JasperExportManager.exportReportToPdfFile(impressoraJasper, caminhoArquivoRelatorio);

    return caminhoArquivoRelatorio;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public byte[] gerarRelatorioByte(HashMap parametroRelatorio, ServletContext servletContext, String nomeReport)
      throws Exception {

    String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
    parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARATOR);

    JasperPrint impressoraJasper = JasperFillManager
        .fillReport(caminhoPastaImagensReport + SEPARATOR + nomeReport + ".jasper", parametroRelatorio,
            jdbcTemplate.getDataSource().getConnection());

    return JasperExportManager.exportReportToPdf(impressoraJasper);
  }

}
