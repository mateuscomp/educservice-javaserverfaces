// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.service;

import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.service.QuestaoService;
import java.util.List;

privileged aspect QuestaoService_Roo_Service {
    
    public abstract long QuestaoService.countAllQuestaos();    
    public abstract void QuestaoService.deleteQuestao(Questao questao);    
    public abstract Questao QuestaoService.findQuestao(Long id);    
    public abstract List<Questao> QuestaoService.findAllQuestaos();    
    public abstract List<Questao> QuestaoService.findQuestaoEntries(int firstResult, int maxResults);    
    public abstract void QuestaoService.saveQuestao(Questao questao);    
    public abstract Questao QuestaoService.updateQuestao(Questao questao);    
}
