package controller.viewControllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.Paths;

public class PerfilController {

  @FXML
  private ComboBox<?> cbOptions;

  @FXML
  private TextField lblCVV;

  @FXML
  private TextField lblCiudad;

  @FXML
  private TextField lblContacto;

  @FXML
  private TextField lblDireccion;

  @FXML
  private TextField lblFechaExp;

  @FXML
  private TextField lblNomEnvio;

  @FXML
  private TextField lblNomTar;

  @FXML
  private TextField lblNumTar;

  @FXML
  private VBox productos;

  @FXML
  void goToAdmin(ActionEvent event) {
    // TODO: link admin
  }

  @FXML
  void goToCart(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
  }

  @FXML
  void goToHistory(ActionEvent event) {
    // TODO: link history

  }

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  @FXML
  void guardarPerfil(ActionEvent event) {

  }
}
