package br.unisul.aula.dao;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class Conexao {
    private String usuario, senha, servidor, dataBase;
    private Connection connection;
    private boolean conectado;
    private ResultSet dados;

    public Conexao(BaseDados baseDados) {
        this.setServidor(baseDados.getServidor());
        this.setUsuario(baseDados.getUsuario());
        this.setDataBase(baseDados.getDatabase());
        this.setSenha(baseDados.getSenha());

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public abstract void conectar();


}
