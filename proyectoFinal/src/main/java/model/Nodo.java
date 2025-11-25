package model;

public class Nodo<T> {
    public T info;
    public Nodo<T> sig;
    public Nodo<T> ant;

    public Nodo(T info) {
        this.info = info;
        this.sig = null;
        this.ant = null;
    }
}