package br.ufpb.dcx.model;
import java.io.Serializable;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RespostaDeQuestaoDissertativa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull
    @ManyToOne
    private RespostaDeQuestaoDeUmExercicio respostaDeQuestaoDeUmExercicio;

    /**
     */
    @NotNull
    private String resposta;
}
