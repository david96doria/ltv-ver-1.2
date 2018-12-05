package Application;

import java.sql.ResultSet;
import Database.DataBase;
import Database.SolicitudesTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Label;

public class AdministracionController {

	public ListView<String> listSolicitudes = new ListView<String>();
	public ObservableList<String> SolicitudesItems = FXCollections.observableArrayList();
	public ChoiceBox<String> choiceAgentes = new ChoiceBox<String>();
	public ObservableList<String> choiceItems = FXCollections.observableArrayList();
	public Label lblFecha; public Label lblCliente; public Label lblSolicitud;
	
	Alert alert = new Alert(AlertType.INFORMATION);
	DataBase database = new DataBase();
	SolicitudesTable Solicitud = new SolicitudesTable();
	ResultSet miResultset; TitledPane tltpEntrantes;
		
	
	public void MostrarSolicitudesEntrantes() { 
		//listSolicitudes.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> System.out.println(v));
		listSolicitudes.getItems().clear();
		choiceAgentes.getItems().clear();
		
		try {
			database.PrepareSolicitudes();
			miResultset = database.CheckSolicitudes("P");
			
			while(miResultset.next()) {
				
				SolicitudesItems.add(miResultset.getString("nombre"));
				
			} 
			
			
			listSolicitudes.setItems(SolicitudesItems);
			
		}catch (Exception e) {System.out.println(e.getMessage()); e.printStackTrace(); 
		System.out.println("Error al mostar solicitudes");} 
		
		
			try {
				database.PrepareUsuarios();
				miResultset = database.CheckAvailableUsuarios(true);
				while(miResultset.next()) {
					choiceItems.add(miResultset.getString("usuario"));
				}
				
				choiceAgentes.setItems(choiceItems);
				
			}catch (Exception e) {System.out.println(e.getMessage()); e.printStackTrace(); 
			System.out.println("Error al mostar agentes");} 
			
			
	}
	
	public void MostrarSolicitudSeleccionada() {
		
		try {
			database.PrepareSolicitudes();
			miResultset = database.CheckSolicitudes("P");
			while(miResultset.next()) {
				if(listSolicitudes.getSelectionModel().getSelectedItem().equals(miResultset.getString("nombre"))){
					lblFecha.setText(miResultset.getString("fecha")); 
					lblCliente.setText(miResultset.getString("nombre"));
					lblSolicitud.setText(miResultset.getString("comentarios")); break;
				}
			}
	
			
			
		}catch (Exception e) {System.out.println(e.getMessage()); e.printStackTrace(); 
		System.out.println("Error al imprimir solicitud");} 
	}
	
	public void AsignarSolicitud() {
		try {
			database.PrepareSolicitudes();
			database.UpdateSolicitudes(listSolicitudes.getSelectionModel().getSelectedItem(),
					choiceAgentes.getSelectionModel().getSelectedItem(),"A");
			alert.setTitle("Asignacion de solicitudes");
			alert.setHeaderText("Se ha asignado correctamente la solicitud al agente");
			alert.setContentText("Favor de Actualizar las solicitudes");
			alert.showAndWait();	
		}catch (Exception e) {System.out.println(e.getMessage()); e.printStackTrace(); 
		System.out.println("Error al asignar solicitud"); } 
	}
		
	
	public void MostrarSolicitudesAsignadas() {
		
	}
	
	public void MostrarSolicitudesSeguimiento() {
		
	}
	
	public void btnAcercaDe() {
		
	}
}//end class
