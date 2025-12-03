package controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import model.Nodo;
import model.Productos;

public class CarritoLista extends Listas<Productos> {

    //rutas
    private final String CARPETA_DB = "DataBase" + File.separator + "Carrito";
    private final String ARCHIVO_DB = "carrito_db.txt";
    private final String RUTA_COMPLETA = CARPETA_DB + File.separator + ARCHIVO_DB;

    public CarritoLista() {
        super();
        setCargarCarritoTxt();
    }

    /**
     * El producto se agrega al carrito y al txt
     */
    public void agregarAlCarrito(Productos p) {

        this.addF(p);
        setGuardarCarritoTxt();
    }

    /**
     * Elimina el producto del carrito y actualiza el TXT
     */
    public boolean eliminarDelCarrito(String id) {

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

        setGuardarCarritoTxt();
        return true;
    }

    /**
     * Elimina el producto del carrito y actualiza el TXT
     */
    public void setGuardarCarritoTxt() {
        createFile();

        if (getEsVacia()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_COMPLETA))) {
                bw.write("");
            } catch (IOException e) {
                System.err.println("Error al vaciar carrito: " + e.getMessage());
            }
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_COMPLETA))) {
            Nodo<Productos> actual = cabecera;
            do {
                bw.write(actual.info.toCsv());
                bw.newLine();
                actual = actual.sig;
            } while (actual != cabecera);
        } catch (IOException e) {
            System.err.println("Error al guardar carrito: " + e.getMessage());
        }
    }

    public void setCargarCarritoTxt() {
        File archivo = createFile();
        if (!archivo.exists()) return;

        setVaciar();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length >= 6) {
                    Productos p = new Productos(datos[0], datos[1], datos[2],
                            Float.parseFloat(datos[3]), datos[4], Integer.parseInt(datos[5]), datos[6]);
                    addF(p);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar carrito: " + e.getMessage());
        }
    }

    /**
     * Crea el archivo txt en la ruta Database/Carrito
     */
    private File createFile() {
        try {
            Path rutaFolder = Paths.get(CARPETA_DB);
            if (!Files.exists(rutaFolder)) {
                Files.createDirectories(rutaFolder);
            }
            Path rutaArchivo = Paths.get(RUTA_COMPLETA);
            if (!Files.exists(rutaArchivo)) {
                Files.createFile(rutaArchivo);
            }
            return rutaArchivo.toFile();
        } catch (IOException e) {
            System.err.println("Error creando archivo carrito: " + e.getMessage());
            return new File(RUTA_COMPLETA);
        }
    }
}