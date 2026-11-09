package primera.aplicacion.Extras;

import primera.aplicacion.principal.Pedido;

public class Queso extends DecoradorIngrediente {
    public Queso(Pedido pedido) { super(pedido); }
    @Override 
        public String getDescripcion() { 
            return pedidoWrappee.getDescripcion() + "\n  - Queso Mozzarella (+$1.50)"; }
    @Override 
        public double getCosto() { 
            return pedidoWrappee.getCosto() + 1.50; }
}