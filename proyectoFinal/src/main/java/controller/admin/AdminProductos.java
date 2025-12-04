package controller.admin;

import controller.Listas;
import controller.ProductoLista;
import model.Admin;
import model.Productos;

public class AdminProductos {
  private Admin user;
  private Listas<Productos> productos;

  public boolean isAdmin() {
    return user.isAdmin();
  }

}
