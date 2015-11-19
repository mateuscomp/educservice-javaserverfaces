package br.ufpb.dcx.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "resposta_de_questao_de_v_ou_f")
public class RespostaDeQuestaoDeVouF implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne
	private RespostaDeQuestaoDeUmExercicio respostaDeQuestaoDeUmExercicio;

	/**
     */
	@ManyToMany(cascade = CascadeType.ALL)
	private List<RespostaDeAlternativaVouF> respostasDeAlternativas = new ArrayList<RespostaDeAlternativaVouF>();
}
