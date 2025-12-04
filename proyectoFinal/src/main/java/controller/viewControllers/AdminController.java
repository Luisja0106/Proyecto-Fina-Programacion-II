package controller.viewControllers;

import java.io.IOException;

import application.App;
import controller.admin.AdminProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Admin;
import model.Nodo;
import model.Productos;
import utils.InputDialog;
import utils.Paths;

public class AdminController {

  @FXML
  private TextField lblStock;

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

  AdminProductos admin = new AdminProductos();

  @FXML
  void AggProdu(ActionEvent event) {
    String nombre = lblNom.getText();
    float precio = Float.parseFloat(lblPrecio.getText());
    String imagen = "/Imagenes/KZ-castor-bass.jpg";
    String cate = lblCategoria.getText();
    int stock = Integer.parseInt(lblStock.getText());
    admin.addLista(nombre, precio, imagen, cate, stock);

  }

  @FXML
  void goToCart(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
  }

  @FXML
  void goToHistory(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_HISTORIAL_VIEW);
  }

  @FXML
  void goToHome(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_CATALOGO_VIEW);
  }

  @FXML
  void goToProfile(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_PERFIL_VIEW);
  }

  private void addGrid(Productos produ) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.GESTIONAR_STOCKADMIN_VIEW));
      HBox tarjeta = loader.load();
      AdminProductController controller = loader.getController();
      controller.setProducto(produ.getNombre(), "", produ.getPrecio(), produ.getStock(), produ.getImagen());
      productos.getChildren().add(tarjeta);
    } catch (IOException e) {
      e.printStackTrace();
      InputDialog.error("error", "error" + e.getMessage());
    }
  }

  private void setGrid(Admin user) {
    Nodo<Productos> aux = user.getProductos().cabecera;
    do {
      addGrid(aux.info);
      aux = aux.sig;
    } while (aux != user.getProductos().cabecera);
  }

  public void initialize() {
  }
}
