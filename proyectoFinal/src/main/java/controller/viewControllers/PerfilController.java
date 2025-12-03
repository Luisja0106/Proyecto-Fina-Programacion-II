package controller.viewControllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.InputDialog;
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

  private void addGrid() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_WISHLIST_VIEW));
      HBox tarjeta = loader.load();
      ProductoWishController controller = loader.getController();
      controller.setProducto("Kz Castor Bass",
          "Los kz son unos in-ear especializados en el audion profundo con bajos definidos", 50000, 1,
          "/Imagenes/KZ-castor-bass.jpg");
      productos.getChildren().add(tarjeta);
    } catch (IOException e) {
      e.printStackTrace();
      InputDialog.error("error", "error" + e.getMessage());
    }
  }

  public void initialize() {
    for (int i = 0; i < 20; i++) {
      addGrid();
    }
  }
}
