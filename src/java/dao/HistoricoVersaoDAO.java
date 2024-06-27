package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidade.HistoricoVersao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HistoricoVersaoDAO implements IDAOT<HistoricoVersao> {

    @Override
    public String salvar(HistoricoVersao o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String editar(HistoricoVersao o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HistoricoVersao> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HistoricoVersao> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HistoricoVersao consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para consultar versões anteriores por requisito
    public ArrayList<HistoricoVersao> consultarPorRequisito(int requisitoId) {

        ArrayList<HistoricoVersao> historicoVersoes = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM historico_versoes "
                    + "WHERE id_requisito = " + requisitoId;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {

                HistoricoVersao versao = new HistoricoVersao();

                versao.setId(retorno.getInt("id"));
                versao.setRequisitoId(retorno.getInt("id_requisito"));
                versao.setNome(retorno.getString("nome"));
                versao.setTipo(retorno.getString("tipo"));
                versao.setDescricao(retorno.getString("descricao"));
                versao.setPrioridade(retorno.getString("prioridade"));
                versao.setComplexidade(retorno.getString("complexidade"));
                versao.setVersao(retorno.getInt("versao"));

                historicoVersoes.add(versao);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar versões: " + e);
        }
        return historicoVersoes;
    }
}

