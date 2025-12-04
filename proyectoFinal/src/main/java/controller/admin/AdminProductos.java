package controller.admin;

import controller.Listas;
import controller.ProductoLista;
import model.Nodo;
import model.Productos;
import model.Usuarios;
import utils.InputDialog;
import utils.UserSession;

public class AdminProductos {
  private static Usuarios user;

  public AdminProductos() {
    user = UserSession.getInstance().getUser();
    setLista();
  }

  public boolean isAdmin() {
    return user.isAdmin();
  }

  // metodo para cargar la lista personal del admin
  public boolean setLista() {
    if (!isAdmin()) { // verificaciones de que sea admin
      return false;
    }
    ProductoLista todos = new ProductoLista();
    Nodo<Productos> aux = todos.cabecera;
    Listas<Productos> productos = new Listas<>();
    do {
      String vendedor = aux.info.getNomVendedor();
      if (vendedor.equals(user.getNombre())) {
        productos.addF(aux.info);
      }
      aux = aux.sig;
    } while (aux != todos.cabecera);
    if (productos.getEsVacia()) {
      return false;
    }
    user.setProductos(productos);
    return true;
  }

  // metodo para añadir un producto a la lista personal del admin
  public boolean addLista(String nombre, float precio, String imagen, String categoria, int stock) {
    String id = createId(categoria);
    // if (!comprobaciones(id, nombre)) {
    // return false;
    // }
    Productos nuevo = new Productos(id, nombre, user.getNombre(), precio, imagen, stock, categoria);
    user.getProductos().addF(nuevo);
    ProductoLista.getInstancia().addF(nuevo);
    ProductoLista.getInstancia().setGuardarProductosTxt();
    return true;
  }

  // metodo para elminar un producto de la lista personal del admin
  public void rmLista(String id) {
    Nodo<Productos> produ = buscarId(id);
    if (produ == null) {
      InputDialog.error("ID no encontrado", "No se ha encontrado el producto con el id ingresado");
      return;
    }
    user.getProductos().eliminar(produ);
  }

  private String createId(String categoria) {
    ProductoLista todos = new ProductoLista();
    Nodo<Productos> aux = todos.cabecera;
    int x = 0;
    do {
      if (aux.info.getCategoria().equals(categoria)) {
        x = Integer.parseInt(aux.info.getId());
        if (x < Integer.parseInt(aux.info.getId())) {
          x = Integer.parseInt(aux.info.getId());
        }
      }
      aux = aux.sig;
    } while (aux != todos.cabecera);
    x++;
    return String.valueOf(x);
  }

  // private boolean comprobaciones(String id, String nombre) {
  // ProductoLista todos = new ProductoLista();
  // Nodo<Productos> aux = todos.cabecera;
  // do {
  // if (aux.info.getId().equals(id)) {
  // InputDialog.warning("Error ya se ha registrado ese id", "Error, ese ID ya ha
  // sido registrado");
  // return false;
  // }
  // if (aux.info.getNombre().equals(nombre)) {
  // InputDialog.warning("Error ya se ha registrado un producto con ese nombre",
  // "Error, ese Nombre ya ha sido registrado");
  // return false;
  // }
  // aux = aux.sig;
  // } while (aux != todos.cabecera);
  // return true;
  // }

  private Nodo<Productos> buscarId(String id) {
    ProductoLista todos = new ProductoLista();
    Nodo<Productos> aux = todos.cabecera;
    do {
      if (aux.info.getId().equals(id)) {
        return aux;
      }
      aux = aux.sig;
    } while (aux != todos.cabecera);
    return null;
  }

  public boolean modStock(String id, int cantN) {
    Nodo<Productos> nodo = buscarId(id);
    Productos produ = nodo.info;
    if (produ == null) {
      InputDialog.error("Ha ocurrido un error", "Ha ocurrido un error al modificar el stock del producto");
      return false;
    }
    produ.setStock(cantN);
    return true;
  }
}
