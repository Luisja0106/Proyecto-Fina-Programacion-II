package controller.viewControllers;

import application.App;
import controller.mock.MockLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.User;
import utils.InputDialog;
import utils.Paths;

public class RegistroController {

  @FXML
  private Button btnMostrarNewPass;

  @FXML
  private Button btnMostrarPrepe;

  @FXML
  private TextField lblCorreo;

  @FXML
  private TextField lblUser;

  @FXML
  private PasswordField lblnewPHide;

  @FXML
  private TextField lblnewPUnhide;

  @FXML
  private PasswordField lblrepePHide;

  @FXML
  private TextField lblrepePUnHide;

  @FXML
  private VBox logo_square;

  private MockLogin login = new MockLogin();
  private User user = new User();

  @FXML
  void Crear(ActionEvent event) {
    // TODO: actualizar este metodo cuando se tenga la clase user y login
    User u = user.createUser(lblUser.getText(), lblrepePUnHide.getText(), lblCorreo.getText());
    if (login.createAccount(u)) {
      InputDialog.information("cuenta Creada de forma satisfactoria", "Se ha creado la cuenta");
    } else {
      InputDialog.error("ha ocurrido un error", "Ha ocurrido un error intente de nuevo");
    }

  }

  @FXML
  void Ingresar(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_LOGIN_VIEW);
  }

  @FXML
  void mostrarNew(ActionEvent event) {
    switchHide(btnMostrarNewPass, lblnewPHide, lblnewPUnhide);
  }

  @FXML
  void mostrarRepe(ActionEvent event) {
    switchHide(btnMostrarPrepe, lblrepePHide, lblrepePUnHide);
  }

  private void switchHide(Button btn, PasswordField hide, TextField unHide) { // meotod para swichear la visibilidad de
                                                                              // la contraseña
    if (btn.getText().equals("MOSTRAR")) {
      // almacenamos datos de la posicion del cursor y la info
      unHide.setText(hide.getText());
      hide.setText("");
      // cambio de visibilidad
      unHide.setVisible(true);
      hide.setVisible(false);
      // ubicamos el cursor
      unHide.requestFocus();
      unHide.positionCaret(unHide.getText().length());
      // cambio en el boton
      btnMostrarPrepe.setText("OCULTAR");
    } else {
      // almacenamos datos de la posicion del cursor y la info
      hide.setText(unHide.getText());
      unHide.setText("");
      // cambio de visibilidad
      hide.setVisible(true);
      unHide.setVisible(false);
      // ubicamos el cursor
      hide.requestFocus();
      hide.positionCaret(hide.getText().length());
      // cambio en el boton
      btn.setText("MOSTRAR");
    }
  }
}
