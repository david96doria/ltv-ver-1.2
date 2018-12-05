package Application;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	///////////////////////////////////////////////////////////////////////////
	// Methods ////////////////////////////////////////////////////////////////
	public void PrintErrMss(String mss) { JOptionPane.showMessageDialog(new JPanel(), mss, "Error", JOptionPane.ERROR_MESSAGE); }
	public void PrintInfMss(String mss) { JOptionPane.showMessageDialog(new JPanel(), mss, "OK", JOptionPane.INFORMATION_MESSAGE); }

	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("InicioScene.fxml"));
//			Parent root = FXMLLoader.load(getClass().getResource("ReservacionesScene.fxml"));
			primaryStage.setTitle("La Tienda del Viaje");
			primaryStage.setScene(new Scene(root, 300, 450));
			primaryStage.setResizable(false);
			primaryStage.show();
		}catch(Exception e) {
			PrintErrMss("Error al iniciar la aplicación.");
			System.out.println(e.getMessage());
		}
	}

	public void changeScene(String NewFXML, double alto, double ancho) {
		try {
			Stage stage = new Stage();
			Parent pane = FXMLLoader.load(getClass().getResource(NewFXML));
			stage.setTitle("La Tienda del Viaje");
			stage.setScene(new Scene(pane));
			stage.setResizable(true);
			stage.setHeight(alto);
			stage.setWidth(ancho);
			stage.show();
		}catch(Exception e) {
			PrintErrMss("Error al cambiar de ventana.");
			System.out.println(e.getMessage());
		}
	}

	public void addScene(String AddFXML) {
		try {
			Stage stage = new Stage();
			Parent pane = FXMLLoader.load(getClass().getResource(AddFXML));
			stage.setTitle("La Tienda del Viaje");
			stage.setScene(new Scene(pane));
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		}catch(Exception e) {
			PrintErrMss("Error al agregar una ventana nueva.");
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) { launch(args); }

}
