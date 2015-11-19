package br.ufpb.dcx.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import br.ufpb.dcx.model.Exercicio;
import br.ufpb.dcx.model.Usuario;

@RooService(domainTypes = { br.ufpb.dcx.model.Exercicio.class })
public interface ExercicioService {

	List<Exercicio> pesquisarExerciciosComQuestoesDeProfessor(Usuario profesor);

	void salvar(Exercicio exercicio) throws EducServiceException;

	List<Exercicio> pesquisarExerciciosPorNomeOuEmailDeProfessor(
			String apelidoDoProfessorPesquisa, String emailDoProfessorPesquisa);
}
