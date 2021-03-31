package br.unisul.aula.dao.postgresql;

import br.unisul.aula.dao.BaseDados;
import br.unisul.aula.dao.ParticipanteDAO;
import br.unisul.aula.dao.ProjetoDAO;
import br.unisul.aula.modelo.Participante;
import br.unisul.aula.modelo.Projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteDAOPostgreSQL implements ParticipanteDAO {
    public boolean save(Participante participante) {
        boolean resultado = false;
        ConexaoPostgreSQL minhaConexaoPostgreSQL = new ConexaoPostgreSQL(BaseDados.POSTGRESQL);
        minhaConexaoPostgreSQL.conectar();
        Connection conn = minhaConexaoPostgreSQL.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = null;
            if (participante.getIdParticipante() != 0) {
                sql = "update participantes set \"nmParticipante\" = ?,\"nmCargo\" = ? where \"idParticipantes\" = ?";
            } else {
                sql = "insert into participantes(\"idParticipantes\",\"nmParticipante\", \"nmCargo\") values(nextval('seq_participante'), ?,?)";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, participante.getNome());
            pstmt.setString(2, participante.getCargo());
            if (participante.getIdParticipante() > 0) {
                pstmt.setInt(3, participante.getIdParticipante());
            }
            System.out.println("projetos: " + participante.getProjetos().size());
            if (participante.getProjetos().size() > 0) {
                saveProjetos(participante);
            }
            pstmt.executeUpdate();
            resultado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }

    public boolean saveProjetos(Participante participante) {
        boolean resultado = false;
        ConexaoPostgreSQL minhaConexaoPostgreSQL = new ConexaoPostgreSQL(BaseDados.POSTGRESQL);
        minhaConexaoPostgreSQL.conectar();
        Connection conn = minhaConexaoPostgreSQL.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sqlDel = "delete from controles where \"idParticipantes\" = ?";
            PreparedStatement pstmtDel = conn.prepareStatement(sqlDel);
            pstmtDel.setInt(1,participante.getIdParticipante());
            pstmtDel.executeUpdate();
            resultado = true;

            for (Projeto projeto : participante.getProjetos()) {
                String sql = "insert into controles(\"idProjetos\", \"idParticipantes\") values( ?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, projeto.getIdProjeto());
                pstmt.setInt(2, participante.getIdParticipante());
                pstmt.executeUpdate();
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }

    public boolean delete(int primaryKey) {
        boolean resultado = false;
        ConexaoPostgreSQL minhaConexaoPostgreSQL = new ConexaoPostgreSQL(BaseDados.POSTGRESQL);
        minhaConexaoPostgreSQL.conectar();
        Connection conn = minhaConexaoPostgreSQL.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "delete from participantes where \"idParticipantes\" = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, primaryKey);
            pstmt.executeUpdate();
            resultado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }

    @Override
    public Participante findById(int primaryKey) {
        Participante participante = null;
        ConexaoPostgreSQL minhaConexaoPostgreSQL = new ConexaoPostgreSQL(BaseDados.POSTGRESQL);
        minhaConexaoPostgreSQL.conectar();
        Connection conn = minhaConexaoPostgreSQL.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select \"idParticipantes\", \"nmParticipante\", \"nmCargo\" " +
                    "from participantes where \"idParticipantes\" = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, primaryKey);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                participante = new Participante();
                participante.setIdParticipante(rs.getInt("idParticipantes"));
                participante.setNome(rs.getString("nmParticipante"));
                participante.setCargo(rs.getString("nmCargo"));

                String sqlProjeto = "select \"idProjetos\" from controles where \"idParticipantes\" = ?";
                PreparedStatement pstmtProjeto = conn.prepareStatement(sqlProjeto);
                pstmtProjeto.setInt(1,participante.getIdParticipante());
                ResultSet rsProjeto = pstmtProjeto.executeQuery();
                while (rsProjeto.next()) {
                    int projetoId = rsProjeto.getInt("idProjetos");
                    ProjetoDAO daoProjeto = new ProjetoDAOPostgreSQL();
                    participante.setProjetos(daoProjeto.findById(projetoId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return participante;
    }

    public Participante findByNome(String nome) {
        Participante participante = null;
        ConexaoPostgreSQL minhaConexaoPostgreSQL = new ConexaoPostgreSQL(BaseDados.POSTGRESQL);
        minhaConexaoPostgreSQL.conectar();
        Connection conn = minhaConexaoPostgreSQL.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select \"idParticipantes\", \"nmParticipante\", \"nmCargo\" from participantes where \"nmParticipante\" = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                participante = new Participante();
                participante.setIdParticipante(rs.getInt("idParticipantes"));
                participante.setNome(rs.getString("nmParticipante"));
                participante.setCargo(rs.getString("nmCargo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return participante;
    }

    public List findAll() {
        Participante participante = null;
        ConexaoPostgreSQL minhaConexaoPostgreSQL = new ConexaoPostgreSQL(BaseDados.POSTGRESQL);
        minhaConexaoPostgreSQL.conectar();
        Connection conn = minhaConexaoPostgreSQL.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Participante> lista = new ArrayList<Participante>();
        try {
            String sql = "select \"idParticipantes\", \"nmParticipante\", \"nmCargo\" from participantes";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                participante = new Participante();
                participante.setIdParticipante(rs.getInt("idParticipantes"));
                participante.setNome(rs.getString("nmParticipante"));
                participante.setCargo(rs.getString("nmCargo"));
                carregaParticipantes(participante);
                lista.add(participante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }

    public boolean carregaParticipantes(Participante participante) {
        boolean resultado = false;
        Projeto projeto = null;
        ConexaoPostgreSQL minhaConexaoPostgreSQL = new ConexaoPostgreSQL(BaseDados.POSTGRESQL);
        minhaConexaoPostgreSQL.conectar();
        Connection conn = minhaConexaoPostgreSQL.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select p.idprojeto, p.descricao ,p.datainicio, p.datafim, p.situacao, " +
                    "p.percentualconcluido from projetos p, controles a " +
                    "where p.idprojeto = a.\"idProjetos\" \n" +
                    "and a.\"idParticipantes\" = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, participante.getIdParticipante());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("idProjetos"));
                projeto.setDescricao(rs.getString("nmDescricao"));
                projeto.setDataInicio(rs.getString("dtInicio"));
                projeto.setDataFim(rs.getString("dtFinal"));
                projeto.setPercentualConcluido(rs.getInt("nrPercConcluido"));
                projeto.setSituacao(rs.getString("nmSituacao"));
                participante.getProjetos().add(projeto);
            }
            resultado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }

}
