package primera.aplicacion.principal;

import java.util.HashMap;
import java.util.Map;

public class RegistroProductosBas {
    private Map<String, Pedido> prototipos = new HashMap<>();

    public void registrarPrototipo(String clave, Pedido prototipo) {
        prototipos.put(clave, prototipo);
    }

    public Pedido obtenerPrototipo(String clave) {
        Pedido prototipo = prototipos.get(clave);
        if (prototipo != null) {
            return prototipo.clonar();
        }
        return null;
    }
}