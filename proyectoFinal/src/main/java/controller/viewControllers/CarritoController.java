package controller.viewControllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import utils.InputDialog;
import utils.Paths;

public class CarritoController {

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  @FXML
  private TilePane TlProdu;

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
    addGrid();
  }

}
