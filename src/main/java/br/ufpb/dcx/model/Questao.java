package br.ufpb.dcx.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Questao {

	protected String nome;

	@Lob
	@NotNull
	protected String enunciado;

	@NotNull
	@ManyToOne
	protected Usuario professor;

	public static List<Questao> pesquisarQuestoesByProfessor(
			Usuario professorParameter, String nomeParameter) {
		EntityManager em = Usuario.entityManager();

		TypedQuery<Questao> q = em
				.createQuery(
						"SELECT q FROM Questao AS q WHERE q.professor.id = :id AND q.nome LIKE :nome ORDER BY q.id ASC",
						Questao.class);
		q.setParameter("id", professorParameter.getId());
		q.setParameter("nome", "%" + nomeParameter + "%");

		return q.getResultList();
	}

	public static Long findMaxId() {
		EntityManager em = Usuario.entityManager();

		TypedQuery<Long> q = em.createQuery(
				"SELECT MAX(q.id) FROM Questao AS q ORDER BY q.id ASC", Long.class);

		return q.getSingleResult();
	}
	
	public String getNomeFormatado(){
		String formatacao = "";

		String[] nomeVetor = nome.split("");
		for (int i = 0; i < nomeVetor.length; i++) {
			if (i >= 50) {
				formatacao = formatacao + "...";
				break;
			} else {
				formatacao = formatacao + "" + nomeVetor[i];
			}
		}

		return formatacao;
	}
	
	public String getEnunciadoFormatado(){
		String formatacao = "";

		String[] enunciadoVetor = enunciado.split("");
		for (int i = 0; i < enunciadoVetor.length; i++) {
			if (i >= 50) {
				formatacao = formatacao + "...";
				break;
			} else {
				formatacao = formatacao + "" + enunciadoVetor[i];
			}
		}

		return formatacao;
	}
}
