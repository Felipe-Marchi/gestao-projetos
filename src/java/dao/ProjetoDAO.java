package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAOT;
import entidade.Projeto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author felip
 */
public class ProjetoDAO implements IDAOT<Projeto> {

    @Override
    public String salvar(Projeto o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "insert into projeto values "
                    + "(default,"
                    + " '" + o.getNome() + "',"
                    + " '" + o.getDescricao() + "',"
                    + " '" + o.getDataInicio() + "',"
                    + " '" + o.getStatus() + "',"
                    + " " + o.getUsuarioId() + ")";

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao inserir projeto: " + e);
            return e.toString();
        }
    }

    @Override
    public String editar(Projeto o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE projeto "
                    + "SET nome = '" + o.getNome()
                    + "', descricao = '" + o.getDescricao()
                    + "', dataInicio = '" + o.getDataInicio()
                    + "', status = '" + o.getStatus()
                    + "', usuario_id = " + o.getUsuarioId()
                    + " WHERE id = " + o.getId();

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar projeto: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        Connection connection = null;
        Statement st = null;

        try {
            connection = ConexaoBD.getInstance().getConnection();
            st = connection.createStatement();

            // Recupera os IDs de requisitos associados ao projeto
            ResultSet rsRequisitos = st.executeQuery("SELECT id FROM requisito WHERE projeto_id = " + id);

            // Lista para armazenar os IDs dos requisitos
            List<Integer> requisitoIds = new ArrayList<>();

            // Armazena os IDs dos requisitos na lista
            while (rsRequisitos.next()) {
                requisitoIds.add(rsRequisitos.getInt("id"));
            }
            
            // Exclui todas as versões associadas aos requisitos
            for (int requisitoId : requisitoIds) {
                String deleteVersoes = "DELETE FROM historico_versoes WHERE id_requisito = " + requisitoId;
                int retVersoes = st.executeUpdate(deleteVersoes);
                System.out.println("Deleted versions for requisito " + requisitoId + ": " + retVersoes);
            }

            // Exclui os requisitos
            String deleteRequisitos = "DELETE FROM requisito WHERE projeto_id = " + id;
            int retRequisitos = st.executeUpdate(deleteRequisitos);
            System.out.println("Deleted requisitos: " + retRequisitos);

            // Exclui o projeto
            String deleteProjeto = "DELETE FROM projeto WHERE id = " + id;
            int retProjeto = st.executeUpdate(deleteProjeto);
            System.out.println("Deleted projeto: " + retProjeto);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir projeto: " + e);
            return e.toString();
        } finally {
            // Fecha a conexão e o Statement no bloco finally para garantir que sejam fechados, mesmo em caso de exceção
            try {
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public ArrayList<Projeto> consultarTodos() {
        ArrayList<Projeto> projetos = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from projeto "
                    + "order by id";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                Projeto projeto = new Projeto();

                projeto.setId(retorno.getInt("id"));
                projeto.setNome(retorno.getString("nome"));
                projeto.setDescricao(retorno.getString("descricao"));
                projeto.setDataInicio(Formatacao.ajustarDataDMA(retorno.getString("dataInicio")));
                projeto.setStatus(retorno.getString("status"));
                projeto.setUsuarioId(retorno.getInt("usuario_id"));

                projetos.add(projeto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar projetos: " + e);
        }
        return projetos;
    }

    @Override
    public ArrayList<Projeto> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Projeto consultarId(int id) {
        Projeto projeto = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from projeto "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                projeto = new Projeto();

                projeto.setId(retorno.getInt("id"));
                projeto.setNome(retorno.getString("nome"));
                projeto.setDescricao(retorno.getString("descricao"));
                projeto.setDataInicio(Formatacao.ajustarDataDMA(retorno.getString("dataInicio")));
                projeto.setStatus(retorno.getString("status"));
                projeto.setUsuarioId(retorno.getInt("usuario_id"));

            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar projeto: " + e);
        }

        return projeto;
    }

    public Projeto consultarNome(int id) {
        Projeto projeto = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select nome from projeto "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                projeto = new Projeto();
                projeto.setNome(retorno.getString("nome"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar projeto: " + e);
        }

        return projeto;
    }

}
