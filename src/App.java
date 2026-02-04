import controller.AlmoxarifadoController;
import repository.ProdutoRepository;
import view.AlmoxarifadoGrafico;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}

        ProdutoRepository repo = new ProdutoRepository();
        AlmoxarifadoController controller = new AlmoxarifadoController(repo);
        AlmoxarifadoGrafico view = new AlmoxarifadoGrafico();

        SwingUtilities.invokeLater(() -> {
            view.atualizarTabela(repo.listarTodos());

            // AÇÃO: NOVO (Botão Verde)
            view.getBtnAdd().addActionListener(e -> {
                String nome = JOptionPane.showInputDialog(view, "Nome do Novo Produto:");
                if (nome != null && !nome.trim().isEmpty()) {
                    try {
                        int q = Integer.parseInt(JOptionPane.showInputDialog(view, "Qtd Inicial:"));
                        int m = Integer.parseInt(JOptionPane.showInputDialog(view, "Mínimo:"));
                        controller.cadastrar(nome, q, m);
                        view.atualizarTabela(repo.listarTodos());
                    } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(view, "Erro: Use apenas números!"); }
                }
            });

            // AÇÃO: EDITAR (Botão Azul)
            view.getBtnEdit().addActionListener(e -> {
                int row = view.getTabela().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(view, "Selecione um produto para editar!");
                    return;
                }
                String nomeAtual = (String) view.getTabela().getValueAt(row, 0);
                int qtdAtual = (int) view.getTabela().getValueAt(row, 1);
                int minAtual = (int) view.getTabela().getValueAt(row, 2);

                String novoNome = JOptionPane.showInputDialog(view, "Novo Nome:", nomeAtual);
                if (novoNome != null) {
                    try {
                        int nQtd = Integer.parseInt(JOptionPane.showInputDialog(view, "Nova Quantidade:", qtdAtual));
                        int nMin = Integer.parseInt(JOptionPane.showInputDialog(view, "Novo Mínimo:", minAtual));
                        controller.editar(nomeAtual, novoNome, nQtd, nMin);
                        view.atualizarTabela(repo.listarTodos());
                    } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(view, "Erro nos valores!"); }
                }
            });

            // AÇÃO: SAÍDA (Botão Amarelo)
            view.getBtnSaida().addActionListener(e -> {
                int row = view.getTabela().getSelectedRow();
                if (row != -1) {
                    String n = (String) view.getTabela().getValueAt(row, 0);
                    String qStr = JOptionPane.showInputDialog(view, "Quantidade para Retirar de " + n + ":");
                    if (qStr != null) {
                        controller.registrarSaida(n, Integer.parseInt(qStr));
                        view.atualizarTabela(repo.listarTodos());
                    }
                }
            });

            // AÇÃO: EXCLUIR (Botão Vermelho)
            view.getBtnExcluir().addActionListener(e -> {
                int row = view.getTabela().getSelectedRow();
                if (row != -1) {
                    controller.excluir((String) view.getTabela().getValueAt(row, 0));
                    view.atualizarTabela(repo.listarTodos());
                }
            });

            view.setVisible(true);
        });
    }
}