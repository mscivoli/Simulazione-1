package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.Event;
import it.polito.tdp.model.Evento;
import it.polito.tdp.model.Evento.TipoEvento;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public List<Integer> getAllDate(){
		String sql = "SELECT DISTINCT YEAR (reported_date) as anno " + 
				"FROM EVENTS";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				list.add(res.getInt("anno"));
			}
			conn.close();
			return list;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}
	
	public List<Distretto> getAllDistretti(){
		String sql = "SELECT DISTINCT district_id as id " + 
				"FROM EVENTS";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Distretto> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Distretto d = new Distretto(res.getInt("id"),0, 0);
				list.add(d);
			}
			conn.close();
			return list;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public void getAllCentriDistrettiPerAnno(Integer anno, Distretto ds){
		String sql = "SELECT AVG (geo_lon) as lon, AVG (geo_lat) as lat " + 
				"FROM EVENTS " + 
				"WHERE district_id = ? AND YEAR (reported_date) = ?";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, ds.getId());
			st.setInt(2, anno);
			
		
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				ds.setCoordinate(res.getDouble("lon"), res.getDouble("lat"));
			}
			conn.close();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	public List<Evento> listAllEventsPerGiorno(int anno, int mese, int giorno, Map<Integer, Distretto> mapId){
		String sql = "SELECT incident_id,offence_category_id,reported_date,district_id " + 
				"FROM EVENTS " + 
				"WHERE DAY(reported_date) = 12 AND MONTH(reported_date)=10 and YEAR(reported_date)=2015" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Evento> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Evento(TipoEvento.EVENTO_VERIFICATO,res.getLong("incident_id"),
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							mapId.get(res.getInt("district_id"))));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

}
























