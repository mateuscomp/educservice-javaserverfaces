package br.ufpb.dcx.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoDissertativa;
import br.ufpb.dcx.model.Usuario;

@RooService(domainTypes = { br.ufpb.dcx.model.QuestaoDissertativa.class })
public interface QuestaoDissertativaService {

	QuestaoDissertativa pesquisarPorIdDeQuestao(Questao questao);

	void salvarQuestaoDissertativa(QuestaoDissertativa questaoDissertativa);

	List<QuestaoDissertativa> pesquisarQuestoesDissertativasPorProfessorAndNome(
			Usuario professor, String nomePesquisa);
}
