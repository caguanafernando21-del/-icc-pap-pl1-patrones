package primera.aplicacion.principal;


public interface Pedido extends Cloneable {
    Pedido clonar();
    String getDescripcion();
    double getCosto();
    String getId();
}