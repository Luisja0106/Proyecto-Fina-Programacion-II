package controller.viewControllers;

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
}
