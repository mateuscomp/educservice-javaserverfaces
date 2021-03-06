// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.model;

import br.ufpb.dcx.model.QuestaoDeMultiplaEscolha;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect QuestaoDeMultiplaEscolha_Roo_Jpa_ActiveRecord {
    
    public static final List<String> QuestaoDeMultiplaEscolha.fieldNames4OrderClauseFilter = java.util.Arrays.asList("serialVersionUID", "alternativas");
    
    public static long QuestaoDeMultiplaEscolha.countQuestaoDeMultiplaEscolhas() {
        return entityManager().createQuery("SELECT COUNT(o) FROM QuestaoDeMultiplaEscolha o", Long.class).getSingleResult();
    }
    
    public static List<QuestaoDeMultiplaEscolha> QuestaoDeMultiplaEscolha.findAllQuestaoDeMultiplaEscolhas() {
        return entityManager().createQuery("SELECT o FROM QuestaoDeMultiplaEscolha o", QuestaoDeMultiplaEscolha.class).getResultList();
    }
    
    public static List<QuestaoDeMultiplaEscolha> QuestaoDeMultiplaEscolha.findAllQuestaoDeMultiplaEscolhas(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM QuestaoDeMultiplaEscolha o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, QuestaoDeMultiplaEscolha.class).getResultList();
    }
    
    public static QuestaoDeMultiplaEscolha QuestaoDeMultiplaEscolha.findQuestaoDeMultiplaEscolha(Long id) {
        if (id == null) return null;
        return entityManager().find(QuestaoDeMultiplaEscolha.class, id);
    }
    
    public static List<QuestaoDeMultiplaEscolha> QuestaoDeMultiplaEscolha.findQuestaoDeMultiplaEscolhaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM QuestaoDeMultiplaEscolha o", QuestaoDeMultiplaEscolha.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<QuestaoDeMultiplaEscolha> QuestaoDeMultiplaEscolha.findQuestaoDeMultiplaEscolhaEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM QuestaoDeMultiplaEscolha o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, QuestaoDeMultiplaEscolha.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public QuestaoDeMultiplaEscolha QuestaoDeMultiplaEscolha.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        QuestaoDeMultiplaEscolha merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
