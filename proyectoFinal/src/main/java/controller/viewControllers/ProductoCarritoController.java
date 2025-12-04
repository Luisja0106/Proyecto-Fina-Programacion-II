package controller.viewControllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Productos;
import java.io.File;
import controller.CarritoLista;
import utils.InputDialog;
import utils.Paths;

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


  private Productos productoActual;

  public void setProductos(Productos p) {
      this.productoActual = p;

      lblName.setText(p.getNombre());
      String info = p.getNomVendedor() + " - " + p.getCategoria();
      lblinfo.setText(info);

      lblPrecio.setText("$" + p.getPrecio());

      lblcant.setText("1");

      File file = new File(p.getImagen());
      if (file.exists()) {
          Image img = new Image(file.toURI().toString());
          imgProducto.setImage(img);
      }
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
    if (cant <= 0) this.eliminar(event);
    lblcant.setText(String.valueOf(cant));
  }

  // TODO: Metodo para eliminar un prodcuto del carrito
  @FXML
  void eliminar(ActionEvent event) {
      if (this.productoActual == null) return;

      CarritoLista carritoManager = new CarritoLista();
      boolean eliminado = carritoManager.eliminarDelCarrito(this.productoActual.getId());

      if (eliminado) {
          App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
      } else {
          InputDialog.error("Error", "No se pudo eliminar el producto.");
      }
  }

  // TODO: Metodo para añadir un producto a favoritos
  @FXML
  void favorito(ActionEvent event) {

  }
}
