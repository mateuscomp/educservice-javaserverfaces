package br.ufpb.dcx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class QuestaoDeMultiplaEscolha implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	/**
     *
     */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AlternativaDeQuestaoDeMultiplaEscolha> alternativas = new ArrayList<AlternativaDeQuestaoDeMultiplaEscolha>();

	/**
     */
	@NotNull
	@ManyToOne
	private Questao questao;

	public static QuestaoDeMultiplaEscolha findQuestaoDeMultiplaEscolhaByIdQuestao(
			Long idQuestao) {

		String query = "SELECT qme FROM QuestaoDeMultiplaEscolha AS qme WHERE qme.questao.id =:id";
		EntityManager em = Usuario.entityManager();
		TypedQuery<QuestaoDeMultiplaEscolha> q = em.createQuery(query,
				QuestaoDeMultiplaEscolha.class);
		q.setParameter("id", idQuestao);
		
		 List<QuestaoDeMultiplaEscolha> resultList = q.getResultList();
		 if (resultList != null && !resultList.isEmpty()){
			 return resultList.get(0);
		 }
		 return null;
	}
}
