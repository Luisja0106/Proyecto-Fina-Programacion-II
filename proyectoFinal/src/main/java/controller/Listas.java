package controlador;

import modelo.Nodo;


public class Listas<T> {

    public Nodo<T> cabecera;

    public Listas() {
        this.cabecera = null;
    }

    public boolean getEsVacia() {
        return cabecera == null;
    }

    // agregar al final
    public boolean addF(T info) {
        Nodo<T> nuevo = new Nodo<>(info);

        if (getEsVacia()) {
            cabecera = nuevo;
            cabecera.sig = cabecera;
            cabecera.ant = cabecera;
        } else {
            Nodo<T> ultimo = cabecera.ant;

            nuevo.sig = cabecera;
            nuevo.ant = ultimo;

            ultimo.sig = nuevo;
            cabecera.ant = nuevo;
        }
        return true;
    }

    public Nodo<T> getUltimo() {
        if (getEsVacia()) return null;
        return cabecera.ant;
    }


    public void setVaciar() {
        cabecera = null;
    }


    public int getTamano() {
        if (getEsVacia()) return 0;

        int cont = 0;
        Nodo<T> actual = cabecera;
        do {
            cont++;
            actual = actual.sig;
        } while (actual != cabecera);

        return cont;
    }
}