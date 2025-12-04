package controller.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Compra;

public class historialProductoController {

  @FXML
  private Label lblDesc;

  @FXML
  private Label lblEnvio;

  @FXML
  private Label lblFecha;

  @FXML
  private Label lblId;

  @FXML
  private Label lblNombre1;

  @FXML
  private Label lblSubtotal;

  @FXML
  private Label lblTotal;


    public void setDatos(Compra compra) {

        lblId.setText(compra.getId());
        lblFecha.setText(compra.getFecha());


        lblNombre1.setText(compra.getDetalleProductos());


        // 3. Totales monetarios
        lblSubtotal.setText(String.format("$ %.2f", compra.getSubtotal()));
        lblEnvio.setText(String.format("$ %.2f", compra.getEnvio()));
        lblDesc.setText(String.format("-$ %.2f", compra.getDescuento()));
        lblTotal.setText(String.format("$ %.2f", compra.getTotal()));

    }
}
