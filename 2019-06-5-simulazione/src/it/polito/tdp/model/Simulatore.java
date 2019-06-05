package it.polito.tdp.model;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.db.EventsDao;

public class Simulatore {

	
	private PriorityQueue<Evento> queue = new PriorityQueue<>() ;
	private int N;
	private int mese;
	private int giorno;
	private int anno;
	private List<Agente> agenti;
	EventsDao dao = new EventsDao();
	
	
	public Simulatore() {
		
	}
	
	public void init(int giorno, int mese, int anno, Map<Integer, Distretto> mapId, int N) {
		this.anno=anno;
		this.giorno=giorno;
		this.mese=mese;
		this.N = N;
		
		for(Evento e : dao.listAllEventsPerGiorno(anno, mese, giorno, mapId)) {
			queue.add(e);
		}
		
		for(int i=0; i<N; i++) {
			
		}
		
	}
	
	public void run() {
		
		
		while(!queue.isEmpty()) {
			
		}
	}
}
