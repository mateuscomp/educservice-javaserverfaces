// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.model;

import br.ufpb.dcx.model.Email;

privileged aspect Email_Roo_JavaBean {
    
    public String Email.getTexto() {
        return this.texto;
    }
    
    public void Email.setTexto(String texto) {
        this.texto = texto;
    }
    
    public String Email.getRemetente() {
        return this.remetente;
    }
    
    public void Email.setRemetente(String remetente) {
        this.remetente = remetente;
    }
    
}
