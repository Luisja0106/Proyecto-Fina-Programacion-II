package controller.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductoCarritoController {

  @FXML
  private Button btnAddProdu;

  @FXML
  private ImageView imgProducto;

  @FXML
  private Button btnDelete;

  @FXML
  private Button btnFav;

  @FXML
  private Button btnQuitProdu;

  @FXML
  private Label lblName;

  @FXML
  private Label lblPrecio;

  @FXML
  private Label lblcant;

  @FXML
  private Label lblinfo;

  public void setProductos(String nombre, String info, float precio, int cant, String ruta) {
    lblName.setText(nombre);
    lblinfo.setText(info);
    lblPrecio.setText("$" + precio);
    lblcant.setText(String.valueOf(cant));
    Image img = new Image(getClass().getResource(ruta).toExternalForm());
    imgProducto.setImage(img);
  }

  // TODO: Metodo para aumentar la cantidad de un mismo producto
  @FXML
  void añadir(ActionEvent event) {
    int cant = Integer.parseInt(lblcant.getText());
    cant++;
    lblcant.setText(String.valueOf(cant));
  }

  // TODO: Metodo para eliminar una cantidad de un mismo producto
  @FXML
  void reducir(ActionEvent event) {
    int cant = Integer.parseInt(lblcant.getText());
    cant--;
    if (cant <= 0) // NOTE: Aplicar logica de que si el producto es 0 o menor, directamente
                   // eliminarlo o preguntar si quiere eliminarlo
      cant = 0;
    lblcant.setText(String.valueOf(cant));
  }

  // TODO: Metodo para eliminar un prodcuto del carrito
  @FXML
  void eliminar(ActionEvent event) {

  }

  // TODO: Metodo para añadir un producto a favoritos
  @FXML
  void favorito(ActionEvent event) {

  }
}
