package Application;

import Database.DataBase;
import Database.UsuariosTable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class InicioController {

	///////////////////////////////////////////////////////////////////////////
	// Attributes /////////////////////////////////////////////////////////////
	public ImageView		imLTV;
	public Label			lblUsuario;
	public TextField		txtUsuario;
	public Label			lblContrasena;
	public Button			btnInicio;
	public PasswordField	txtContrasena;

	Main objMain = new Main();
	DataBase objDatabase = new DataBase();
	UsuariosTable user = new UsuariosTable();

	///////////////////////////////////////////////////////////////////////////
	// Methods ////////////////////////////////////////////////////////////////
	public void callInicio () {
		objDatabase.PrepareUsuarios();
		user = objDatabase.findUsuario(txtUsuario.getText());
		objDatabase.CloseDataBase();

		if(user.getCLAVE().equals(txtContrasena.getText())) {
			objDatabase.PrepareUsuarios();
			objDatabase.UpdateUsuarioINICIO(txtUsuario.getText(), 1);
			btnInicio.getScene().getWindow().hide();
			switch (user.getCARGO().charAt(0)) {
				case 'R': objMain.changeScene("ReservacionesScene.fxml",  600, 900);	break;
				case 'A': objMain.changeScene("AdministracionScene.fxml", 600, 900);	break;
				case 'C': objMain.changeScene("ContabilidadScene.fxml",   600, 900);	break;
				case 'D': objMain.changeScene("DireccionScene.fxml",      600, 900);	break;
			default: break;
			}
		}else {
			objDatabase.PrintErrMss("Contraseña incorrecta");
		}
	}
}
