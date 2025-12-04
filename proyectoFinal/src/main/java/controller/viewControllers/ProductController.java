package controller.viewControllers;
import controller.ProductoLista;
import controller.CarritoLista;
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
import java.io.File;

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
    if (!isInFav) {
      favIcon.setGlyphName(FontAwesomeIcon.HEART.name());
      favIcon.setStyle("-fx-fill:#c00b0b;");
      isInFav = true;
    } else {
      favIcon.setGlyphName(FontAwesomeIcon.HEART_ALT.name());
      favIcon.setStyle("-fx-fill:#BFC0C2;");
      isInFav = false;
    }
  }

  public void setProducto(String nom, float precio, String ruta) {
    txtNom.setText(nom);
    txtPrecio.setText("$" + precio);

    File file = new File(ruta);
    if (file.exists()) {
        Image img = new Image(file.toURI().toString());
        imgProducto.setImage(img);
    }
  }
}
