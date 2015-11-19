package br.ufpb.dcx.model;
import java.io.Serializable;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "resposta_de_alternativa_v_ou_f")
public class RespostaDeAlternativaVouF implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    @NotNull
    @ManyToOne
    private AlternativaVouF alternativaVouF;
    
    @NotNull
    private Boolean resposta;
}
