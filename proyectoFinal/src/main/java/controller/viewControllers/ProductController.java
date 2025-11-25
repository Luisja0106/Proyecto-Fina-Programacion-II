package controller.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import utils.InputDialog;

public class ProductController {

  @FXML
  private Button btnAddFav;

  @FXML
  private Button btnCarrito;

  @FXML
  private ImageView imgProducto;

  @FXML
  private Label txtNom;

  @FXML
  private Label txtPrecio;

  @FXML
  void addCarrito(ActionEvent event) {
    InputDialog.information("Se ha añadido al carrito", "El producto se ha añadido al carrito satisfactoriamente");

  }

  @FXML
  void addFavs(ActionEvent event) {
    InputDialog.information("Se ha añadido a favoritos", "El producto se ha añadido a favoritos satisfactoriamente");
  }

  public void setProducto(String nom, float precio, String ruta) {
    txtNom.setText(nom);
    txtPrecio.setText("$" + precio);
  }
}
