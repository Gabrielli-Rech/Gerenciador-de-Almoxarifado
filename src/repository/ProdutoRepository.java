package repository;

import model.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();
    private final String ARQUIVO = "estoque.csv";

    public ProdutoRepository() { carregarDados(); }

    public void salvar(Produto p) { 
        produtos.add(p); 
        sincronizar();
    }

    public void excluir(String nome) {
        produtos.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
        sincronizar();
    }

    public List<Produto> listarTodos() { return produtos; }

    public Produto buscarPorNome(String nome) {
        return produtos.stream().filter(p -> p.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    public void sincronizar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Produto p : produtos) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void carregarDados() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(",");
                produtos.add(new Produto(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2])));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}