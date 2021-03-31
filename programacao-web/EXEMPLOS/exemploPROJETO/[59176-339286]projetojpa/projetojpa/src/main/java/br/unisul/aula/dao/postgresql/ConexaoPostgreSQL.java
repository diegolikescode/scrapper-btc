package br.unisul.aula.dao.postgresql;

import br.unisul.aula.dao.BaseDados;
import br.unisul.aula.dao.Conexao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL extends Conexao {


    public ConexaoPostgreSQL(BaseDados baseDados) {
        super(baseDados);
    }

    @Override
    public void conectar() {
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://"+this.getServidor()+":5432/"+
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
