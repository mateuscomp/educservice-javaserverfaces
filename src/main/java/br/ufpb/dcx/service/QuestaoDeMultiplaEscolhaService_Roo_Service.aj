// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.service;

import br.ufpb.dcx.model.QuestaoDeMultiplaEscolha;
import br.ufpb.dcx.service.QuestaoDeMultiplaEscolhaService;
import java.util.List;

privileged aspect QuestaoDeMultiplaEscolhaService_Roo_Service {
    
    public abstract long QuestaoDeMultiplaEscolhaService.countAllQuestaoDeMultiplaEscolhas();    
    public abstract void QuestaoDeMultiplaEscolhaService.deleteQuestaoDeMultiplaEscolha(QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha);    
    public abstract QuestaoDeMultiplaEscolha QuestaoDeMultiplaEscolhaService.findQuestaoDeMultiplaEscolha(Long id);    
    public abstract List<QuestaoDeMultiplaEscolha> QuestaoDeMultiplaEscolhaService.findAllQuestaoDeMultiplaEscolhas();    
    public abstract List<QuestaoDeMultiplaEscolha> QuestaoDeMultiplaEscolhaService.findQuestaoDeMultiplaEscolhaEntries(int firstResult, int maxResults);    
    public abstract void QuestaoDeMultiplaEscolhaService.saveQuestaoDeMultiplaEscolha(QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha);    
    public abstract QuestaoDeMultiplaEscolha QuestaoDeMultiplaEscolhaService.updateQuestaoDeMultiplaEscolha(QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha);    
}
