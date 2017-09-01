package br.com.dfe.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.protocol.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dfe.api.PreparaConexaoSegura;
import br.com.dfe.configuracao.DadosEmissor;
import br.com.dfe.utils.BuildCacerts;
import br.com.dfe.utils.SocketFactoryDinamico;

@Component
public class PreparaConexaoSeguraImpl implements PreparaConexaoSegura {
	
	private String pathCacerts;
	private BuildCacerts buildCacerts;
	private SocketFactoryDinamico socketDinamico;
	private URL url;
	
	@Autowired
	private DadosEmissor dados;
	
	public PreparaConexaoSeguraImpl() {
		super();
	}

	@Override
	public void preparaConexaoSegura() throws Exception {
		gerarCacerts();
		registraSocketDinamico();
	}

	public void gerarCacerts() throws MalformedURLException {
		if (buildCacerts == null) {
			buildCacerts = new BuildCacerts(pathCacerts);
		}
		
		if (this.url != null) {
			buildCacerts.geraCacert(url);
		}
	}
	
	public void registraSocketDinamico() {
		if (socketDinamico == null) {
			socketDinamico = new SocketFactoryDinamico(dados.getCertificado(), dados.getPrivateKey());
			socketDinamico.setFileCacerts(pathCacerts);
			
			Protocol protocol = new Protocol("https", socketDinamico, 443);
			Protocol.registerProtocol("https", protocol);
		}
	}

	@Override
	public void setUrl(String url) throws MalformedURLException {
		this.url = new URL(url);
		
	}

	@Override
	public void setPathCacerts(String pathCacerts) {
		this.pathCacerts = pathCacerts;
	}
}