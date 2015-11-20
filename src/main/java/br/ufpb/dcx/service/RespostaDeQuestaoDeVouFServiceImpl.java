package br.ufpb.dcx.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpb.dcx.model.RespostaDeAlternativaVouF;
import br.ufpb.dcx.model.RespostaDeAlternativaVouFService;
import br.ufpb.dcx.model.RespostaDeQuestaoDeVouF;

public class RespostaDeQuestaoDeVouFServiceImpl implements
		RespostaDeQuestaoDeVouFService, Serializable {
	
	@Autowired
	private RespostaDeAlternativaVouFService respostaDeAlternativaVouFService;

	private static final long serialVersionUID = 1L;

	@Override
	public void salvarRespostaDeQuestaoDeVouF(
			RespostaDeQuestaoDeVouF respostaDeQuestaoDeVouF) {
		
		for(RespostaDeAlternativaVouF respostaDeAlternativa : respostaDeQuestaoDeVouF.getRespostasDeAlternativas()){
			respostaDeAlternativaVouFService.saveRespostaDeAlternativaVouF(respostaDeAlternativa);
		}
		
		this.saveRespostaDeQuestaoDeVouF(respostaDeQuestaoDeVouF);
	}
}
