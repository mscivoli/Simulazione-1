package it.polito.tdp.model;

import java.util.Map;

public class TestSimulatore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Simulatore sim = new Simulatore();
		Model m = new Model();
		
		m.creaGrafo(2015);
		Map<Integer, Distretto> mappa = m.getMappa();
		System.out.println("Verifica dei dati");
		for(Map.Entry<Integer, Distretto> mtemp : mappa.entrySet()) {
			System.out.println(mtemp.getKey()+" : "+mtemp.getValue());
		}
		
		System.out.println(m.getGrafo());
		
		sim.init(12, 10, 2015, mappa, 8, mappa.get(7), m.getGrafo());
		sim.run();

	}

}
