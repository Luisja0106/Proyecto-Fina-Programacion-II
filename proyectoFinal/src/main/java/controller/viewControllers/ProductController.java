package controller.viewControllers;

import controller.ProductoLista;
import controller.CarritoLista;
import controller.WishLista;
import model.Nodo;
import model.Productos;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import utils.InputDialog;
import java.io.InputStream;

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
  private HBox cartBtnContainer;
  @FXML
  private FontAwesomeIconView favIcon;

  @FXML
  private FontAwesomeIconView cartIcon;

  private boolean isInFav = false;

  @FXML
  public void addCarrito(ActionEvent event) {

    String nombreBusqueda = txtNom.getText();

    if (nombreBusqueda == null || nombreBusqueda.isEmpty()) {
      InputDialog.error("Error", "No se puede identificar el nombre del producto.");
      return;
    }
    ProductoLista catalogo = new ProductoLista();

    Nodo<Productos> nodoEncontrado = catalogo.buscarPorNombre(nombreBusqueda);
    if (nodoEncontrado != null) {
      Productos productoCompleto = nodoEncontrado.info;

      CarritoLista gestorCarrito = new CarritoLista();
      gestorCarrito.agregarAlCarrito(productoCompleto);

      InputDialog.information("Añadido", "Se agregó '" + nombreBusqueda + "' al carrito.");
    } else {
      InputDialog.error("Error", "No se encontró el producto en la base de datos.");
    }
  }

  @FXML
  void addFavs(ActionEvent event) {
    Productos p = buscarProductoCompleto();
    if (p == null)
      return;

    WishLista wishLista = new WishLista();

    if (!isInFav) {
      // --- AGREGAR A WISHLIST ---
      boolean agregado = wishLista.agregarAWishList(p);
      if (agregado) {
        cambiarIconoFavorito(true);
        // InputDialog.information("Wishlist", "Producto añadido a tus deseos.");
      }
    } else {
      // --- ELIMINAR DE WISHLIST ---
      boolean eliminado = wishLista.eliminarDeWishList(p.getId());
      if (eliminado) {
        cambiarIconoFavorito(false);
      }
    }
  }

  private void cambiarIconoFavorito(boolean activo) {
    isInFav = activo;
    if (activo) {
      favIcon.setGlyphName(FontAwesomeIcon.HEART.name());
      favIcon.setStyle("-fx-fill:#c00b0b;");
    } else {
      favIcon.setGlyphName(FontAwesomeIcon.HEART_ALT.name());
      favIcon.setStyle("-fx-fill:#BFC0C2;");
    }
  }

  public void setProducto(String nom, float precio, String ruta) {
    txtNom.setText(nom);
    txtPrecio.setText("$" + precio);

    InputStream file = getClass().getResourceAsStream("/" + ruta);
    if (file != null) {
      Image img = new Image(file);
      imgProducto.setImage(img);
    }
    verificarEstadoInicialWishList(nom);
  }

  private void verificarEstadoInicialWishList(String nombre) {
    WishLista wl = new WishLista();
    // Buscamos en el catálogo global para tener el ID, y luego preguntamos a la
    // wishlist
    ProductoLista catalogo = new ProductoLista();
    Nodo<Productos> nodo = catalogo.buscarPorNombre(nombre);
    if (nodo != null) {
      if (wl.existeEnWishList(nodo.info.getId())) {
        cambiarIconoFavorito(true);
      }
    }
  }

  private Productos buscarProductoCompleto() {
    String nombre = txtNom.getText();
    ProductoLista catalogo = new ProductoLista();
    Nodo<Productos> nodo = catalogo.buscarPorNombre(nombre);
    if (nodo != null) {
      return nodo.info;
    }
    InputDialog.error("Error", "No se encontró información del producto.");
    return null;
  }
}
