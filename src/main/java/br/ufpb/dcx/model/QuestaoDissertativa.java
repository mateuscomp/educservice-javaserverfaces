package br.ufpb.dcx.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Lob;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class QuestaoDissertativa extends Questao{

	@Lob
	@NotNull
	private String solucao;

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
	
	public static List<QuestaoDissertativa> pesquisarQuestoesDeMultiplaEscolhaByProfessor(
			Usuario professorParameter, String nomeParameter) {
		EntityManager em = Usuario.entityManager();

		TypedQuery<QuestaoDissertativa> q = em
				.createQuery(
						"SELECT q FROM QuestaoDissertativa AS q WHERE q.professor.id = :id AND q.nome LIKE :nome ORDER BY q.id ASC",
						QuestaoDissertativa.class);
		q.setParameter("id", professorParameter.getId());
		q.setParameter("nome", "%" + nomeParameter + "%");

		return q.getResultList();
	}
}
