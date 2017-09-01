package br.com.dfe.utils;

import java.io.StringReader;
import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.util.StAXParserConfiguration;
import org.xml.sax.InputSource;

public class ConverterUtils {

	public static OMElement toOMElement(String xml) {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		
		OMElement element = factory.getMetaFactory().createOMBuilder(factory, StAXParserConfiguration.NON_COALESCING , 
			new InputSource(new StringReader(xml))).getDocumentElement();
		
	    Iterator<?> children = element.getChildrenWithLocalName("NFe");  
	    while (children.hasNext()) {  
	        OMElement omElement = (OMElement) children.next();  
	        if (omElement != null && "NFe".equals(omElement.getLocalName())) {  
	            omElement.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe", null);  
	        }  
	    }  
		
		return element;
	}
}