// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.service;

import br.ufpb.dcx.model.RespostaDeQuestaoDeUmExercicio;
import br.ufpb.dcx.service.RespostaDeQuestaoDeUmExercicioServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RespostaDeQuestaoDeUmExercicioServiceImpl_Roo_Service {
    
    declare @type: RespostaDeQuestaoDeUmExercicioServiceImpl: @Service;
    
    declare @type: RespostaDeQuestaoDeUmExercicioServiceImpl: @Transactional;
    
    public long RespostaDeQuestaoDeUmExercicioServiceImpl.countAllRespostaDeQuestaoDeUmExercicios() {
        return RespostaDeQuestaoDeUmExercicio.countRespostaDeQuestaoDeUmExercicios();
    }
    
    public void RespostaDeQuestaoDeUmExercicioServiceImpl.deleteRespostaDeQuestaoDeUmExercicio(RespostaDeQuestaoDeUmExercicio respostaDeQuestaoDeUmExercicio) {
        respostaDeQuestaoDeUmExercicio.remove();
    }
    
    public RespostaDeQuestaoDeUmExercicio RespostaDeQuestaoDeUmExercicioServiceImpl.findRespostaDeQuestaoDeUmExercicio(Long id) {
        return RespostaDeQuestaoDeUmExercicio.findRespostaDeQuestaoDeUmExercicio(id);
    }
    
    public List<RespostaDeQuestaoDeUmExercicio> RespostaDeQuestaoDeUmExercicioServiceImpl.findAllRespostaDeQuestaoDeUmExercicios() {
        return RespostaDeQuestaoDeUmExercicio.findAllRespostaDeQuestaoDeUmExercicios();
    }
    
    public List<RespostaDeQuestaoDeUmExercicio> RespostaDeQuestaoDeUmExercicioServiceImpl.findRespostaDeQuestaoDeUmExercicioEntries(int firstResult, int maxResults) {
        return RespostaDeQuestaoDeUmExercicio.findRespostaDeQuestaoDeUmExercicioEntries(firstResult, maxResults);
    }
    
    public void RespostaDeQuestaoDeUmExercicioServiceImpl.saveRespostaDeQuestaoDeUmExercicio(RespostaDeQuestaoDeUmExercicio respostaDeQuestaoDeUmExercicio) {
        respostaDeQuestaoDeUmExercicio.persist();
    }
    
    public RespostaDeQuestaoDeUmExercicio RespostaDeQuestaoDeUmExercicioServiceImpl.updateRespostaDeQuestaoDeUmExercicio(RespostaDeQuestaoDeUmExercicio respostaDeQuestaoDeUmExercicio) {
        return respostaDeQuestaoDeUmExercicio.merge();
    }
    
}
