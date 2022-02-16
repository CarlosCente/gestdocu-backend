package com.gestdocu.models;

import java.util.ArrayList;
import java.util.List;

import com.gestdocu.interfaces.InterfaceTablasValor;

public class InfoProceso {

	transient InterfaceTablasValor iTV;
	
	public String fechaServidor;

    public String entidad;

    public String proceso;

    public List datos;

    public InfoError mapaMsgErrores;

    public InfoError mapaMsgAdvertencias;

    public InfoError mapaMsgInformativos;
    
    public InfoProceso(InterfaceTablasValor interTv) {
    	fechaServidor = "";
    	this.entidad = "";
    	this.proceso = "";
    	this.datos = new ArrayList();
    	this.mapaMsgErrores = null;
    	this.mapaMsgAdvertencias = null;
    	this.mapaMsgInformativos = null;
     }

	public InterfaceTablasValor getiTV() {
		return iTV;
	}

	public void setiTV(InterfaceTablasValor iTV) {
		this.iTV = iTV;
	}

	public String getFechaServidor() {
		return fechaServidor;
	}

	public void setFechaServidor(String fechaServidor) {
		this.fechaServidor = fechaServidor;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public List getDatos() {
		return datos;
	}

	public void setDatos(List datos) {
		this.datos = datos;
	}

	public InfoError getMapaMsgErrores() {
		return mapaMsgErrores;
	}

	public void setMapaMsgErrores(InfoError mapaMsgErrores) {
		this.mapaMsgErrores = mapaMsgErrores;
	}

	public InfoError getMapaMsgAdvertencias() {
		return mapaMsgAdvertencias;
	}

	public void setMapaMsgAdvertencias(InfoError mapaMsgAdvertencias) {
		this.mapaMsgAdvertencias = mapaMsgAdvertencias;
	}

	public InfoError getMapaMsgInformativos() {
		return mapaMsgInformativos;
	}

	public void setMapaMsgInformativos(InfoError mapaMsgInformativos) {
		this.mapaMsgInformativos = mapaMsgInformativos;
	}
	
    
}
