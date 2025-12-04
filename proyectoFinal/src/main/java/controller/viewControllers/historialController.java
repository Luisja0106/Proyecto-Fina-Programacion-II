package controller.viewControllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Paths;

public class historialController {

  @FXML
  private VBox prodcutos;

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  @FXML
  void goToUser(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_PERFIL_VIEW);
  }

  private void setGrid() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_HISTORIALPRODU_VIEW));
      HBox tarjeta = loader.load();
      historialProductoController controller = loader.getController();
      controller.setProducto("Kz Castor Bass",
          "Los kz castor bass son unos audifonos in-ear especializados en bajos profundos", "#klsd", "00/00/0000",
          "KZ official", 50000, 1, 0, 200000, 400000, "/Imagenes/KZ-castor-bass.jpg");
      prodcutos.getChildren().add(tarjeta);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void initialize() {
    for (int i = 0; i < 20; i++) {
      setGrid();
    }
  }
}
