package controller.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminProductController {

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

  @FXML
  void QutProduct(ActionEvent event) {
    // NOTE: Añadir logica de eliminar producto teniendo en cuenta que no puede ser
    // menor que 0
    int cant = Integer.parseInt(lblCant.getText());
    cant--;
    cant = (cant <= 0) ? 0 : cant;
    lblCant.setText(String.valueOf(cant));
  }

  @FXML
  void addProdu(ActionEvent event) {
    int cant = Integer.parseInt(lblCant.getText());
    cant++;
    lblCant.setText(String.valueOf(cant));
  }

  @FXML
  void actualizarStock(ActionEvent event) {
    // TODO: logica actuaizar Stock

  }

  public void setProducto(String name, String info, float price, int cant, String ruta) {
    lblName.setText(name);
    lblInfo.setText(info);
    lblPrice.setText("$" + price);
    lblCant.setText(String.valueOf(cant));
    // Image img = new Image(getClass().getResource(ruta).toExternalForm());
    // imgProdu.setImage(img);
  }

}
