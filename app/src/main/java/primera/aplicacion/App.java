package primera.aplicacion;

import primera.aplicacion.Ventana.VentanaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {
    public static void main(String[] args) {
        // Lanzar la aplicación gráfica del POS
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            
            new VentanaPrincipal().setVisible(true);
        });
    }
}