package model;

public class Produto {
    private String nome;
    private int quantidade;
    private int estoqueMinimo;

    public Produto(String nome, int quantidade, int estoqueMinimo) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public int getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }

    public boolean remover(int qtd) {
        if (qtd > 0 && qtd <= this.quantidade) {
            this.quantidade -= qtd;
            return true;
        }
        return false;
    }

    public boolean temEstoqueBaixo() { return this.quantidade <= this.estoqueMinimo; }

    public String toCSV() {
        return nome + "," + quantidade + "," + estoqueMinimo;
    }
}