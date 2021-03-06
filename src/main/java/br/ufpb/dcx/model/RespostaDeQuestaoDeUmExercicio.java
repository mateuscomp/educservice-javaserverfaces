package br.ufpb.dcx.model;
import java.io.Serializable;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RespostaDeQuestaoDeUmExercicio implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     */
    @NotNull
    @ManyToOne
    private RespostaDeExercicio respostaDeExercicio;

    /**
     */
    @NotNull
    @ManyToOne
    private Questao questao;
}
