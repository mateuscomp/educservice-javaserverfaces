package br.ufpb.dcx.service;

import java.io.Serializable;
import java.util.List;

import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.Usuario;

public class QuestaoServiceImpl implements QuestaoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Questao> findQuestoesByProfessorEqualsAndNomeLike(Usuario professor, String nome) {
		return Questao.pesquisarQuestoesByProfessor(professor, nome);
	}

	@Override
	public Long findMaxId() {
		return Questao.findMaxId();
	}

	@Override
	public void salvarQuestao(Questao questao) {
		if(questao.getId() == null){
			this.saveQuestao(questao);
		} else {
			this.updateQuestao(questao);
		}
	}
}
