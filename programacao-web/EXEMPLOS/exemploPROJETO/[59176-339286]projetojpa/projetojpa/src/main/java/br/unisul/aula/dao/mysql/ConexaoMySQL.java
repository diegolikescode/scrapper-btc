package br.unisul.aula.dao.mysql;

import br.unisul.aula.dao.BaseDados;
import br.unisul.aula.dao.Conexao;

import java.sql.*;

public class ConexaoMySQL extends Conexao {

    public ConexaoMySQL(BaseDados baseDados) {
        super(baseDados);
    }

    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://"+this.getServidor()+":3306/"+
                    this.getDataBase();
            this.setConnection(
                    DriverManager.
                            getConnection(url,
                                    this.getUsuario(), this.getSenha()));
            this.setConectado(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this.setConectado(false);
        } catch (SQLException e) {
            e.printStackTrace();

            this.setConectado(false);
        }
    }

}
