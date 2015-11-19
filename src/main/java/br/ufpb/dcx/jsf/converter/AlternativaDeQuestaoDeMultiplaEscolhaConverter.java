package br.ufpb.dcx.jsf.converter;

import java.io.Serializable;

import javax.faces.convert.Converter;

import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

import br.ufpb.dcx.model.AlternativaDeQuestaoDeMultiplaEscolha;

@RooJsfConverter(entity = AlternativaDeQuestaoDeMultiplaEscolha.class)
public class AlternativaDeQuestaoDeMultiplaEscolhaConverter implements Converter, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
