package br.ufpb.dcx.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "questao_v_ou_f")
public class QuestaoVouF implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    @Size(min = 1)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AlternativaVouF> alternativas = new ArrayList<AlternativaVouF>();

    /**
     */
    @NotNull
    @ManyToOne
    private Questao questao;

	public static QuestaoVouF findQuestaoVouFByIdDeQuestao(Long id) {
		String query = "SELECT qvf FROM QuestaoVouF AS qvf WHERE qvf.questao.id =:id";
		EntityManager em = Usuario.entityManager();
		TypedQuery<QuestaoVouF> q = em.createQuery(query,
				QuestaoVouF.class);
		q.setParameter("id", id);
		
		List<QuestaoVouF> resultList = q.getResultList();
		 if (resultList != null && !resultList.isEmpty()){
			 return resultList.get(0);
		 }
		 return null;
	}
}
