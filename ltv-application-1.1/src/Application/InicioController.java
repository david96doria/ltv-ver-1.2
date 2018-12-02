package Application;

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
	public void callInicio () {
		try {
			objDatabase.PrepareUsuarios();
			user = objDatabase.findUsuario(txtUsuario.getText());
			if(user.getCLAVE().equals(txtContrasena.getText())) {
				btnInicio.getScene().getWindow().hide();
				switch (user.getCARGO().charAt(0)) {
					case 'R': objMain.changeScene("ReservacionesScene.fxml");	break;
					case 'A': objMain.changeScene("AdministracionScene.fxml");	break;
					case 'C': objMain.changeScene("ContabilidadScene.fxml");	break;
					case 'D': objMain.changeScene("DireccionScene.fxml");		break;
				default: break;
				}
			}else {
				objDatabase.PrintErrMss("Contraseña incorrecta");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
