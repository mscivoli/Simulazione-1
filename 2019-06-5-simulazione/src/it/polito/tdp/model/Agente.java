package it.polito.tdp.model;

public class Agente {
	
	private int id;
	private Distretto posizione;
	private boolean libero;
	
	
	public Agente(int id, Distretto posizione, boolean libero) {
		super();
		this.id = id;
		this.posizione = posizione;
		this.libero = libero;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Distretto getPosizione() {
		return posizione;
	}


	public void setPosizione(Distretto posizione) {
		this.posizione = posizione;
	}


	public boolean isLibero() {
		return libero;
	}


	public void setLibero(boolean libero) {
		this.libero = libero;
	}


	@Override
	public String toString() {
		return "Agente [id=" + id + ", posizione=" + posizione + ", libero=" + libero + "]";
	}
	
	

}
