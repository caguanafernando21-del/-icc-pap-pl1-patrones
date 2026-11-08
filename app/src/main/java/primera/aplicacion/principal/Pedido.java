package primera.aplicacion.Extras;


public interface Pedido extends Cloneable {
    Pedido clonar();
    String getDescripcion();
    double getCosto();
    String getId();
}