package primera.aplicacion.Extras;

import primera.aplicacion.principal.Pedido;

public class Pollo extends DecoradorIngrediente {
    public Pollo(Pedido pedido) { super(pedido); }
    @Override 
        public String getDescripcion() {
             return pedidoWrappee.getDescripcion() + "\n  - Pollo (+$1.80)"; }
    @Override 
        public double getCosto() { 
            return pedidoWrappee.getCosto() + 1.80; }
}