// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.service;

import br.ufpb.dcx.model.Exercicio;
import br.ufpb.dcx.service.ExercicioService;
import java.util.List;

privileged aspect ExercicioService_Roo_Service {
    
    public abstract long ExercicioService.countAllExercicios();    
    public abstract void ExercicioService.deleteExercicio(Exercicio exercicio);    
    public abstract Exercicio ExercicioService.findExercicio(Long id);    
    public abstract List<Exercicio> ExercicioService.findAllExercicios();    
    public abstract List<Exercicio> ExercicioService.findExercicioEntries(int firstResult, int maxResults);    
    public abstract void ExercicioService.saveExercicio(Exercicio exercicio);    
    public abstract Exercicio ExercicioService.updateExercicio(Exercicio exercicio);    
}