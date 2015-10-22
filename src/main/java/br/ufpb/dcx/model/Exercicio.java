package br.ufpb.dcx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Exercicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String nome;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Questao> questoes = new ArrayList<Questao>();

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataDeVencimento;

	public String getNomeFormatado() {
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

	public String getDataDeVencimentoFormatada() {
		String dataFormatada = "";

		if (this.dataDeVencimento != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataDeVencimento);

			int dia = calendar.get(Calendar.DATE);
			int mes = calendar.get(Calendar.MONTH);
			int ano = calendar.get(Calendar.YEAR);


			dataFormatada = dia + "/" + mes + "/" + ano;
		}

		return dataFormatada;
	}

	public static List<Exercicio> findExerciciosWithQuestionsOfTeacher(
			Usuario professor) {

		EntityManager em = Usuario.entityManager();
		TypedQuery<Exercicio> q = em
				.createQuery(
						"SELECT DISTINCT(e) FROM Exercicio AS e WHERE e.id IN("
								+ "SELECT q FROM Questao AS q WHERE q.professor.id =:id) ORDER BY e.dataDeVencimento ASC",
						Exercicio.class);

		q.setParameter("id", professor.getId());

		return q.getResultList();
	}
}
