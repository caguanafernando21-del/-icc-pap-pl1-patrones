package primera.aplicacion.principal;

public abstract class ProductoBase implements Pedido {
    private static int contadorId = 1;
    protected String id;
    protected String nombre;
    protected double precio;
    protected String categoria;

    public ProductoBase() {
        this.id = String.valueOf(contadorId++);
    }

    public ProductoBase(String nombre, double precio, String categoria) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    @Override
    public Pedido clonar() {
        try {
            ProductoBase clon = (ProductoBase) super.clone();
            clon.id = String.valueOf(contadorId++); // ID diferente para cada clon (Paso 2)
            return clon;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getDescripcion() {
        return nombre + " ($" + String.format("%.2f", precio) + ")";
    }

    @Override
    public double getCosto() {
        return precio;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
}