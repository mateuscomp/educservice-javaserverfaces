package br.ufpb.dcx.model;

import java.io.Serializable;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AlternativaDeQuestaoDeMultiplaEscolha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     */
	@Lob
	@NotNull
	private String descricao;

	/**
     */
	private Boolean alternativaCorreta;

	public String getDescricaoFormatada() {
		String formatacao = "";

		String[] descricaoVetor = descricao.split("");
		for (int i = 0; i < descricaoVetor.length; i++) {
			if (i >= 50) {
				formatacao = formatacao + "...";
				break;
			} else {
				formatacao = formatacao + "" + descricaoVetor[i];
			}
		}

		return formatacao;
	}

}
