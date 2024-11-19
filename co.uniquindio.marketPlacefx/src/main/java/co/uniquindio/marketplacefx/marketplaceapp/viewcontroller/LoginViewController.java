package co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;

import co.uniquindio.marketplacefx.marketplaceapp.MarketPlaceApplication;
import co.uniquindio.marketplacefx.marketplaceapp.controller.LoginController;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.UsuarioDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static co.uniquindio.marketplacefx.marketplaceapp.utils.PrestamoConstantes.*;

public class LoginViewController {
    LoginController loginController;

    @FXML
    private Button btnIngresar;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtNombreUsuario;

    @FXML
    void initialize() {
        loginController = new LoginController();
    }





    private boolean verificarCamposVacios() {
        boolean verificar = true;
        if (txtNombreUsuario.getText().isEmpty()) {
            verificar = false;
            mostrarMensaje(TITULO_NOMBRE_USUARIO_VACIO, HEADER, BODY_NOMBRE_USUARIO_VACIO,Alert.AlertType.INFORMATION);
        }
        if (txtContrasena.getText().isEmpty()) {
            verificar = false;
            mostrarMensaje(TITULO_CONTRASENA_USUARIO_VACIO, HEADER, BODY_CONTRASENA_USUARIO_VACIA,Alert.AlertType.INFORMATION);

        }

        return verificar;
    }

    private boolean validaUsusario() {
        boolean validacion=false;
        if (verificarCamposVacios()) {
            UsuarioDto usuario = new UsuarioDto(txtNombreUsuario.getText(), txtContrasena.getText());
            if(loginController.validarUsuario(usuario)){
                mostrarMensaje(TITULO_USUARIO_ENCONTRADO, HEADER, BODY_USUARIO_ENCONTRADO,Alert.AlertType.INFORMATION);
                return true;
            }else{
                mostrarMensaje(TITULO_USUARIO_NO_ENCONTRADO, HEADER, BODY_USUARIO_NO_ENCONTRADO,Alert.AlertType.INFORMATION);
            }
        }
        return false;
    }


    @FXML
    void onbtnIngresar(ActionEvent event) throws IOException {
        if(validaUsusario()){
            UsuarioDto usuario=new UsuarioDto(txtNombreUsuario.getText(),txtContrasena.getText());
            loginController.iniciarVista(txtNombreUsuario.getText());
            MarketPlaceApplication.setRoot("marketplacee");
        }
    }


    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
