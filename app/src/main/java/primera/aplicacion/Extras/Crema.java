package primera.aplicacion.Extras;

import primera.aplicacion.principal.Pedido;

public class Crema extends DecoradorIngrediente {
    public Crema(Pedido pedido) { super(pedido); }
    @Override
    
        public String getDescripcion() { return pedidoWrappee.getDescripcion() + "\n  - Crema (+$0.50)"; }
    @Override 
        public double getCosto() {  
            return pedidoWrappee.getCosto() + 0.50; }
}