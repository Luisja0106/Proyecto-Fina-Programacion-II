package controller.viewControllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import utils.InputDialog;
import utils.Paths;

public class CarritoController {
  @FXML
  private Button btnPagar;
  @FXML
  private TilePane TlProdu;

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  // TODO: Metodo de pago
  @FXML
  void pagar(ActionEvent event) {

  }

  private void addGrid() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_CARRITO_PRODUCTO_VIEW));
      HBox tarjeta = loader.load();
      ProductoCarritoController controller = loader.getController();
      controller.setProductos("Kz castor bass", "Audifonos in-ear centrados en tener unos bajos profundos y definidos",
          500000, 1, "/Imagenes/KZ-castor-bass.jpg");
      TlProdu.getChildren().add(tarjeta);
    } catch (IOException e) {
      e.printStackTrace();
      InputDialog.error("error", "error" + e.getMessage());
    }
  }

  public void initialize() {
    btnPagar.setOnMouseEntered(e -> btnPagar.setText("P a g a r"));
    btnPagar.setOnMouseExited(e -> btnPagar.setText("Pagar"));
    for (int i = 0; i < 20; i++) {
      addGrid();
    }
  }

  @FXML
  void goToLogin(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_LOGIN_VIEW);
  }

  // TODO: metodo para ingresar al perfil
  @FXML
  void goToProfile(ActionEvent event) {
    InputDialog.information("Dirigiendose al perfil", "Entrando al perfil");
  }

}
