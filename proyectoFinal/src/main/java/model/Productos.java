package model;


public class Productos {
    private String id;
    private String nombre;
    private String nomVendedor;
    private float precio;
    private String imagen; // Ruta de la imagen
    private int stock;
    private String categoria;

    public Productos(String id, String nombre, String nomVendedor, float precio, String imagen, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.nomVendedor = nomVendedor;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
        this.categoria = categoria;
    }

    // setters y getters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNomVendedor() { return nomVendedor; }
    public void setNomVendedor(String nomVendedor) { this.nomVendedor = nomVendedor; }

    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    // txt formato: id;nombre;vendedor;precio;rutaImagen;stock
    public String toCsv() {
        return id + ";" + nombre + ";" + nomVendedor + ";" + precio + ";" + imagen + ";" + stock + ";" + categoria;
    }
}