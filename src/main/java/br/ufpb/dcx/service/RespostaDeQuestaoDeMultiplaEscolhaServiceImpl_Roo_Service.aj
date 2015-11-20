// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.service;

import br.ufpb.dcx.model.RespostaDeQuestaoDeMultiplaEscolha;
import br.ufpb.dcx.service.RespostaDeQuestaoDeMultiplaEscolhaServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RespostaDeQuestaoDeMultiplaEscolhaServiceImpl_Roo_Service {
    
    declare @type: RespostaDeQuestaoDeMultiplaEscolhaServiceImpl: @Service;
    
    declare @type: RespostaDeQuestaoDeMultiplaEscolhaServiceImpl: @Transactional;
    
    public long RespostaDeQuestaoDeMultiplaEscolhaServiceImpl.countAllRespostaDeQuestaoDeMultiplaEscolhas() {
        return RespostaDeQuestaoDeMultiplaEscolha.countRespostaDeQuestaoDeMultiplaEscolhas();
    }
    
    public void RespostaDeQuestaoDeMultiplaEscolhaServiceImpl.deleteRespostaDeQuestaoDeMultiplaEscolha(RespostaDeQuestaoDeMultiplaEscolha respostaDeQuestaoDeMultiplaEscolha) {
        respostaDeQuestaoDeMultiplaEscolha.remove();
    }
    
    public RespostaDeQuestaoDeMultiplaEscolha RespostaDeQuestaoDeMultiplaEscolhaServiceImpl.findRespostaDeQuestaoDeMultiplaEscolha(Long id) {
        return RespostaDeQuestaoDeMultiplaEscolha.findRespostaDeQuestaoDeMultiplaEscolha(id);
    }
    
    public List<RespostaDeQuestaoDeMultiplaEscolha> RespostaDeQuestaoDeMultiplaEscolhaServiceImpl.findAllRespostaDeQuestaoDeMultiplaEscolhas() {
        return RespostaDeQuestaoDeMultiplaEscolha.findAllRespostaDeQuestaoDeMultiplaEscolhas();
    }
    
    public List<RespostaDeQuestaoDeMultiplaEscolha> RespostaDeQuestaoDeMultiplaEscolhaServiceImpl.findRespostaDeQuestaoDeMultiplaEscolhaEntries(int firstResult, int maxResults) {
        return RespostaDeQuestaoDeMultiplaEscolha.findRespostaDeQuestaoDeMultiplaEscolhaEntries(firstResult, maxResults);
    }
    
    public void RespostaDeQuestaoDeMultiplaEscolhaServiceImpl.saveRespostaDeQuestaoDeMultiplaEscolha(RespostaDeQuestaoDeMultiplaEscolha respostaDeQuestaoDeMultiplaEscolha) {
        respostaDeQuestaoDeMultiplaEscolha.persist();
    }
    
    public RespostaDeQuestaoDeMultiplaEscolha RespostaDeQuestaoDeMultiplaEscolhaServiceImpl.updateRespostaDeQuestaoDeMultiplaEscolha(RespostaDeQuestaoDeMultiplaEscolha respostaDeQuestaoDeMultiplaEscolha) {
        return respostaDeQuestaoDeMultiplaEscolha.merge();
    }
    
}
