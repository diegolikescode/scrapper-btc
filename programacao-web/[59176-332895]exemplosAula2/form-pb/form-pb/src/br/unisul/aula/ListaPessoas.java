package br.unisul.aula;

import java.util.ArrayList;
import java.util.List;

public class ListaPessoas {
    private static ListaPessoas instance = new ListaPessoas();
    private static List<Pessoa> pessoaList = new ArrayList<>();
    private ListaPessoas(){}

    public static ListaPessoas getInstance(){
        return instance;
    }

    public void incluir(Pessoa pessoa){
        pessoaList.add(pessoa);
    }

    public List<Pessoa> listarTodas(){
        return pessoaList;
    }
}
