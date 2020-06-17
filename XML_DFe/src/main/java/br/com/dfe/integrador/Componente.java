package br.com.dfe.integrador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Componente {

    @XmlAttribute(name = "Nome")
    private String nome;

    @XmlElement(name = "Metodo")
    private Metodo metodo;
}
