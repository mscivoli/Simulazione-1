package it.polito.tdp.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	EventsDao dao = new EventsDao();
	private List<Distretto> distretti;

	private Graph<Distretto, DefaultWeightedEdge> grafo;
	private Map<Integer, Distretto> mapId;
	
	public void creaGrafo(int anno, int dsId) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.distretti = dao.getAllDistretti();
		
		this.mapId = new HashMap<Integer, Distretto>();
		for(Distretto d : distretti) {
			mapId.put(d.getId(), d);
		}
		
		for(Distretto d : distretti) {
			dao.getAllCentriDistrettiPerAnno(anno, d);
		}
		
		Graphs.addAllVertices(this.grafo, this.distretti);
		
		for (Distretto partenza : this.grafo.vertexSet()) {
			List<Distretto> arrivi = this.distretti;

			for (Distretto arrivo : arrivi) {
				if(arrivo.getId() > partenza.getId()) {
					double peso = LatLngTool.distance(partenza.getLatlng(), arrivo.getLatlng(), LengthUnit.KILOMETER);
					Graphs.addEdge(grafo, partenza, arrivo, peso);
				}
			}
		}
		
		System.out.println("grafo creato");
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			System.out.println(grafo.getEdgeWeight(e));
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	

	public List<Integer> getAnni() {
		
		List<Integer> ltemp = new LinkedList<Integer>();
		ltemp = dao.getAllDate();
		return ltemp;
	}
	
}
