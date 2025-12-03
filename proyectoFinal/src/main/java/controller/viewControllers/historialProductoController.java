package controller.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class historialProductoController {

  @FXML
  private Label fechaCompra;

  @FXML
  private Label idRecibo;

  @FXML
  private ImageView imgImagen;

  @FXML
  private Label lblCantidad;

  @FXML
  private Label lblDesc;

  @FXML
  private Label lblEnvio;

  @FXML
  private Label lblInfo;

  @FXML
  private Label lblNombre;

  @FXML
  private Label lblTotal;

  @FXML
  private Label lblValor;

  @FXML
  private Label vendedor;

  @FXML
  void addcarrito(ActionEvent event) {

  }

  @FXML
  void restore(ActionEvent event) {

  }

  public void setProducto(String nombre, String info, String idRecibo, String fecha, String vendedor, float valor,
      int cant, float descuento, float envio, float total, String ruta) {
    lblNombre.setText(nombre);
    lblInfo.setText(info);
    this.idRecibo.setText(idRecibo);
    fechaCompra.setText(fecha);
    this.vendedor.setText(vendedor);
    lblValor.setText("$" + valor);
    lblCantidad.setText(String.valueOf(cant));
    lblDesc.setText("$" + descuento);
    lblEnvio.setText("$" + envio);
    lblTotal.setText("$" + total);
    Image img = new Image(getClass().getResource(ruta).toExternalForm());
    imgImagen.setImage(img);
  }
}
