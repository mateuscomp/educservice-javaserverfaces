package br.ufpb.dcx.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class QuestaoDissertativa implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	/**
     */
	@Lob
	@NotNull
	private String solucao;

	/**
     */
	@NotNull
	@ManyToOne
	private Questao questao;

	public static QuestaoDissertativa findQuestaoDissertativaByIdDeQuestao(
			Long idQuestao) {

		String query = "SELECT qd FROM QuestaoDissertativa AS qd WHERE qd.questao.id =:id";
		EntityManager em = Usuario.entityManager();
		TypedQuery<QuestaoDissertativa> q = em.createQuery(query,
				QuestaoDissertativa.class);
		q.setParameter("id", idQuestao);

		List<QuestaoDissertativa> resultList = q.getResultList();
		 if (resultList != null && !resultList.isEmpty()){
			 return resultList.get(0);
		 }
		 return null;
	}
}
