package controller.admin;

import controller.Listas;
import controller.ProductoLista;
import model.Admin;
import model.Nodo;
import model.Productos;

public class AdminProductos {
  private Admin user;

  public boolean isAdmin() {
    return user.isAdmin();
  }

  // metodo para cargar la lista personal del admin
  public boolean setLista() {
    if (!isAdmin()) // verificaciones de que sea admin
      return false;
    ProductoLista todos = new ProductoLista(true);
    Nodo<Productos> aux = todos.cabecera;
    Listas<Productos> productos = new Listas<>();
    do {
      String vendedor = aux.info.getNomVendedor();
      if (vendedor.equals(user.getNombre())) {
        productos.addF(aux.info);
      }
      aux = aux.sig;
    } while (aux != todos.cabecera);
    if (!productos.getEsVacia())
      return false;
    user.setProductos(productos);
    return true;
  }

  private void addLista() {
  }
}
