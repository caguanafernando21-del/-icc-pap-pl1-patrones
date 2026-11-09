package primera.aplicacion.Servicio;


import primera.aplicacion.Extras.DecoradorIngrediente;
import primera.aplicacion.principal.Pedido;
import primera.aplicacion.principal.ProductoBase;

public class ServicioPedidos {

    public boolean validarCompatibilidad(Pedido pedido, String nombreIngrediente) {
        Pedido actual = pedido;
        while (actual instanceof DecoradorIngrediente) {
            actual = ((DecoradorIngrediente) actual).getPedidoWrappee();
        }

        String categoria = "";
        if (actual instanceof ProductoBase) {
            categoria = ((ProductoBase) actual).getCategoria();
        }

        // Reglas 
        if (categoria.equalsIgnoreCase("Bebida")) {
            // Las bebidas (como el Café) aceptan azucar y cafe
            return nombreIngrediente.equalsIgnoreCase("Azucar") || nombreIngrediente.equalsIgnoreCase("Crema");
        }
        if (categoria.equalsIgnoreCase("Saludable")) {
            // Las ensaladas aceptan Tomate, Pollo o Queso
            return nombreIngrediente.equalsIgnoreCase("Tomate") || 
                   nombreIngrediente.equalsIgnoreCase("Pollo") || 
                   nombreIngrediente.equalsIgnoreCase("Queso");
        }
        if (categoria.equalsIgnoreCase("Horno Leña")) {
            // Las pizzas aceptan ingredientes de pizza 
            return !nombreIngrediente.equalsIgnoreCase("Azucar") && !nombreIngrediente.equalsIgnoreCase("Crema");
        }

        return true;
    }
}