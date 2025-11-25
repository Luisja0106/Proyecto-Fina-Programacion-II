package controller;

import java.io.*;
import modelo.Nodo;
import modelo.Productos;


public class ProductoLista extends Listas<Productos> {

    private final String ARCHIVO_DB = "productos_db.txt";

    public ProductoLista() {
        super(); // Inicializa la lista vacía

        // carga el txt al inicio de la clase
        setCargarProductosTxt();
    }

    public Nodo<Productos> buscarPorId(String id) {
        if (getEsVacia()) return null;
        Nodo<Productos> actual = cabecera;
        do {
            if (actual.info.getId().equals(id)) return actual;
            actual = actual.sig;
        } while (actual != cabecera);
        return null;
    }

    public boolean eliminarProducto(String id) {
        if (getEsVacia()) return false;
        Nodo<Productos> actual = cabecera;
        boolean encontrado = false;
        do {
            if (actual.info.getId().equals(id)) {
                encontrado = true;
                break;
            }
            actual = actual.sig;
        } while (actual != cabecera);

        if (!encontrado) return false;

        if (actual == cabecera && actual.sig == cabecera) {
            cabecera = null;
        } else {
            Nodo<Productos> anterior = actual.ant;
            Nodo<Productos> siguiente = actual.sig;
            anterior.sig = siguiente;
            siguiente.ant = anterior;
            if (actual == cabecera) cabecera = siguiente;
        }
        setGuardarProductosTxt();
        return true;
    }
}