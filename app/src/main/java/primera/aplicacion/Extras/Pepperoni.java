package primera.aplicacion.Extras;

import primera.aplicacion.principal.Pedido;

public class Pepperoni extends DecoradorIngrediente {
    public Pepperoni(Pedido pedido) { super(pedido); }
    @Override 
        public String getDescripcion() { return pedidoWrappee.getDescripcion() + "\n  - Pepperoni Crujiente (+$2.00)"; }
    @Override   
        public double getCosto() { 
            return pedidoWrappee.getCosto() + 2.00; 
        }
}