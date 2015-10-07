package br.ufpb.dcx.model;

import java.util.LinkedList;
import java.util.List;

public enum PerfilEnum {
    ALUNO("Aluno"), PROFESSOR("Professor");
    
    private String nome;

	private PerfilEnum(String nome){
		this.nome = nome;
    }

	public static List<PerfilEnum> getList() {
		PerfilEnum[] values = values();
		
		List<PerfilEnum> perfis = new LinkedList<PerfilEnum>();
		for(int i = 0; i < values.length; i++){
			perfis.add(values[i]);
		}
		
		return perfis;
	}

	public String getNome() {
		return nome;
	}
}
