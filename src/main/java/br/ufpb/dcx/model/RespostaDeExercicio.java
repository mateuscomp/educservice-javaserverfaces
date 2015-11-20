package br.ufpb.dcx.model;
import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RespostaDeExercicio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    @NotNull
    @ManyToOne
    private Usuario aluno;

    /**
     */
    @NotNull
    @ManyToOne
    private Exercicio exercicio;
    
    @Enumerated(EnumType.STRING)
	@NotNull
	private StatusRespostaDeExercicioEnum status;
}
