package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidade.Usuario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author felip
 */
public class UsuarioDAO implements IDAOT<Usuario> {

    @Override
    public String salvar(Usuario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "insert into usuario values "
                    + "(default,"
                    + " '" + o.getNome() + "',"
                    + " '" + o.getEmail() + "',"
                    + " '" + o.getSenha() + "',"
                    + " '" + o.getNivel() + "')";

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao inserir usuário: " + e);
            return e.toString();
        }
    }

    @Override
    public String editar(Usuario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE usuario "
                    + "SET nome = '" + o.getNome()
                    + "', email = '" + o.getEmail()
                    + "', senha = '" + o.getSenha()
                    + "', nivel = '" + o.getNivel()
                    + "' WHERE id = " + o.getId();

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "delete from usuario "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            int retorno = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir usuário: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Usuario> consultarTodos() {
        ArrayList<Usuario> usuarios = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from usuario "
                    + "order by id";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(retorno.getInt("id"));
                usuario.setNome(retorno.getString("nome"));
                usuario.setEmail(retorno.getString("email"));
                usuario.setNivel(retorno.getString("nivel"));

                usuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuários: " + e);
        }
        return usuarios;
    }

    @Override
    public ArrayList<Usuario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario consultarId(int id) {
        Usuario usuario = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from usuario "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                usuario = new Usuario();

                usuario.setId(retorno.getInt("id"));
                usuario.setNome(retorno.getString("nome"));
                usuario.setEmail(retorno.getString("email"));
                usuario.setSenha(retorno.getString("senha"));
                usuario.setNivel(retorno.getString("nivel"));

            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário: " + e);
        }

        return usuario;
    }

    public boolean logar(Usuario usuario) {

        boolean retorno = false;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql
                    = "select email, senha from usuario "
                    + "where email='" + usuario.getEmail()+ "'"
                    + "and senha='" + usuario.getSenha() + "'";

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                retorno = true;
                System.out.println("Login feito com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e);
        }
        return retorno;
    }

}
