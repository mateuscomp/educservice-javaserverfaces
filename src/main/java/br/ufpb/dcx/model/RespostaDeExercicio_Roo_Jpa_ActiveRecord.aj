// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.model;

import br.ufpb.dcx.model.RespostaDeExercicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RespostaDeExercicio_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager RespostaDeExercicio.entityManager;
    
    public static final List<String> RespostaDeExercicio.fieldNames4OrderClauseFilter = java.util.Arrays.asList("serialVersionUID", "aluno", "exercicio", "status");
    
    public static final EntityManager RespostaDeExercicio.entityManager() {
        EntityManager em = new RespostaDeExercicio().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RespostaDeExercicio.countRespostaDeExercicios() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RespostaDeExercicio o", Long.class).getSingleResult();
    }
    
    public static List<RespostaDeExercicio> RespostaDeExercicio.findAllRespostaDeExercicios() {
        return entityManager().createQuery("SELECT o FROM RespostaDeExercicio o", RespostaDeExercicio.class).getResultList();
    }
    
    public static List<RespostaDeExercicio> RespostaDeExercicio.findAllRespostaDeExercicios(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM RespostaDeExercicio o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, RespostaDeExercicio.class).getResultList();
    }
    
    public static RespostaDeExercicio RespostaDeExercicio.findRespostaDeExercicio(Long id) {
        if (id == null) return null;
        return entityManager().find(RespostaDeExercicio.class, id);
    }
    
    public static List<RespostaDeExercicio> RespostaDeExercicio.findRespostaDeExercicioEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RespostaDeExercicio o", RespostaDeExercicio.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<RespostaDeExercicio> RespostaDeExercicio.findRespostaDeExercicioEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM RespostaDeExercicio o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, RespostaDeExercicio.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void RespostaDeExercicio.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RespostaDeExercicio.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RespostaDeExercicio attached = RespostaDeExercicio.findRespostaDeExercicio(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RespostaDeExercicio.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RespostaDeExercicio.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public RespostaDeExercicio RespostaDeExercicio.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RespostaDeExercicio merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
