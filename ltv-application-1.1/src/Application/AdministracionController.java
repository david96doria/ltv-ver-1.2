package Application;

import java.sql.ResultSet;

import Database.DataBase;
import Database.SolicitudesTable;
import javafx.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class AdministracionController {

	ListView<String> listSolicitudes = new ListView<String>();
	ObservableList<String> SolicitudesItems = FXCollections.observableArrayList();
	DataBase database = new DataBase();
	SolicitudesTable Solicitud = new SolicitudesTable();
	ResultSet miResultset;
	TitledPane tltpEntrantes;
	
	public void MostrarSolicitudesEntrantes() {
		
		System.out.println("Paso 1");
		
		
		try {
			database.PrepareSolicitudes();
			miResultset = database.CheckAllSolicitudes("S");
			
			while(miResultset.next()) {
				Solicitud = new SolicitudesTable(miResultset.getString("fecha"), miResultset.getString("cliente"),
						miResultset.getString("solicitud"), miResultset.getString("estatus"), miResultset.getString("usuario"));
				System.out.println(Solicitud.getCLIENTE());
			}
			
		}catch (Exception e) {System.out.println(e.getMessage()); e.printStackTrace(); 
		System.out.println("Error al mostar solicitudes");} 
		
		
	}
	
	public void MostrarSolicitudesAsignadas() {
		
	}
	
	public void MostrarSolicitudesSeguimiento() {
		
	}
	
	public void btnAcercaDe() {
		
	}
}//end class
