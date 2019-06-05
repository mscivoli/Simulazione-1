package it.polito.tdp.model;

import java.time.LocalDateTime;

public class Evento implements Comparable<Evento>{
	
	public enum TipoEvento{
		EVENTO_VERIFICATO,
		POLIZIOTTO_CHIAMATO,
		LAVORO_FINITO;
	}
		
		private Distretto distretto;
		private long id;
		private String categoria;
		private LocalDateTime dataEvento;

		public Evento(TipoEvento tipo,long long1, String string, LocalDateTime l1, Distretto distretto) {
			this.dataEvento=l1;
			this.distretto=distretto;
			this.categoria=string;
			this.id=long1;
		}

	@Override
	public int compareTo(Evento o1) {
		// TODO Auto-generated method stub
		return this.dataEvento.compareTo(o1.dataEvento);
	}
	
	

}
