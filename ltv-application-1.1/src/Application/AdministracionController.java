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

	public ListView<String> listSolicitudes = new ListView<String>();
	public ObservableList<String> SolicitudesItems = FXCollections.observableArrayList();
	DataBase database = new DataBase();
	SolicitudesTable Solicitud = new SolicitudesTable();
	ResultSet miResultset;
	TitledPane tltpEntrantes;
	
	public void MostrarSolicitudesEntrantes() {
		
		System.out.println("Paso ");
		listSolicitudes.getItems().clear();
		
		try {
			database.PrepareSolicitudes();
			miResultset = database.CheckSolicitudes("P");
			
			while(miResultset.next()) {
				
				SolicitudesItems.add(miResultset.getString("fecha"));
			} 
			
			listSolicitudes.setItems(SolicitudesItems);
			
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
