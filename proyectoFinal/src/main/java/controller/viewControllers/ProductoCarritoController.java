package controller.viewControllers;

import application.App;
import controller.WishLista;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Productos;
import java.io.InputStream;

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
    lblcant.setText(String.valueOf(p.getStock()));

    InputStream file = getClass().getResourceAsStream("/" + p.getImagen());
    if (file != null) {
      Image img = new Image(file);
      imgProducto.setImage(img);
    }

    WishLista wishManager = new WishLista();
    boolean estaEnWishlist = wishManager.existeEnWishList(p.getId());
    actualizarIconoFavorito(estaEnWishlist);
  }

  @FXML
  void añadir(ActionEvent event) {
    CarritoLista sumar = new CarritoLista();
    sumar.agregarAlCarrito(this.productoActual);

    App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
  }

  @FXML
  void reducir(ActionEvent event) {
    CarritoLista restar = new CarritoLista();
    restar.reducirCantidad(this.productoActual.getId());

    App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
  }

  @FXML
  void eliminar(ActionEvent event) {
    if (this.productoActual == null)
      return;

    CarritoLista carritoManager = new CarritoLista();
    boolean eliminado = carritoManager.eliminarDelCarrito(this.productoActual.getId());

    if (eliminado) {
      App.app.setScene(Paths.GESTIONAR_CARRITO_VIEW);
    } else {
      InputDialog.error("Error", "No se pudo eliminar el producto.");
    }
  }

  @FXML
  void favorito(ActionEvent event) {
    if (this.productoActual == null)
      return;

    WishLista wishManager = new WishLista();

    boolean existe = wishManager.existeEnWishList(this.productoActual.getId());

    if (existe) {

      wishManager.eliminarDeWishList(this.productoActual.getId());
      actualizarIconoFavorito(false);

    } else {

      wishManager.agregarAWishList(this.productoActual);
      actualizarIconoFavorito(true);
      InputDialog.information("Wishlist", "Producto guardado en tu lista de deseos.");
    }
  }

  private void actualizarIconoFavorito(boolean activo) {
    if (btnFav.getGraphic() instanceof FontAwesomeIconView) {
      FontAwesomeIconView icon = (FontAwesomeIconView) btnFav.getGraphic();

      if (activo) {
        icon.setGlyphName(FontAwesomeIcon.HEART.name());
        icon.setStyle("-fx-fill:#c00b0b;"); // Rojo
      } else {
        icon.setGlyphName(FontAwesomeIcon.HEART.name());
        icon.setStyle("-fx-fill:black;");
      }
    }
  }
}
