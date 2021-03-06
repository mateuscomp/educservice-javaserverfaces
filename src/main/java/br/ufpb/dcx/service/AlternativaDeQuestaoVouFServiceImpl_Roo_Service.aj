// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.service;

import br.ufpb.dcx.model.AlternativaVouF;
import br.ufpb.dcx.service.AlternativaDeQuestaoVouFServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AlternativaDeQuestaoVouFServiceImpl_Roo_Service {
    
    declare @type: AlternativaDeQuestaoVouFServiceImpl: @Service;
    
    declare @type: AlternativaDeQuestaoVouFServiceImpl: @Transactional;
    
    public long AlternativaDeQuestaoVouFServiceImpl.countAllAlternativaVouFs() {
        return AlternativaVouF.countAlternativaVouFs();
    }
    
    public void AlternativaDeQuestaoVouFServiceImpl.deleteAlternativaVouF(AlternativaVouF alternativaVouF) {
        alternativaVouF.remove();
    }
    
    public AlternativaVouF AlternativaDeQuestaoVouFServiceImpl.findAlternativaVouF(Long id) {
        return AlternativaVouF.findAlternativaVouF(id);
    }
    
    public List<AlternativaVouF> AlternativaDeQuestaoVouFServiceImpl.findAllAlternativaVouFs() {
        return AlternativaVouF.findAllAlternativaVouFs();
    }
    
    public List<AlternativaVouF> AlternativaDeQuestaoVouFServiceImpl.findAlternativaVouFEntries(int firstResult, int maxResults) {
        return AlternativaVouF.findAlternativaVouFEntries(firstResult, maxResults);
    }
    
    public void AlternativaDeQuestaoVouFServiceImpl.saveAlternativaVouF(AlternativaVouF alternativaVouF) {
        alternativaVouF.persist();
    }
    
    public AlternativaVouF AlternativaDeQuestaoVouFServiceImpl.updateAlternativaVouF(AlternativaVouF alternativaVouF) {
        return alternativaVouF.merge();
    }
    
}
