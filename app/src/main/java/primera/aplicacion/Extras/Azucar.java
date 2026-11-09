package primera.aplicacion.Extras;

import primera.aplicacion.principal.Pedido;

public class Azucar extends DecoradorIngrediente {
    public Azucar(Pedido pedido) { super(pedido); }
    @Override
    public String getDescripcion() {
         return pedidoWrappee.getDescripcion() + "\n  - Azúcar (+$0.30)"; }
    
    @Override 
    public double getCosto() { 
        return pedidoWrappee.getCosto() + 0.30; 
    }
}