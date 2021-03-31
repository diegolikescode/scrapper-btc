package br.unisul.aula;

public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, String idade) {
        this.nome = nome;
        this.setIdade(idade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setIdade(String idade) {
        this.setIdade(Integer.parseInt(idade));
    }
}
