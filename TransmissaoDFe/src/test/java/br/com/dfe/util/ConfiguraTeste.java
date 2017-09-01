package br.com.dfe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import br.com.dfe.configuracao.DadosEmissor;
import br.com.dfe.utils.CertificadoUtils;

@Component
public class ConfiguraTeste {
	
	@Autowired
	private DadosEmissor dados;
	
	private CertificadoUtils utils;

	public void configuraDadosEmissor() {
		utils = new CertificadoUtils();
		try {
			utils.loadPFX(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"certs/cert_A1.pfx").getAbsolutePath(), "1234");
			dados.setCertificado(utils.getCertificado());
			dados.setPrivateKey(utils.getPrivateKey());
			dados.setUf("PR");
			dados.setUfCodigo("41");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void configuraConexaoSegura() {
		String pathCacerts = System.getProperty("user.dir")+"/Resources/NFeCacerts";
		dados.setPathCacerts(pathCacerts);
	}
}