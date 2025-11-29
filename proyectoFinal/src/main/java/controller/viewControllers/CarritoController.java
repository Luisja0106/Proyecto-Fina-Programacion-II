package controller.viewControllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utils.Paths;

public class CarritoController {

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

}
