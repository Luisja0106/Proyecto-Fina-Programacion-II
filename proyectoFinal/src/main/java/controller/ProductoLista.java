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

    public void setGuardarProductosTxt() {
        if (getEsVacia()) return;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_DB))) {
            Nodo<Productos> actual = cabecera;
            do {
                bw.write(actual.info.toCsv());
                bw.newLine();
                actual = actual.sig;
            } while (actual != cabecera);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void setCargarProductosTxt() {
        File archivo = new File(ARCHIVO_DB);
        if (!archivo.exists()) return;
        setVaciar();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 6) {
                    Productos p = new Productos(datos[0], datos[1], datos[2],
                            Float.parseFloat(datos[3]), datos[4], Integer.parseInt(datos[5]));
                    agg(p);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
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