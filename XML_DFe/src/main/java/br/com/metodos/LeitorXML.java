package br.com.metodos;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

public class LeitorXML<T> {
	
	/*public static void main(String[] args) {		
		String valor = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><enviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"3.10\"><idLote>1</idLote><indSinc>1</indSinc><NFe><infNFe Id=\"NFe43151000320033000119650060000000601000000605\" versao=\"3.10\"><ide><cUF>43</cUF><cNF>00000060</cNF><natOp>VENDA</natOp><indPag>2</indPag><mod>65</mod><serie>6</serie><nNF>60</nNF><dhEmi>2015-10-29T10:15:00-02:00</dhEmi><tpNF>1</tpNF><idDest>1</idDest><cMunFG>4304606</cMunFG><tpImp>4</tpImp><tpEmis>1</tpEmis><cDV>5</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><indFinal>1</indFinal><indPres>1</indPres><procEmi>0</procEmi><verProc>1.7.0.53</verProc></ide><emit><CNPJ>00320033000119</CNPJ><xNome>BRAZIL SISTEM SISTEMAS DE INF LTDA</xNome><xFant>BRAZIL SISTEM SISTEMAS DE INF LTDA</xFant><enderEmit><xLgr>RUA FELIPE SCHMIDT</xLgr><nro>894</nro><xBairro>INSS</xBairro><cMun>4304606</cMun><xMun>CANOAS</xMun><UF>RS</UF><CEP>88750000</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>4836290077</fone></enderEmit><IE>240259696</IE><CRT>3</CRT></emit><det nItem=\"1\"><prod><cProd>000013</cProd><cEAN/><xProd>NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xProd><NCM>27111910</NCM><CFOP>5656</CFOP><uCom>UN</uCom><qCom>2.0000</qCom><vUnCom>45.0000000000</vUnCom><vProd>90.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>2.0000</qTrib><vUnTrib>45.0000000000</vUnTrib><indTot>1</indTot><comb><cProdANP>210203001</cProdANP><UFCons>RS</UFCons></comb></prod><imposto><vTotTrib>19.73</vTotTrib><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>04</CST></PISNT></PIS><COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson><vBCST>0.00</vBCST><vST>0.00</vST><vProd>90.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>90.00</vNF><vTotTrib>19.73</vTotTrib></ICMSTot></total><transp><modFrete>9</modFrete></transp><pag><tPag>99</tPag><vPag>90.00</vPag></pag><infAdic><infCpl>MD5 PAF-ECF: 15D75F37E51E652EE59E7CACAEF9BF39;;;;;;;;;</infCpl></infAdic></infNFe><infNFeSupl><qrCode><![CDATA[https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?chNFe=43151000320033000119650060000000601000000605&nVersao=100&tpAmb=2&dhEmi=323031352d31302d32395431303a31353a30302d30323a3030&vNF=90.00&vICMS=0.00&digVal=49586f68394877315935454f6942676338757a496c5130346c41513d&cIdToken=000001&cHashQRCode=B4BE9CAB8FE5BFC4151F40CAC1B37EEF81555CFA]]></qrCode></infNFeSupl><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#NFe43151000320033000119650060000000601000000605\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>IXoh9Hw1Y5EOiBgc8uzIlQ04lAQ=</DigestValue></Reference></SignedInfo><SignatureValue>f7Y+3PLC5ieL4SWL3YoBIsxDtGSJcwU+M6Ji3Qs5l2F+Wk91N/B4qgMHK3NFAO4c30Vhu6DgKzBgI+3BiwUx/XSE5GvUElGvYX7mAW0Zahw7sKw94bR6gOTgb03ck/3d8bCSh9u9FApVah6IM8AtYHZgaxstjnNxPd1M9FM+5W/G81+htC29L+RSMXCfweaOzHHvEYzco2WmCnRYVDrX0aG9F3wrUUwe8d6ZmEfJTMOU3O4PLpTMhw+RlR+PQLIhbXLXQcqU5ma8WukVy38SNfbli26D3FPIPjjMSh82kQpU1q8sbKNyaJVPcKdV8rcPe2e/9nhW2Fuu7FRwvsO0aQ==</SignatureValue><KeyInfo><X509Data><X509Certificate>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</X509Certificate></X509Data></KeyInfo></Signature></NFe></enviNFe>";
		
		TEnviNFe envNfe =new LeitorXML<TEnviNFe>().carregaEntidade(valor, TEnviNFe.class);
		
		TNfeProc procNFe = new TNfeProc();
		procNFe.setNFe(envNfe.getNFe().get(0));
		procNFe.setVersao("3.10");
		procNFe.setProtNFe(new LeitorXML<TRetEnviNFe>().carregaEntidade("<retEnviNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><tpAmb>2</tpAmb><verAplic>RSnfce201508280903</verAplic><cStat>104</cStat><xMotivo>Lote processado</xMotivo><cUF>43</cUF><dhRecbto>2015-10-29T15:13:30-02:00</dhRecbto><protNFe versao=\"3.10\"><infProt><tpAmb>2</tpAmb><verAplic>RSnfce201508280903</verAplic><chNFe>43151000320033000119650060000000621000000626</chNFe><dhRecbto>2015-10-29T15:13:30-02:00</dhRecbto><nProt>143150001221695</nProt><digVal>cRVIsmp06fa435weh5sa0lHk/ok=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe></retEnviNFe>", TRetEnviNFe.class).getProtNFe());
		
		System.out.println(new LeitorXML<TNfeProc>().criaStrXML(procNFe,false));
		
		try {
			FileWriter writer = new FileWriter("C:/teste.xml", false);
			writer.write(new LeitorXML<TNfeProc>().criaStrXML(procNFe,false));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public T carregaEntidade(String StrXML, Class<T> clazz){
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
			return ((JAXBElement<T>) unmarshaller.unmarshal(new StreamSource(new StringReader(StrXML)),clazz)).getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * @param entidade
	 * @param formatado
	 * @return xml
	 */
	public String criaStrXML(T entidade, boolean formatado){
		try {
			final StringWriter writer = new StringWriter();
			Class<T> classe = (Class<T>) entidade.getClass();
			
			Marshaller marshaller = JAXBContext.newInstance(classe).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatado);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			QName qname = new QName("http://www.portalfiscal.inf.br/nfe", preparaNomeElemento(entidade.getClass().getSimpleName()));
			JAXBElement<T> elemento = new JAXBElement<T>(qname,classe,entidade); 
			marshaller.marshal(elemento, writer);
			
			return writer.toString().replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "")
				.replaceAll(":ns2", "")
				.replaceAll("ns2:", "")
				.replaceAll("<Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String preparaNomeElemento(String nomeClasse){
		return String.valueOf(nomeClasse.charAt(1)).toLowerCase()+nomeClasse.substring(2);
	}
}