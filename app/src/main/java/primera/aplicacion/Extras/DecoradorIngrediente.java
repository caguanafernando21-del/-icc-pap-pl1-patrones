package primera.aplicacion.Extras;

import primera.aplicacion.principal.Pedido;

public abstract class DecoradorIngrediente implements Pedido {
    protected Pedido pedidoWrappee;

    public DecoradorIngrediente(Pedido pedido) {
        this.pedidoWrappee = pedido;
    }

    public Pedido getPedidoWrappee() {
        return pedidoWrappee;
    }

    @Override
    public Pedido clonar() {
        try {
            DecoradorIngrediente clon = (DecoradorIngrediente) super.clone();
            clon.pedidoWrappee = this.pedidoWrappee.clonar();
            return clon;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getDescripcion() {
        return pedidoWrappee.getDescripcion();
    }

    @Override
    public double getCosto() {
        return pedidoWrappee.getCosto();
    }

    @Override
    public String getId() {
        return pedidoWrappee.getId();
    }
}