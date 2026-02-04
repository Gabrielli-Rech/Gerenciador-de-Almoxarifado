package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import model.Produto;
import java.util.List;

public class AlmoxarifadoGrafico extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnAdd, btnEdit, btnSaida, btnExcluir;
    private JLabel lblTotal, lblCriticos;

    public AlmoxarifadoGrafico() {
        setTitle("Controle do Almoxarifado");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 242, 245));
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout(20, 20));

        // --- DASHBOARD (Cards) ---
        JPanel painelDash = new JPanel(new GridLayout(1, 2, 30, 0));
        painelDash.setOpaque(false);
        painelDash.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 25));
        
        lblTotal = criarCard(painelDash, "ESTOQUE TOTAL", new Color(52, 152, 219));
        lblCriticos = criarCard(painelDash, "ALERTAS CRÍTICOS", new Color(231, 76, 60));
        add(painelDash, BorderLayout.NORTH);

        // --- TABELA ESTILIZADA ---
        modelo = new DefaultTableModel(new Object[]{"Produto", "Qtd", "Mínimo", "Status"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        
        tabela = new JTable(modelo);
        tabela.setRowHeight(35);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.setSelectionBackground(new Color(232, 240, 254));
        tabela.setSelectionForeground(Color.BLACK);
        tabela.setGridColor(new Color(230, 230, 230));

        // Centralizar e colorir status
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                Component comp = super.getTableCellRendererComponent(t, v, s, f, r, c);
                setHorizontalAlignment(JLabel.CENTER);
                if (c == 3) {
                    setForeground("CRÍTICO".equals(v) ? new Color(192, 57, 43) : new Color(39, 174, 96));
                    setFont(getFont().deriveFont(Font.BOLD));
                } else {
                    setForeground(Color.DARK_GRAY);
                }
                return comp;
            }
        };
        for(int i=0; i<4; i++) tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        JPanel containerTabela = new JPanel(new BorderLayout());
        containerTabela.setOpaque(false);
        containerTabela.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        containerTabela.add(scroll);
        add(containerTabela, BorderLayout.CENTER);

        // --- BARRA LATERAL DE BOTÕES (Menu) ---
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
        acoes.setBackground(Color.WHITE);
        acoes.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

        btnAdd = criarBotao(" Novo ", new Color(30, 144, 255));
        btnEdit = criarBotao(" Editar ", new Color(128, 128, 128));
        btnSaida = criarBotao(" Saída ", new Color(255, 196, 15));
        btnExcluir = criarBotao("Excluir ", new Color(231, 76, 60));

        acoes.add(btnExcluir); acoes.add(btnSaida); acoes.add(btnEdit); acoes.add(btnAdd);
        add(acoes, BorderLayout.SOUTH);
    }

    private JLabel criarCard(JPanel p, String t, Color c) {
        JPanel card = new JPanel(new BorderLayout(0, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel tit = new JLabel(t);
        tit.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tit.setForeground(Color.GRAY);
        
        JLabel val = new JLabel("0");
        val.setFont(new Font("Segoe UI", Font.BOLD, 28));
        val.setForeground(c);

        card.add(tit, BorderLayout.NORTH);
        card.add(val, BorderLayout.CENTER);
        p.add(card);
        return val;
    }

    private JButton criarBotao(String txt, Color c) {
        JButton b = new JButton(txt);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(130, 40));
        return b;
    }

    public void atualizarTabela(List<Produto> lista) {
        modelo.setRowCount(0);
        int crit = 0;
        for (Produto p : lista) {
            String s = p.temEstoqueBaixo() ? "CRÍTICO" : "OK";
            if (p.temEstoqueBaixo()) crit++;
            modelo.addRow(new Object[]{p.getNome(), p.getQuantidade(), p.getEstoqueMinimo(), s});
        }
        lblTotal.setText(String.valueOf(lista.size()));
        lblCriticos.setText(String.valueOf(crit));
    }

    public JTable getTabela() { return tabela; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnSaida() { return btnSaida; }
    public JButton getBtnExcluir() { return btnExcluir; }
}