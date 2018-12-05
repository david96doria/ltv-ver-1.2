package Application;

import java.sql.ResultSet;

import Database.DataBase;
import Database.SolicitudesTable;
import javafx.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class AdministracionController {

	public ListView<String> listSolicitudes = new ListView<String>();
	public ObservableList<String> SolicitudesItems = FXCollections.observableArrayList();
	public ChoiceBox<String> choiceAgentes = new ChoiceBox<String>();
	public ObservableList<String> choiceItems = FXCollections.observableArrayList();
	
	DataBase database = new DataBase();
	SolicitudesTable Solicitud = new SolicitudesTable();
	ResultSet miResultset;
	TitledPane tltpEntrantes;
	
	public void MostrarSolicitudesEntrantes() {
		
		System.out.println("Paso ");
		listSolicitudes.getItems().clear();
		choiceAgentes.getItems().clear();
		
		try {
			database.PrepareSolicitudes();
			miResultset = database.CheckSolicitudes("P");
			
			while(miResultset.next()) {
				
				SolicitudesItems.add(miResultset.getString("fecha")+" "+miResultset.getString("nombre")+"->"+
						miResultset.getString("destino"));
			} 
			
			listSolicitudes.setItems(SolicitudesItems);
			
		}catch (Exception e) {System.out.println(e.getMessage()); e.printStackTrace(); 
		System.out.println("Error al mostar solicitudes");} 
		
		
			try {
				database.PrepareUsuarios();
				miResultset = database.CheckAvailableUsuarios(true);
				while(miResultset.next()) {
					choiceItems.add(miResultset.getString("nombre"));
				}
				
				choiceAgentes.setItems(choiceItems);
				
			}catch (Exception e) {System.out.println(e.getMessage()); e.printStackTrace(); 
			System.out.println("Error al mostar agentes");} 
			
		
	}
	
	public void MostrarSolicitudesAsignadas() {
		
	}
	
	public void MostrarSolicitudesSeguimiento() {
		
	}
	
	public void btnAcercaDe() {
		
	}
}//end class
