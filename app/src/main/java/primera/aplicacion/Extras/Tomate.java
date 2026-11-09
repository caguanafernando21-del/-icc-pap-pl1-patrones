package primera.aplicacion.Extras;

import primera.aplicacion.principal.Pedido;

public class Tomate extends DecoradorIngrediente {
    public Tomate(Pedido pedido) { super(pedido); }
    @Override 
        public String getDescripcion() {
             return pedidoWrappee.getDescripcion() + "\n  - Tomate (+$0.80)"; }
    @Override 
        public double getCosto() { 
            return pedidoWrappee.getCosto() + 0.80; }
}