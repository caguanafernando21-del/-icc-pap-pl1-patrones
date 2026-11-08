package primera.aplicacion.Ventana;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

    // =========================================================================
    // 💡 PUNTO CLAVE 1: VARIABLES Y COMPONENTES GLOBALES
    // Desde aquí podrás manipular dinámicamente la interfaz con tus clases.
    // =========================================================================
    private JPanel panelListaProductosOrden; // Contenedor donde agregarás los productos seleccionados
    private JLabel lblSubtotalBase;          // Para actualizar el subtotal base
    private JLabel lblExtrasAdicionales;     // Para actualizar el costo de extras
    private JLabel lblSubtotalNeto;          // Para actualizar el subtotal neto
    private JLabel lblIvaImpuestos;          // Para actualizar el IVA (ej. 18%)
    private JLabel lblTotalPagar;            // Para actualizar el monto total principal
    private JLabel lblTotalBotonRojo;        // Para actualizar el monto en el botón de "Terminar Pedido"
    private JPanel panelCuerpoTicket;        // Contenedor para renderizar tu recibo térmico digital

    private final Color COLOR_BG = new Color(11, 15, 25);         // #0b0f19
    private final Color COLOR_CARD = new Color(17, 23, 38);       // #111726
    private final Color COLOR_ITEM = new Color(23, 32, 51);       // #172033
    private final Color COLOR_ITEM_HOVER = new Color(30, 41, 59); 
    private final Color COLOR_BORDER = new Color(30, 41, 59);     
    private final Color COLOR_EMERALD = new Color(52, 211, 153);  
    private final Color COLOR_CYAN = new Color(34, 211, 238);     

    public VentanaPrincipal() {
        setTitle("Lumen POS - Terminal");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(COLOR_BG);
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 4, 0, 4);

        gbc.gridx = 0;
        gbc.weightx = 0.42; 
        contentPane.add(crearColumnaIzquierda(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.58; 
        contentPane.add(crearColumnaDerecha(), gbc);
    }

    private JPanel crearColumnaIzquierda() {
        JPanel col = new JPanel();
        col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
        col.setOpaque(false);

        // 1. PRODUCTOS BASE
        JPanel panelBase = crearTarjetaContenedora();
        panelBase.setLayout(new BorderLayout(0, 10));

        JPanel headerBase = new JPanel(new BorderLayout());
        headerBase.setOpaque(false);
        JLabel lblTituloBase = new JLabel("☰  PRODUCTOS BASE");
        lblTituloBase.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTituloBase.setForeground(Color.WHITE);

        JLabel lblBadgeBase = new JLabel(" 4 Disponibles ");
        lblBadgeBase.setOpaque(true);
        lblBadgeBase.setBackground(new Color(6, 78, 59));
        lblBadgeBase.setForeground(COLOR_EMERALD);
        lblBadgeBase.setFont(new Font("SansSerif", Font.BOLD, 10));

        headerBase.add(lblTituloBase, BorderLayout.WEST);
        headerBase.add(lblBadgeBase, BorderLayout.EAST);
        panelBase.add(headerBase, BorderLayout.NORTH);

        JPanel gridProductos = new JPanel(new GridLayout(2, 2, 8, 8));
        gridProductos.setOpaque(false);
        
        // =========================================================================
        // 💡 PUNTO CLAVE 2: CLICS EN PRODUCTOS BASE
        // Aquí es donde instanciarás o llamarás a tus clases de productos (ej. Cafe, Pizza)
        // =========================================================================
        gridProductos.add(crearBotonProductoBase("☕", "Café", "$3.50", "BEBIDA", false));
        gridProductos.add(crearBotonProductoBase("🥗", "Ensalada", "$7.50", "SALUDABLE", false));
        gridProductos.add(crearBotonProductoBase("🍕", "Pizza", "$12.00", "HORNO LEÑA", true));
        gridProductos.add(crearBotonProductoBase("🥪", "Sándwich", "$6.50", "ARTESANAL", false));
        
        panelBase.add(gridProductos, BorderLayout.CENTER);

        // 2. PERSONALIZAR EXTRAS
        JPanel panelExtras = crearTarjetaContenedora();
        panelExtras.setLayout(new BorderLayout(0, 8));

        JPanel headerExtras = new JPanel(new BorderLayout());
        headerExtras.setOpaque(false);
        JLabel lblTituloExtras = new JLabel("● PERSONALIZAR EXTRAS");
        lblTituloExtras.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTituloExtras.setForeground(COLOR_CYAN);

        JLabel lblSubtituloExtras = new JLabel("Editando: Pizza Artesanal");
        lblSubtituloExtras.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblSubtituloExtras.setForeground(new Color(148, 163, 184));

        JPanel subHeaderPanel = new JPanel(new GridLayout(2, 1));
        subHeaderPanel.setOpaque(false);
        subHeaderPanel.add(lblTituloExtras);
        subHeaderPanel.add(lblSubtituloExtras);

        JLabel lblAccionExtras = new JLabel(" Agregar / Quitar ");
        lblAccionExtras.setOpaque(true);
        lblAccionExtras.setBackground(COLOR_ITEM);
        lblAccionExtras.setForeground(new Color(148, 163, 184));
        lblAccionExtras.setFont(new Font("SansSerif", Font.PLAIN, 10));

        headerExtras.add(subHeaderPanel, BorderLayout.WEST);
        headerExtras.add(lblAccionExtras, BorderLayout.EAST);
        panelExtras.add(headerExtras, BorderLayout.NORTH);

        JPanel contenidoExtras = new JPanel();
        contenidoExtras.setLayout(new BoxLayout(contenidoExtras, BoxLayout.Y_AXIS));
        contenidoExtras.setOpaque(false);

        JLabel lblAnadidos = new JLabel("EXTRAS AÑADIDOS AL PRODUCTO:");
        lblAnadidos.setFont(new Font("SansSerif", Font.BOLD, 9));
        lblAnadidos.setForeground(new Color(148, 163, 184));
        lblAnadidos.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel panelChips = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelChips.setOpaque(false);
        panelChips.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelChips.add(crearChipExtra("Queso Mozzarella (+$1.50) ✕"));
        panelChips.add(crearChipExtra("Pepperoni Crujiente (+$2.00) ✕"));

        JLabel lblDisponibles = new JLabel("INGREDIENTES DISPONIBLES:");
        lblDisponibles.setFont(new Font("SansSerif", Font.BOLD, 9));
        lblDisponibles.setForeground(new Color(148, 163, 184));
        lblDisponibles.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel gridIngredientes = new JPanel(new GridLayout(2, 3, 6, 6));
        gridIngredientes.setOpaque(false);
        gridIngredientes.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // =========================================================================
        // 💡 PUNTO CLAVE 3: CLICS EN INGREDIENTES / EXTRAS
        // Aquí conectarás tus clases del paquete `Extras` (ej. Queso, Pepperoni, Pollo)
        // =========================================================================
        gridIngredientes.add(crearBotonIngrediente("🧂", "Azúcar", "+$0.30", false));
        gridIngredientes.add(crearBotonIngrediente("🥛", "Crema", "+$0.50", false));
        gridIngredientes.add(crearBotonIngrediente("🍅", "Tomate", "+$0.80", false));
        gridIngredientes.add(crearBotonIngrediente("🍗", "Pollo", "+$1.80", false));
        gridIngredientes.add(crearBotonIngrediente("🧀", "Queso", "+$1.50", true));
        gridIngredientes.add(crearBotonIngrediente("🍕", "Pepperoni", "+$2.00", true));

        contenidoExtras.add(lblAnadidos);
        contenidoExtras.add(Box.createVerticalStrut(4));
        contenidoExtras.add(panelChips);
        contenidoExtras.add(Box.createVerticalStrut(8));
        contenidoExtras.add(lblDisponibles);
        contenidoExtras.add(Box.createVerticalStrut(4));
        contenidoExtras.add(gridIngredientes);

        panelExtras.add(contenidoExtras, BorderLayout.CENTER);

        // 3. BOTÓN DE SALIDA
        JButton btnSalir = new JButton("🚪  Salir de la app / Cerrar Turno");
        configurarBotonPlano(btnSalir, COLOR_CARD, new Color(148, 163, 184), COLOR_ITEM_HOVER);
        btnSalir.setBorder(new LineBorder(COLOR_BORDER, 1, true));
        btnSalir.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnSalir.addActionListener(e -> System.exit(0));

        col.add(panelBase);
        col.add(Box.createVerticalStrut(10));
        col.add(panelExtras);
        col.add(Box.createVerticalStrut(10));
        col.add(btnSalir);

        return col;
    }

    private JPanel crearColumnaDerecha() {
        JPanel col = new JPanel();
        col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
        col.setOpaque(false);

        // 1. SECCIÓN ORDEN EN CURSO
        JPanel panelOrden = crearTarjetaContenedora();
        panelOrden.setLayout(new BorderLayout(0, 10));

        JPanel headerOrden = new JPanel(new BorderLayout());
        headerOrden.setOpaque(false);
        JLabel lblTituloOrden = new JLabel("●  ORDEN #1042 — EN CURSO");
        lblTituloOrden.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblTituloOrden.setForeground(Color.WHITE);

        JLabel lblMesa = new JLabel("Mesa: 04 (Salón)");
        lblMesa.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblMesa.setForeground(new Color(148, 163, 184));

        headerOrden.add(lblTituloOrden, BorderLayout.WEST);
        headerOrden.add(lblMesa, BorderLayout.EAST);
        panelOrden.add(headerOrden, BorderLayout.NORTH);

        JPanel contenedorCentral = new JPanel(new GridBagLayout());
        contenedorCentral.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 2, 0, 2);

        // =========================================================================
        // 💡 PUNTO CLAVE 4: LISTA DE PRODUCTOS Y DESGLOSE EN CURSO
        // Aquí inyectarás dinámicamente los componentes de tus clases de servicio (`ServicioPedidos.java`)
        // =========================================================================
        panelListaProductosOrden = new JPanel();
        panelListaProductosOrden.setLayout(new BoxLayout(panelListaProductosOrden, BoxLayout.Y_AXIS));
        panelListaProductosOrden.setBackground(COLOR_ITEM);
        panelListaProductosOrden.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(8, 8, 8, 8)
        ));

        JLabel lblMensajeVacio = new JLabel("PRODUCTOS E INGREDIENTES AGREGADOS:");
        lblMensajeVacio.setFont(new Font("SansSerif", Font.BOLD, 9));
        lblMensajeVacio.setForeground(new Color(148, 163, 184));
        panelListaProductosOrden.add(lblMensajeVacio);

        JScrollPane scrollProductos = new JScrollPane(panelListaProductosOrden);
        scrollProductos.setBorder(null);
        scrollProductos.setOpaque(false);
        scrollProductos.getViewport().setOpaque(false);

        gbc.gridx = 0;
        gbc.weightx = 0.58;
        contenedorCentral.add(scrollProductos, gbc);

        JPanel panelDesglose = new JPanel(new BorderLayout());
        panelDesglose.setBackground(COLOR_ITEM);
        panelDesglose.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel lblTituloDesglose = new JLabel("DESGLOSE FINANCIERO");
        lblTituloDesglose.setFont(new Font("SansSerif", Font.BOLD, 9));
        lblTituloDesglose.setForeground(new Color(148, 163, 184));

        JPanel panelValores = new JPanel(new GridLayout(4, 2, 4, 4));
        panelValores.setOpaque(false);

        lblSubtotalBase = crearLabelMoneda("$0.00");
        lblExtrasAdicionales = crearLabelMoneda("+$0.00");
        lblSubtotalNeto = crearLabelMoneda("$0.00");
        lblIvaImpuestos = crearLabelMoneda("$0.00");

        panelValores.add(crearLabelTextoDesglose("Subtotal Base:"));
        panelValores.add(lblSubtotalBase);
        panelValores.add(crearLabelTextoDesglose("Extras Adicionales:"));
        panelValores.add(lblExtrasAdicionales);
        panelValores.add(crearLabelTextoDesglose("Subtotal Neto:"));
        panelValores.add(lblSubtotalNeto);
        panelValores.add(crearLabelTextoDesglose("IVA / Impuestos (18%):"));
        panelValores.add(lblIvaImpuestos);

        JPanel panelTotal = new JPanel(new GridLayout(2, 1));
        panelTotal.setOpaque(false);
        JLabel lblTextoTotal = new JLabel("TOTAL A PAGAR");
        lblTextoTotal.setFont(new Font("SansSerif", Font.BOLD, 9));
        lblTextoTotal.setForeground(new Color(148, 163, 184));

        lblTotalPagar = new JLabel("$0.00");
        lblTotalPagar.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTotalPagar.setForeground(COLOR_EMERALD);

        panelTotal.add(lblTextoTotal);
        panelTotal.add(lblTotalPagar);

        panelDesglose.add(lblTituloDesglose, BorderLayout.NORTH);
        panelDesglose.add(panelValores, BorderLayout.CENTER);
        panelDesglose.add(panelTotal, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.weightx = 0.42;
        contenedorCentral.add(panelDesglose, gbc);

        panelOrden.add(contenedorCentral, BorderLayout.CENTER);

        // Botón inferior de terminar pedido
        JButton btnTerminarPedido = new JButton("✔  TERMINAR PEDIDO");
        configurarBotonPlano(btnTerminarPedido, new Color(225, 29, 72), Color.WHITE, new Color(244, 63, 94));
        btnTerminarPedido.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnTerminarPedido.setLayout(new BorderLayout());

        lblTotalBotonRojo = new JLabel("$0.00 [ENTER] ");
        lblTotalBotonRojo.setFont(new Font("Monospaced", Font.BOLD, 12));
        lblTotalBotonRojo.setForeground(Color.WHITE);
        btnTerminarPedido.add(lblTotalBotonRojo, BorderLayout.EAST);
        btnTerminarPedido.setPreferredSize(new Dimension(0, 42));

        panelOrden.add(btnTerminarPedido, BorderLayout.SOUTH);

        // 2. RECIBO DIGITAL Y COBRO
        JPanel panelInferiorDerecho = new JPanel(new GridBagLayout());
        panelInferiorDerecho.setOpaque(false);

        GridBagConstraints gbcInf = new GridBagConstraints();
        gbcInf.fill = GridBagConstraints.BOTH;
        gbcInf.weighty = 1.0;
        gbcInf.insets = new Insets(0, 2, 0, 2);

        JPanel panelRecibo = crearTarjetaContenedora();
        panelRecibo.setLayout(new BorderLayout(0, 6));

        JPanel headerRecibo = new JPanel(new BorderLayout());
        headerRecibo.setOpaque(false);
        JLabel lblTituloRecibo = new JLabel("●  RECIBO TÉRMICO DIGITAL EN VIVO");
        lblTituloRecibo.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblTituloRecibo.setForeground(new Color(251, 191, 36));

        JLabel lblTerminal = new JLabel("Terminal: POS-T800");
        lblTerminal.setFont(new Font("Monospaced", Font.PLAIN, 10));
        lblTerminal.setForeground(new Color(148, 163, 184));

        headerRecibo.add(lblTituloRecibo, BorderLayout.WEST);
        headerRecibo.add(lblTerminal, BorderLayout.EAST);
        panelRecibo.add(headerRecibo, BorderLayout.NORTH);

        // =========================================================================
        // 💡 PUNTO CLAVE 5: RECIBO TÉRMICO DIGITAL
        // Aquí podrás reemplazar el contenido para mostrar el ticket final impreso
        // =========================================================================
        panelCuerpoTicket = new JPanel();
        panelCuerpoTicket.setLayout(new BoxLayout(panelCuerpoTicket, BoxLayout.Y_AXIS));
        panelCuerpoTicket.setBackground(Color.WHITE);
        panelCuerpoTicket.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblHeaderTicket = new JLabel("LUMEN BISTRO & COFFEE");
        lblHeaderTicket.setFont(new Font("Monospaced", Font.BOLD, 11));
        lblHeaderTicket.setForeground(Color.BLACK);
        lblHeaderTicket.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEspacio = new JLabel("[ Esperando generación de recibo... ]");
        lblEspacio.setFont(new Font("Monospaced", Font.ITALIC, 10));
        lblEspacio.setForeground(Color.GRAY);
        lblEspacio.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCuerpoTicket.add(lblHeaderTicket);
        panelCuerpoTicket.add(Box.createVerticalStrut(10));
        panelCuerpoTicket.add(lblEspacio);

        panelRecibo.add(panelCuerpoTicket, BorderLayout.CENTER);

        gbcInf.gridx = 0;
        gbcInf.weightx = 0.72;
        panelInferiorDerecho.add(panelRecibo, gbcInf);

        JButton btnCobrar = new JButton("<html><center>💳<br><br><b>Cobrar / Enviar</b></center></html>");
        configurarBotonPlano(btnCobrar, new Color(21, 42, 45), COLOR_CYAN, new Color(27, 54, 58));
        btnCobrar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnCobrar.setBorder(new LineBorder(new Color(6, 182, 212, 100), 1, true));

        gbcInf.gridx = 1;
        gbcInf.weightx = 0.28;
        panelInferiorDerecho.add(btnCobrar, gbcInf);

        col.add(panelOrden);
        col.add(Box.createVerticalStrut(10));
        col.add(panelInferiorDerecho);

        return col;
    }

    private void configurarBotonPlano(JButton btn, Color bgNormal, Color fg, Color bgHover) {
        btn.setBackground(bgNormal);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bgHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgNormal);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btn.setBackground(bgNormal.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (btn.contains(e.getPoint())) {
                    btn.setBackground(bgHover);
                } else {
                    btn.setBackground(bgNormal);
                }
            }
        });
    }

    private JPanel crearTarjetaContenedora() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_CARD);
        panel.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));
        return panel;
    }

    private JButton crearBotonProductoBase(String emoji, String nombre, String precio, String etiqueta, boolean activo) {
        String colorTagBg = activo ? "#064e3b" : "#111726";
        String colorTagFg = activo ? "#34d399" : "#94a3b8";
        String colorTexto = "#ffffff";
        
        String htmlText = "<html><div style='text-align: left;'>" +
                "<span style='background-color: " + colorTagBg + "; color: " + colorTagFg + "; font-size: 7px; padding: 2px 4px;'> " + etiqueta + " </span><br/>" +
                "<b style='font-size: 11px; color: " + colorTexto + ";'>" + emoji + " " + nombre + "</b>" + (activo ? " <span style='color: #34d399;'>✓</span>" : "") + "<br/>" +
                "<span style='font-family: monospace; font-size: 10px; color: #34d399;'>" + precio + "</span>" +
                "</div></html>";

        JButton btn = new JButton(htmlText);
        Color bgBase = activo ? new Color(21, 39, 51) : COLOR_ITEM;
        configurarBotonPlano(btn, bgBase, Color.WHITE, activo ? new Color(27, 49, 64) : COLOR_ITEM_HOVER);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setVerticalAlignment(SwingConstants.TOP);
        btn.setBorder(new CompoundBorder(
                new LineBorder(activo ? COLOR_EMERALD : COLOR_BORDER, activo ? 2 : 1, true),
                new EmptyBorder(6, 6, 6, 6)
        ));

        // Aquí puedes enlazar la lógica de selección de tu producto base
        btn.addActionListener(e -> System.out.println("Producto presionado: " + nombre));
        return btn;
    }

    private JLabel crearChipExtra(String texto) {
        JLabel chip = new JLabel(texto);
        chip.setOpaque(true);
        chip.setBackground(new Color(4, 47, 46));
        chip.setForeground(COLOR_EMERALD);
        chip.setFont(new Font("SansSerif", Font.BOLD, 10));
        chip.setBorder(new CompoundBorder(
                new LineBorder(new Color(5, 150, 105, 100), 1, true),
                new EmptyBorder(3, 6, 3, 6)
        ));
        return chip;
    }

    private JButton crearBotonIngrediente(String emoji, String nombre, String precio, boolean activo) {
        String colorNombre = activo ? nametagColor(true) : "#ffffff";

        String htmlText = "<html><div style='text-align: left;'>" +
                "<b style='font-size: 10px; color: " + colorNombre + ";'>" + emoji + " " + nombre + "</b><br/>" +
                "<span style='font-family: monospace; font-size: 9px; color: #34d399;'>" + precio + "</span>" +
                "</div></html>";

        JButton btn = new JButton(htmlText);
        Color bgBase = activo ? new Color(20, 41, 40) : COLOR_ITEM;
        configurarBotonPlano(btn, bgBase, Color.WHITE, activo ? new Color(25, 52, 50) : COLOR_ITEM_HOVER);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setVerticalAlignment(SwingConstants.TOP);
        btn.setBorder(new CompoundBorder(
                new LineBorder(activo ? COLOR_EMERALD : COLOR_BORDER, 1, true),
                new EmptyBorder(6, 6, 6, 6)
        ));

        // Aquí puedes enlazar la lógica de selección de ingredientes/extras
        btn.addActionListener(e -> System.out.println("Ingrediente presionado: " + nombre));
        return btn;
    }

    private String nametagColor(boolean activo) {
        return activo ? "#a7f3d0" : "#ffffff";
    }

    private JLabel crearLabelTextoDesglose(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lbl.setForeground(new Color(148, 163, 184));
        return lbl;
    }

    private JLabel crearLabelMoneda(String valor) {
        JLabel lbl = new JLabel(valor, SwingConstants.RIGHT);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 10));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new VentanaPrincipal().setVisible(true);
        });
    }
}