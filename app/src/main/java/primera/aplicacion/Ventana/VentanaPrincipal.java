package primera.aplicacion.Ventana;

import primera.aplicacion.Extras.*;
import primera.aplicacion.Servicio.ServicioPedidos;
import primera.aplicacion.principal.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {
    private RegistroProductosBas registroProductos = new RegistroProductosBas();
    private ServicioPedidos servicioPedidos = new ServicioPedidos();
    private Pedido pedidoActual = null;

    private JPanel panelListaOrden = new JPanel();
    private JPanel panelChipsExtras = new JPanel();
    private JPanel panelCuerpoTicket = new JPanel();
    private JLabel lblSubtotal = new JLabel("$0.00"), lblExtras = new JLabel("+$0.00");
    private JLabel lblNeto = new JLabel("$0.00"), lblIva = new JLabel("$0.00"), lblTotal = new JLabel("$0.00");
    private JLabel lblEditando = new JLabel("Seleccione un producto base para comenzar");

    private final Color BG = new Color(11, 15, 25), CARD = new Color(17, 23, 38), ITEM = new Color(23, 32, 51);
    private final Color EMERALD = new Color(52, 211, 153), CYAN = new Color(34, 211, 238), BORDER = new Color(30, 41, 59);

    public VentanaPrincipal() {
        setTitle("Lumen POS - Terminal");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new GridLayout(1, 2, 12, 12));
        main.setBackground(BG);
        main.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(main);

        main.add(crearIzquierda());
        main.add(crearDerecha());
    }

    private JPanel crearIzquierda() {
        JPanel col = new JPanel();
        col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
        col.setOpaque(false);

        // 1. Productos Base
        JPanel pBase = tarjeta("☰ PRODUCTOS BASE");
        JPanel gridP = new JPanel(new GridLayout(2, 2, 8, 8));
        gridP.setOpaque(false);
        gridP.add(boton("☕", "Café", "$3.50", "CAFE"));
        gridP.add(boton("🥗", "Ensalada", "$7.50", "ENSALADA"));
        gridP.add(boton("🍕", "Pizza", "$12.00", "PIZZA"));
        pBase.add(gridP, BorderLayout.CENTER);

        // 2. Personalizar Extras
        JPanel pExtras = tarjeta("● PERSONALIZAR EXTRAS");
        pExtras.add(lblEditando, BorderLayout.NORTH);
        lblEditando.setForeground(CYAN);
        lblEditando.setFont(new Font("SansSerif", Font.PLAIN, 11));

        JPanel contenedorExtrasCentro = new JPanel();
        contenedorExtrasCentro.setLayout(new BoxLayout(contenedorExtrasCentro, BoxLayout.Y_AXIS));
        contenedorExtrasCentro.setOpaque(false);

        panelChipsExtras.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
        panelChipsExtras.setOpaque(false);
        contenedorExtrasCentro.add(panelChipsExtras);
        contenedorExtrasCentro.add(Box.createVerticalStrut(8));

        JPanel gridE = new JPanel(new GridLayout(2, 3, 6, 6));
        gridE.setOpaque(false);
        String[][] extras = {{"🧂","Azucar","+$0.30"},{"🥛","Crema","+$0.50"},{"🍅","Tomate","+$0.80"},{"🍗","Pollo","+$1.80"},{"🧀","Queso","+$1.50"},{"🍕","Pepperoni","+$2.00"}};
        for (String[] ex : extras) {
            JButton b = new JButton("<html><b>" + ex[0] + " " + ex[1] + "</b><br><font color='#34d399'>" + ex[2] + "</font></html>");
            estilizarBoton(b, ITEM);
            b.addActionListener(e -> agregarIngrediente(ex[1]));
            gridE.add(b);
        }
        contenedorExtrasCentro.add(gridE);
        pExtras.add(contenedorExtrasCentro, BorderLayout.CENTER);

        // Boton Limpiar Orden
        JButton btnLimpiar = new JButton("Reiniciar Pedido");
        estilizarBoton(btnLimpiar, new Color(153, 27, 27));
        btnLimpiar.addActionListener(e -> reiniciarOrden());

        col.add(pBase);
        col.add(Box.createVerticalStrut(10));
        col.add(pExtras);
        col.add(Box.createVerticalStrut(10));
        col.add(btnLimpiar);
        return col;
    }

    private JPanel crearDerecha() {
        JPanel col = new JPanel();
        col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
        col.setOpaque(false);

        // Orden en curso
        JPanel pOrden = tarjeta("● ORDEN EN CURSO");
        panelListaOrden.setLayout(new BoxLayout(panelListaOrden, BoxLayout.Y_AXIS));
        panelListaOrden.setBackground(ITEM);
        panelListaOrden.setBorder(new EmptyBorder(6,6,6,6));
        
        JScrollPane scroll = new JScrollPane(panelListaOrden);
        scroll.setOpaque(false); scroll.getViewport().setOpaque(false); scroll.setBorder(null);

        JPanel pDesglose = new JPanel(new GridLayout(5, 2, 2, 2));
        pDesglose.setOpaque(false);
        pDesglose.add(txt("Subtotal Base:")); pDesglose.add(lblSubtotal);
        pDesglose.add(txt("Extras:")); pDesglose.add(lblExtras);
        pDesglose.add(txt("Neto:")); pDesglose.add(lblNeto);
        pDesglose.add(txt("IVA (15%):")); pDesglose.add(lblIva);
        pDesglose.add(txt("TOTAL:")); pDesglose.add(lblTotal);
        lblTotal.setForeground(EMERALD); lblTotal.setFont(new Font("Monospaced", Font.BOLD, 14));

        JPanel split = new JPanel(new GridLayout(1, 2, 8, 8));
        split.setOpaque(false);
        split.add(scroll); split.add(pDesglose);
        pOrden.add(split, BorderLayout.CENTER);

        // Boton Terminar Pedido
        JButton btnTerminar = new JButton("TERMINAR PEDIDO Y ENVIAR");
        estilizarBoton(btnTerminar, new Color(225, 29, 72));
        btnTerminar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnTerminar.addActionListener(e -> terminarPedido());
        pOrden.add(btnTerminar, BorderLayout.SOUTH);

        // Recibo Térmico
        JPanel pRecibo = tarjeta("● RECIBO TÉRMICO DIGITAL");
        panelCuerpoTicket.setLayout(new BoxLayout(panelCuerpoTicket, BoxLayout.Y_AXIS));
        panelCuerpoTicket.setBackground(Color.WHITE);
        pRecibo.add(new JScrollPane(panelCuerpoTicket), BorderLayout.CENTER);

        col.add(pOrden);
        col.add(Box.createVerticalStrut(10));
        col.add(pRecibo);
        return col;
    }

    private void seleccionar(String tipo) {
        registroProductos.registrarPrototipo("CAFE", new Cafe());
        registroProductos.registrarPrototipo("ENSALADA", new Ensalada());
        registroProductos.registrarPrototipo("PIZZA", new Pizza());
        pedidoActual = registroProductos.obtenerPrototipo(tipo);
        lblEditando.setText("Editando: " + ((ProductoBase)pedidoActual).getNombre());
        actualizar();
    }

    private void agregarIngrediente(String ing) {
        if (pedidoActual == null) {
            JOptionPane.showMessageDialog(this, "Primero seleccione un producto base.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!servicioPedidos.validarCompatibilidad(pedidoActual, ing)) {
            JOptionPane.showMessageDialog(this, "Ingrediente no compatible con este producto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch (ing) {
            case "Azucar": pedidoActual = new Azucar(pedidoActual); break;
            case "Crema": pedidoActual = new Crema(pedidoActual); break;
            case "Tomate": pedidoActual = new Tomate(pedidoActual); break;
            case "Pollo": pedidoActual = new Pollo(pedidoActual); break;
            case "Queso": pedidoActual = new Queso(pedidoActual); break;
            case "Pepperoni": pedidoActual = new Pepperoni(pedidoActual); break;
        }
        actualizar();
    }

    private void terminarPedido() {
        if (pedidoActual == null) {
            JOptionPane.showMessageDialog(this, "No hay ninguna orden en curso para terminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, 
            "¡Pedido terminado y enviado a cocina con éxito!\nID de Pedido: " + pedidoActual.getId(), 
            "Orden Procesada", 
            JOptionPane.INFORMATION_MESSAGE);

        reiniciarOrden();
    }

    private void reiniciarOrden() {
        pedidoActual = null;
        lblEditando.setText("Seleccione un producto base para comenzar");
        panelListaOrden.removeAll();
        panelChipsExtras.removeAll();
        panelCuerpoTicket.removeAll();
        lblSubtotal.setText("$0.00"); lblExtras.setText("+$0.00");
        lblNeto.setText("$0.00"); lblIva.setText("$0.00"); lblTotal.setText("$0.00");
        repaint(); revalidate();
    }

    private void actualizar() {
        if (pedidoActual == null) return;
        panelListaOrden.removeAll(); panelChipsExtras.removeAll(); panelCuerpoTicket.removeAll();

        double total = pedidoActual.getCosto();
        Pedido base = pedidoActual;
        while (base instanceof DecoradorIngrediente) base = ((DecoradorIngrediente)base).getPedidoWrappee();
        double costoBase = base.getCosto();
        double extras = total - costoBase;
        double iva = total * 0.15;
        double finalTotal = total + iva;

        JLabel item = new JLabel("<html><b>1x " + ((ProductoBase)base).getNombre() + "</b><br><font color='#34d399'>" + pedidoActual.getDescripcion().replace("\n","<br>") + "</font></html>");
        item.setForeground(Color.WHITE);
        panelListaOrden.add(item);

        Pedido tmp = pedidoActual;
        while (tmp instanceof DecoradorIngrediente) {
            JLabel chip = new JLabel(tmp.getClass().getSimpleName() + " ✕");
            chip.setOpaque(true); chip.setBackground(new Color(4,47,46)); chip.setForeground(EMERALD);
            panelChipsExtras.add(chip);
            tmp = ((DecoradorIngrediente)tmp).getPedidoWrappee();
        }

        lblSubtotal.setText(String.format("$%.2f", costoBase));
        lblExtras.setText(String.format("+$%.2f", extras));
        lblNeto.setText(String.format("$%.2f", total));
        lblIva.setText(String.format("$%.2f", iva));
        lblTotal.setText(String.format("$%.2f", finalTotal));

        JLabel ticket = new JLabel("<html><center><b>Tienda de comida </b><br>" +
                "Ticket ID: " + pedidoActual.getId() + "<br>" +
                "------------------------------------------<br>" +
                pedidoActual.getDescripcion().replace("\n","<br>") + "<br>" +
                "------------------------------------------<br>" +
                "SUBTOTAL: $" + String.format("%.2f", total) + "<br>" +
                "I.V.A. (15%): $" + String.format("%.2f", iva) + "<br>" +
                "<b>TOTAL: $" + String.format("%.2f", finalTotal) + "</b><br><br>" +
                "<i>¡Gracias por su visita!</i></center></html>");
        ticket.setForeground(Color.BLACK);
        panelCuerpoTicket.add(ticket);

        repaint(); revalidate();
    }

    private JPanel tarjeta(String titulo) {
        JPanel p = new JPanel(new BorderLayout(0, 5));
        p.setBackground(CARD);
        p.setBorder(new CompoundBorder(new LineBorder(BORDER, 1, true), new EmptyBorder(10, 10, 10, 10)));
        JLabel lbl = new JLabel(titulo); lbl.setForeground(Color.WHITE); lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        p.add(lbl, BorderLayout.NORTH);
        return p;
    }

    private JButton boton(String emoji, String nom, String precio, String tipo) {
        JButton b = new JButton("<html><b>" + emoji + " " + nom + "</b><br><font color='#34d399'>" + precio + "</font></html>");
        estilizarBoton(b, ITEM);
        b.addActionListener(e -> seleccionar(tipo));
        return b;
    }

    private void estilizarBoton(JButton b, Color bg) {
        b.setBackground(bg); 
        b.setForeground(Color.WHITE); 
        b.setFocusPainted(false); 
        b.setContentAreaFilled(false); 
        b.setOpaque(true);
        b.setBorder(new LineBorder(BORDER, 1, true));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setBackground(bg.brighter()); }
            public void mouseExited(MouseEvent e) { b.setBackground(bg); }
        });
    }

    private JLabel txt(String s) {
        JLabel l = new JLabel(s); l.setForeground(new Color(148, 163, 184)); l.setFont(new Font("SansSerif", Font.PLAIN, 10)); return l;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}