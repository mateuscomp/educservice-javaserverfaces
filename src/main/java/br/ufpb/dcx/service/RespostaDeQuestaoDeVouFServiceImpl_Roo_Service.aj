// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.service;

import br.ufpb.dcx.model.RespostaDeQuestaoDeVouF;
import br.ufpb.dcx.service.RespostaDeQuestaoDeVouFServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RespostaDeQuestaoDeVouFServiceImpl_Roo_Service {
    
    declare @type: RespostaDeQuestaoDeVouFServiceImpl: @Service;
    
    declare @type: RespostaDeQuestaoDeVouFServiceImpl: @Transactional;
    
    public long RespostaDeQuestaoDeVouFServiceImpl.countAllRespostaDeQuestaoDeVouFs() {
        return RespostaDeQuestaoDeVouF.countRespostaDeQuestaoDeVouFs();
    }
    
    public void RespostaDeQuestaoDeVouFServiceImpl.deleteRespostaDeQuestaoDeVouF(RespostaDeQuestaoDeVouF respostaDeQuestaoDeVouF) {
        respostaDeQuestaoDeVouF.remove();
    }
    
    public RespostaDeQuestaoDeVouF RespostaDeQuestaoDeVouFServiceImpl.findRespostaDeQuestaoDeVouF(Long id) {
        return RespostaDeQuestaoDeVouF.findRespostaDeQuestaoDeVouF(id);
    }
    
    public List<RespostaDeQuestaoDeVouF> RespostaDeQuestaoDeVouFServiceImpl.findAllRespostaDeQuestaoDeVouFs() {
        return RespostaDeQuestaoDeVouF.findAllRespostaDeQuestaoDeVouFs();
    }
    
    public List<RespostaDeQuestaoDeVouF> RespostaDeQuestaoDeVouFServiceImpl.findRespostaDeQuestaoDeVouFEntries(int firstResult, int maxResults) {
        return RespostaDeQuestaoDeVouF.findRespostaDeQuestaoDeVouFEntries(firstResult, maxResults);
    }
    
    public void RespostaDeQuestaoDeVouFServiceImpl.saveRespostaDeQuestaoDeVouF(RespostaDeQuestaoDeVouF respostaDeQuestaoDeVouF) {
        respostaDeQuestaoDeVouF.persist();
    }
    
    public RespostaDeQuestaoDeVouF RespostaDeQuestaoDeVouFServiceImpl.updateRespostaDeQuestaoDeVouF(RespostaDeQuestaoDeVouF respostaDeQuestaoDeVouF) {
        return respostaDeQuestaoDeVouF.merge();
    }
    
}
