package br.ufpb.dcx.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class QuestaoDeMultiplaEscolha extends Questao{

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	/**
     *
     */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AlternativaDeQuestaoDeMultiplaEscolha> alternativas = new ArrayList<AlternativaDeQuestaoDeMultiplaEscolha>();

	public List<AlternativaDeQuestaoDeMultiplaEscolha> getAlternativas(){
		return this.alternativas;
	}

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
	
	public static List<QuestaoDeMultiplaEscolha> pesquisarQuestoesDeMultiplaEscolhaByProfessor(
			Usuario professorParameter, String nomeParameter) {
		EntityManager em = Usuario.entityManager();

		TypedQuery<QuestaoDeMultiplaEscolha> q = em
				.createQuery(
						"SELECT q FROM QuestaoDeMultiplaEscolha AS q WHERE q.professor.id = :id AND q.nome LIKE :nome ORDER BY q.id ASC",
						QuestaoDeMultiplaEscolha.class);
		q.setParameter("id", professorParameter.getId());
		q.setParameter("nome", "%" + nomeParameter + "%");

		return q.getResultList();
	}
}
