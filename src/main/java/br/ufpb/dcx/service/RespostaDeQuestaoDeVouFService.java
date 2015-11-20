package br.ufpb.dcx.service;
import org.springframework.roo.addon.layers.service.RooService;

import br.ufpb.dcx.model.RespostaDeQuestaoDeVouF;

@RooService(domainTypes = { br.ufpb.dcx.model.RespostaDeQuestaoDeVouF.class })
public interface RespostaDeQuestaoDeVouFService {

	void salvarRespostaDeQuestaoDeVouF(
			RespostaDeQuestaoDeVouF respostaDeQuestaoDeVouF);
}
