package controller.viewControllers;

import application.App;
import controller.CarritoLista;
import controller.WishLista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Productos;
import utils.InputDialog;
import utils.Paths;

import java.io.File;

public class ProductoWishController {

  @FXML
  private Button btnMinus;

  @FXML
  private Button btnPlus;

  @FXML
  private ImageView imgProdu;

  @FXML
  private Label lblCant;

  @FXML
  private Label lblInfo;

  @FXML
  private Label lblName;

  @FXML
  private Label lblPrice;

  private Productos productoActual;

    @FXML
  void addProdu(ActionEvent event) {
    int cant = Integer.parseInt(lblCant.getText());
    cant++;
    lblCant.setText(String.valueOf(cant));
  }

  @FXML
  void aggCarrito(ActionEvent event) {
      if (this.productoActual == null) return;

      CarritoLista carrito = new CarritoLista();
      carrito.agregarAlCarrito(this.productoActual);

      InputDialog.information("Carrito", "Producto movido al carrito.");
  }

  @FXML
  void QutProduct(ActionEvent event) {
      int cant = Integer.parseInt(lblCant.getText());
      cant--;

      if (cant <= 0) {
          eliminarDeWishList();
      }
      lblCant.setText(String.valueOf(cant));
  }

    public void setDatosProducto(Productos p) {
        this.productoActual = p;
        lblName.setText(p.getNombre());
        lblInfo.setText(p.getNomVendedor() + " - " + p.getCategoria());
        lblPrice.setText("$" + p.getPrecio());


        lblCant.setText("1");

        File file = new File(p.getImagen());
        if (file.exists()) {
            Image img = new Image(file.toURI().toString());
            imgProdu.setImage(img);
        }
    }

    private void eliminarDeWishList() {
        WishLista wl = new WishLista();
        wl.eliminarDeWishList(this.productoActual.getId());

        // Recargamos la vista de perfil para que desaparezca la tarjeta
        App.app.setScene(Paths.GESTIONAR_PERFIL_VIEW);
    }

}
