package br.ufpb.dcx.service;

import java.io.Serializable;
import java.util.List;

import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoDissertativa;
import br.ufpb.dcx.model.Usuario;

public class QuestaoDissertativaServiceImpl implements
		QuestaoDissertativaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public QuestaoDissertativa pesquisarPorIdDeQuestao(Questao questao) {
		return QuestaoDissertativa.findQuestaoDissertativaByIdDeQuestao(questao.getId());
	}

	@Override
	public void salvarQuestaoDissertativa(
			QuestaoDissertativa questaoDissertativa) {
		
		if(questaoDissertativa.getId() == null){
			this.saveQuestaoDissertativa(questaoDissertativa);
		} else{
			this.updateQuestaoDissertativa(questaoDissertativa);
		}
	}

	@Override
	public List<QuestaoDissertativa> pesquisarQuestoesDissertativasPorProfessorAndNome(
			Usuario professor, String nomePesquisa) {
		return QuestaoDissertativa.pesquisarQuestoesDeMultiplaEscolhaByProfessor(professor, nomePesquisa);
	}
}
