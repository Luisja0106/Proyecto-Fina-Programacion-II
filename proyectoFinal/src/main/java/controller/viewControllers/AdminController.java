package controller.viewControllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.InputDialog;
import utils.Paths;

public class AdminController {

  @FXML
  private Label Stock;

  @FXML
  private TextField lblCategoria;

  @FXML
  private TextField lblNom;

  @FXML
  private TextField lblPrecio;

  @FXML
  private TextField lblSKU;

  @FXML
  private VBox productos;

  @FXML
  void AggProdu(ActionEvent event) {

  }

  @FXML
  void aumentarStock(ActionEvent event) {
    int cant = Integer.parseInt(Stock.getText());
    cant++;
    Stock.setText(String.valueOf(cant));
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
  void goToProfile(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_ADMIN_VIEW);
  }

  @FXML
  void reducirStock(ActionEvent event) {
    // NOTE: Añadir logica de eliminar producto teniendo en cuenta que no puede ser
    // menor que 0
    int cant = Integer.parseInt(Stock.getText());
    cant--;
    cant = (cant <= 0) ? 0 : cant;
    Stock.setText(String.valueOf(cant));
  }

  private void addGrid() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_STOCKADMIN_VIEW));
      HBox tarjeta = loader.load();
      AdminProductController controller = loader.getController();
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
