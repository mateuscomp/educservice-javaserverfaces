package br.ufpb.dcx.model;

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

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "questao_v_ou_f")
public class QuestaoVouF extends Questao {

	/**
     */
	@Size(min = 1)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AlternativaVouF> alternativas = new ArrayList<AlternativaVouF>();

	public static QuestaoVouF findQuestaoVouFByIdDeQuestao(Long id) {
		String query = "SELECT qvf FROM QuestaoVouF AS qvf WHERE qvf.questao.id =:id";
		EntityManager em = Usuario.entityManager();
		TypedQuery<QuestaoVouF> q = em.createQuery(query, QuestaoVouF.class);
		q.setParameter("id", id);

		List<QuestaoVouF> resultList = q.getResultList();
		if (resultList != null && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	public static List<QuestaoVouF> pesquisarQuestoesDeMultiplaEscolhaByProfessor(
			Usuario professorParameter, String nomeParameter) {

		EntityManager em = Usuario.entityManager();

		TypedQuery<QuestaoVouF> q = em
				.createQuery(
						"SELECT qvf FROM QuestaoVouF AS qvf WHERE qvf.professor.id = :id AND qvf.nome LIKE :nome ORDER BY qvf.id ASC",
						QuestaoVouF.class);
		q.setParameter("id", professorParameter.getId());
		q.setParameter("nome", "%" + nomeParameter + "%");

		return q.getResultList();
	}
}
