
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidade.Requisito;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class RequisitoDAO implements IDAOT<Requisito> {

    @Override
    public String salvar(Requisito o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "insert into requisito values "
                    + "(default,"
                    + " '" + o.getNome() + "',"
                    + " '" + o.getTipo()+ "',"
                    + " '" + o.getDescricao()+ "',"
                    + " " + o.getProjetoId()+ ","
                    + " '" + o.getPrioridade()+ "',"
                    + " '" + o.getComplexidade()+ "',"
                    + " " + o.getVersaoAtual()+ ")";

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao inserir requisito: " + e);
            return e.toString();
        }
    }

    @Override
    public String editar(Requisito o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE requisito "
                    + "SET nome = '" + o.getNome()
                    + "', tipo = '" + o.getTipo()
                    + "', descricao = '" + o.getDescricao()
                    + "', projeto_id = " + o.getProjetoId()
                    + ", prioridade = '" + o.getPrioridade()
                    + "', complexidade = '" + o.getComplexidade()
                    + "', versao_atual = " + o.getVersaoAtual()
                    + " WHERE id = " + o.getId();

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar requisito: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
       try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String versoes = "delete from historico_versoes "
                    + "where id_requisito = " + id;
            
            String sql = "delete from requisito "
                    + "where id = " + id;
            
            System.out.println("SQL: " + versoes);
            System.out.println("SQL: " + sql);
            
            int ret = st.executeUpdate(versoes);
            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir requisito: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Requisito> consultarTodos() {
        ArrayList<Requisito> requisitos = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from requisito "
                    + "order by id";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                Requisito requisito = new Requisito();

                requisito.setId(retorno.getInt("id"));
                requisito.setNome(retorno.getString("nome"));
                requisito.setTipo(retorno.getString("tipo"));
                requisito.setDescricao(retorno.getString("descricao"));
                requisito.setProjetoId(retorno.getInt("projeto_id"));
                requisito.setPrioridade(retorno.getString("prioridade"));
                requisito.setComplexidade(retorno.getString("complexidade"));
                requisito.setVersaoAtual(retorno.getInt("versao_atual"));

                requisitos.add(requisito);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar requisitos: " + e);
        }
        return requisitos;
    }

    @Override
    public ArrayList<Requisito> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Requisito consultarId(int id) {
        Requisito requisito = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from requisito "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                requisito = new Requisito();

                requisito.setId(retorno.getInt("id"));
                requisito.setNome(retorno.getString("nome"));
                requisito.setTipo(retorno.getString("tipo"));
                requisito.setDescricao(retorno.getString("descricao"));
                requisito.setProjetoId(retorno.getInt("projeto_id"));
                requisito.setPrioridade(retorno.getString("prioridade"));
                requisito.setComplexidade(retorno.getString("complexidade"));
                requisito.setVersaoAtual(retorno.getInt("versao_atual"));

            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar requisito: " + e);
        }

        return requisito;
    }

    public String salvarHistoricoVersao(Requisito requisito) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO historico_versoes (id_requisito, nome, tipo, descricao, prioridade, complexidade, versao) "
                    + "VALUES (" + requisito.getId() + ", "
                    + "'" + requisito.getNome() + "', "
                    + "'" + requisito.getTipo() + "', "
                    + "'" + requisito.getDescricao() + "', "
                    + "'" + requisito.getPrioridade() + "', "
                    + "'" + requisito.getComplexidade() + "', "
                    + "'" + requisito.getVersaoAtual() + "')";

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar histórico de versão: " + e);
            return e.toString();
        }
    }
    
    public ArrayList<Requisito> consultarPorProjeto(int projetoId) {
    ArrayList<Requisito> requisitos = new ArrayList<>();

    try {
        Statement st = ConexaoBD.getInstance().getConnection().createStatement();

        String sql = "select * from requisito where projeto_id = " + projetoId;

        System.out.println("SQL: " + sql);

        ResultSet retorno = st.executeQuery(sql);

        while (retorno.next()) {
            Requisito requisito = new Requisito();

            requisito.setId(retorno.getInt("id"));
            requisito.setNome(retorno.getString("nome"));
            requisito.setTipo(retorno.getString("tipo"));
            requisito.setDescricao(retorno.getString("descricao"));
            requisito.setProjetoId(retorno.getInt("projeto_id"));
            requisito.setPrioridade(retorno.getString("prioridade"));
            requisito.setComplexidade(retorno.getString("complexidade"));
            requisito.setVersaoAtual(retorno.getInt("versao_atual"));

            requisitos.add(requisito);
        }

    } catch (Exception e) {
        System.out.println("Erro ao consultar requisitos por projeto: " + e);
    }

    return requisitos;
}
    
}
