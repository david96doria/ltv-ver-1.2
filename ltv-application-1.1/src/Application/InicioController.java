package Application;

import javax.swing.JOptionPane;

import Database.DataBase;
import Database.UsuariosTable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InicioController {

	///////////////////////////////////////////////////////////////////////////
	// Attributes /////////////////////////////////////////////////////////////
	public Button			btnInicio;
	public TextField		txtUsuario;
	public PasswordField	txtContrasena;
	
	Main objMain = new Main();
	DataBase objDatabase = new DataBase();
	UsuariosTable user = new UsuariosTable();

	///////////////////////////////////////////////////////////////////////////
	// Methods ////////////////////////////////////////////////////////////////
	public void print(String mss) { System.out.print(mss); }
	
	public void callInicio () {
		try {
			objDatabase.PrepareUsuarios();
			user = objDatabase.findUsuario(txtUsuario.getText());
			
			if(user.getCLAVE().equals(txtContrasena.getText())) {
				btnInicio.getScene().getWindow().hide();
				switch (user.getCARGO().charAt(0)) {
					case 'R': objMain.changeScene("ReservacionesScene.fxml");	objDatabase.CloseDataBase(); break;
					case 'A': objMain.changeScene("AdministracionScene.fxml");	break;
					case 'C': objMain.changeScene("ContabilidadScene.fxml");	break;
					case 'D': objMain.changeScene("DireccionScene.fxml");		break;
				default: break;
				}
			}else {
				System.out.println("Contraseña incorrecta");
			}
			
		}catch(Exception e) { System.out.println(e.getMessage());}
		
		
//		findUSUARIO(txtUsuario.getText());
//		if(getCLAVE().equals(txtContrasena.getText())) {
//			btnInicio.getScene().getWindow().hide();
//			try {
//				switch (getCARGO()) {
//				case 'R': objMain.changeScene("ReservacionesScene.fxml");	break;
//				case 'A': objMain.changeScene("AdministracionScene.fxml");	break;
//				case 'C': objMain.changeScene("ContabilidadScene.fxml");	break;
//				case 'D': objMain.changeScene("DireccionScene.fxml");		break;
//				default: break;
//				}
//			}catch (Exception e) {
//				errMess(" Error al iniciar sesión.");
//			}
//		}else {
//			errMess(" Contraseña incorrecta.");
//		}
	}

}
