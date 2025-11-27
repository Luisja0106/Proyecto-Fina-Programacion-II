package controller.viewControllers;

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

  private boolean isInFav = false;

  @FXML
  void addCarrito(ActionEvent event) {
    InputDialog.information("Se ha añadido al carrito", "El producto se ha añadido al carrito satisfactoriamente");

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
    Image img = new Image(getClass().getResource(ruta).toExternalForm());
    imgProducto.setImage(img);
  }
}
