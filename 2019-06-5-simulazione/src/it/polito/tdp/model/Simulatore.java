package it.polito.tdp.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.db.EventsDao;
import it.polito.tdp.model.Evento.TipoEvento;

public class Simulatore {

	
	private PriorityQueue<Evento> queue = new PriorityQueue<>() ;
	private int N;
	private int mese;
	private int giorno;
	private int anno;
	private List<Agente> agenti;
	EventsDao dao = new EventsDao();
	private Distretto distretto;
	private Graph<Distretto, DefaultWeightedEdge> grafo;
	Random rnd = new Random();
	private int casiGestitiMale = 0;
	
	
	public Simulatore() {
		this.agenti = new LinkedList<Agente>();
		
	}
	
	public void init(int giorno, int mese, int anno, Map<Integer, Distretto> mapId, int N, Distretto ds, Graph<Distretto, DefaultWeightedEdge> grafo) {
		
		queue.clear();
		this.anno=anno;
		this.giorno=giorno;
		this.mese=mese;
		this.N = N;
		this.distretto = ds;
		this.grafo = grafo;
		
		for(Evento e : dao.listAllEventsPerGiorno(anno, mese, giorno, mapId)) {
			queue.add(e);
		}
		
		for(int i=1; i<=N; i++) {
			agenti.add((new Agente(i, distretto, true)));
		}
	}
	
	public void run() {
		
		
		while(!queue.isEmpty()) {
			Evento e = queue.poll();
			System.out.println(e.toString());
			
			switch(e.getTipo()) {
			
			case EVENTO_VERIFICATO:
				double best = 0;
				Agente chiamato = null;
				
				for(Agente a : agenti) {
					
					if(a.isLibero() && e.getDistretto().equals(a.getPosizione()) && (chiamato == null || chiamato.getPosizione()!=e.getDistretto())) {
						chiamato = a;
						best = 0;
					}
					
					if(a.isLibero() && this.grafo.containsEdge(e.getDistretto(), a.getPosizione()) 
							&& (best==0 || this.grafo.getEdgeWeight(this.grafo.getEdge(e.getDistretto(), a.getPosizione()))<best)) {
						
						chiamato = a;
						best = this.grafo.getEdgeWeight(this.grafo.getEdge(e.getDistretto(), a.getPosizione()));
						
						
					}
				}
				if(chiamato!=null) {
					chiamato.setLibero(false);
					chiamato.setPosizione(e.getDistretto());
					
					int tempoSoluzione = 0;
					
					if(e.getCategoria().equals("all-other-crimes")) {
						tempoSoluzione+=(rnd.nextInt(2)+1)*60 + (best/60)*60;
					}
					else {
						tempoSoluzione+=2*60 + (best/60)*60;
					}
					
					Evento ev = new Evento(TipoEvento.LAVORO_FINITO,e.getId(),e.getCategoria(),e.getDataEvento().plusMinutes(tempoSoluzione),chiamato.getPosizione(), chiamato);
					queue.add(ev);
					if(Duration.between(e.getDataEvento().plusMinutes((long)((best/60)*60)),e.getDataEvento().plusMinutes(15)).toMinutes()>0) {
						this.casiGestitiMale++;
					}
				}else {
					System.out.println("Non ci sono agenti disponibili");
					this.casiGestitiMale++;
				}
					
				break;
				
				
			case LAVORO_FINITO:
				Agente ag = e.getIncaricato();
				
				ag.setLibero(true);
				
				break;
			}
		}
		
		System.out.println("Casi gestiti male = "+this.casiGestitiMale);
		for(Agente atemp : agenti) {
			System.out.println(atemp.toString());
		}
	}
}
























