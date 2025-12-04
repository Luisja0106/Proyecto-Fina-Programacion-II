package model;

public class Compra {
    private String id;
    private String fecha;
    private double subtotal;
    private double envio;
    private double descuento;
    private double total;
    private String detalleProductos;   // Nombres y cantidades


    public Compra(String id, String fecha, double subtotal, double envio, double descuento, double total, String detalleProductos) {
        this.id = id;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.envio = envio;
        this.descuento = descuento;
        this.total = total;
        this.detalleProductos = detalleProductos;
    }


    public String toCsv() {
        // Reemplazamos saltos de línea por un separador especial (#) para no romper el CSV
        String detalleSeguro = detalleProductos.replace("\n", "#");
        return id + ";" + fecha + ";" + subtotal + ";" + envio + ";" + descuento + ";" + total + ";" + detalleSeguro;
    }


    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public double getSubtotal() { return subtotal; }
    public double getEnvio() { return envio; }
    public double getDescuento() { return descuento; }
    public double getTotal() { return total; }

    // Al obtener el detalle, recuperamos el formato original con saltos de linea
    public String getDetalleProductos() {
        return detalleProductos.replace("#", "\n");
    }
}