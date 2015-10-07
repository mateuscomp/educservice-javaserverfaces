package br.ufpb.dcx.model;

import java.io.Serializable;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "alternativa_v_ou_f")
public class AlternativaVouF implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     */
	@NotNull
	private String alternativa;

	/**
     */
	@NotNull
	private Boolean resposta;

	public String getAlternativaFormatada() {
		String formatacao = "";

		String[] alternativaVetor = alternativa.split("");
		for (int i = 0; i < alternativaVetor.length; i++) {
			if (i >= 50) {
				formatacao = formatacao + "...";
				break;
			} else {
				formatacao = formatacao + "" + alternativaVetor[i];
			}
		}

		return formatacao;
	}
}
