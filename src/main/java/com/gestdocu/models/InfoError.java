package com.gestdocu.models;

import java.util.HashMap;

import com.gestdocu.interfaces.InterfaceTablasValor;

public class InfoError extends HashMap<Object, Object> {

	private static final long serialVersionUID = -9038030280969051643L;

	transient InterfaceTablasValor iTV;

	public InfoError(InterfaceTablasValor interfaceTV) {
		super();
		iTV = interfaceTV;
	}
	
	  /**
     * Método que recibe el el código de un error y guarda 
     * el código y la descripción del error indicado como un error genérico
     *
     * @param codError: String con el código del error producido
     */
    public void addError(String codError) {
    	
    	
    }

}
