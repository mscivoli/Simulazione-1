package it.polito.tdp.model;

import java.time.LocalDateTime;

public class Evento implements Comparable<Evento>{
	
	public enum TipoEvento{
		EVENTO_VERIFICATO,
		LAVORO_FINITO
	}
		
		private Distretto distretto;
		private long id;
		private String categoria;
		private LocalDateTime dataEvento;
		private TipoEvento tipo;
		private Agente incaricato;

		public Evento(TipoEvento tipo,long long1, String string, LocalDateTime l1, Distretto distretto, Agente incaricato) {
			this.dataEvento=l1;
			this.distretto=distretto;
			this.categoria=string;
			this.id=long1;
			this.tipo = tipo;
			this.incaricato = incaricato;
		}
		
	

	public Agente getIncaricato() {
			return incaricato;
		}



		public void setIncaricato(Agente incaricato) {
			this.incaricato = incaricato;
		}



	public TipoEvento getTipo() {
			return tipo;
		}



		public void setTipo(TipoEvento tipo) {
			this.tipo = tipo;
		}



	public Distretto getDistretto() {
			return distretto;
		}



		public void setDistretto(Distretto distretto) {
			this.distretto = distretto;
		}



		public long getId() {
			return id;
		}



		public void setId(long id) {
			this.id = id;
		}



		public String getCategoria() {
			return categoria;
		}



		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}



		public LocalDateTime getDataEvento() {
			return dataEvento;
		}



		


		



		@Override
		public String toString() {
			return "Evento [distretto=" + distretto + ", id=" + id + ", tipo=" + tipo + ", incaricato=" + incaricato
					+ "]";
		}



		public void setDataEvento(LocalDateTime dataEvento) {
			this.dataEvento = dataEvento;
		}



	@Override
	public int compareTo(Evento o1) {
		// TODO Auto-generated method stub
		return this.dataEvento.compareTo(o1.dataEvento);
	}
	
	

}
