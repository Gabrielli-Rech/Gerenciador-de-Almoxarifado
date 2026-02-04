package controller;

import model.Produto;
import repository.ProdutoRepository;
import javax.swing.JOptionPane;

public class AlmoxarifadoController {
    private final ProdutoRepository repo;

    public AlmoxarifadoController(ProdutoRepository repo) {
        this.repo = repo;
    }

    public void cadastrar(String nome, int qtd, int min) {
        if (repo.buscarPorNome(nome) != null) {
            JOptionPane.showMessageDialog(null, "Produto já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        repo.salvar(new Produto(nome, qtd, min));
    }

    public void editar(String nomeAntigo, String novoNome, int novaQtd, int novoMin) {
    Produto p = repo.buscarPorNome(nomeAntigo);
    if (p != null) {
        p.setNome(novoNome);
        p.setQuantidade(novaQtd);
        p.setEstoqueMinimo(novoMin);
        repo.sincronizar(); // Salva no CSV
        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
    }
}
    public void registrarSaida(String nome, int qtd) {
        Produto p = repo.buscarPorNome(nome);
        if (p != null && p.remover(qtd)) {
            repo.sincronizar();
            if (p.temEstoqueBaixo()) {
                JOptionPane.showMessageDialog(null, "Alerta: Estoque baixo para " + p.getNome(), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Saída inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluir(String nome) {
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja excluir " + nome + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) repo.excluir(nome);
    }

}