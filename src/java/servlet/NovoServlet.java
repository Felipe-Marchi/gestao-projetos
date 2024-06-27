package servlet;

import dao.ProjetoDAO;
import dao.RequisitoDAO;
import dao.UsuarioDAO;
import entidade.Projeto;
import entidade.Requisito;
import entidade.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author felip
 */
public class NovoServlet extends HttpServlet {

    //Declaração variáveis requisição e resposta
    HttpServletRequest requisicao;
    HttpServletResponse resposta;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NovoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NovoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        //HttpServletResponse UTF-8 Encoding
        response.setContentType("text/html;charset=UTF-8");

        requisicao = request; //recebe o parâmetro HttpServletRequest request
        resposta = response; // recebe o parâmetro HttpServletResponse response

        //Recupera para parametro o valor enviado por meio do 'param'        
        String parametro = request.getParameter("param");

        //Se parâmetro for "updateUsuario"
        if (parametro.equals("updateUsuario")) {
            //Recupera o parâmetro "id" vindo do form
            int id = Integer.parseInt(requisicao.getParameter("id"));

            //Chama o método consultarId da classe UsuarioDAO, passando "id" como parâmetro
            Usuario u = new UsuarioDAO().consultarId(id);

            //Define o usuário como um atributo de requisição
            requisicao.setAttribute("usuario", u);

            //Encaminha o fluxo para cadUsuario
            encaminharPagina("cadUsuario.jsp");
            //Se parâmetro for "deleteUsuario"
        } else if (parametro.equals("deleteUsuario")) {
            // Recupera o parâmetro "id" vindo do form
            int id = Integer.parseInt(requisicao.getParameter("id"));

            // Verifica se o parâmetro "confirmado" está presente
            String confirmado = requisicao.getParameter("confirmado");

            if (confirmado != null && confirmado.equals("true")) {
                // Chama o método excluir da classe UsuarioDAO, passando "id" como parâmetro
                String resultado = new UsuarioDAO().excluir(id);

                if (resultado == null) {
                    String mensagem = "Usuário excluído com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    // Atualiza a página atual
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaUsuario.jsp';</script>");
                } else {
                    String mensagem = "Erro ao excluir usuário!";
                    request.setAttribute("mensagem", mensagem);

                    // Atualiza a página atual
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaUsuario.jsp';</script>");
                }
            } else {
                // Adicione o parâmetro "confirmado" à URL antes do redirecionamento
                encaminharPagina("/NovoServlet?param=deleteUsuario&id=" + id + "&confirmado=true");
            }
        }
//--------------------------------------------------------------------------------------------------------------
        if (parametro.equals("updateProjeto")) {
            int id = Integer.parseInt(requisicao.getParameter("id"));

            Projeto p = new ProjetoDAO().consultarId(id);

            requisicao.setAttribute("projeto", p);

            encaminharPagina("cadProjeto.jsp");
        } else if (parametro.equals("deleteProjeto")) {
            int id = Integer.parseInt(requisicao.getParameter("id"));

            String confirmado = requisicao.getParameter("confirmado");

            if (confirmado != null && confirmado.equals("true")) {
                String resultado = new ProjetoDAO().excluir(id);

                if (resultado == null) {
                    String mensagem = "Projeto excluído com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");
                } else {
                    String mensagem = "Erro ao excluir projeto!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");
                }
            } else {
                encaminharPagina("/NovoServlet?param=deleteProjeto&id=" + id + "&confirmado=true");
            }
        }
//--------------------------------------------------------------------------------------------------------------
        if (parametro.equals("updateRequisito")) {
            int id = Integer.parseInt(requisicao.getParameter("id"));

            Requisito r = new RequisitoDAO().consultarId(id);

            requisicao.setAttribute("requisito", r);

            encaminharPagina("cadRequisito.jsp");
        } else if (parametro.equals("deleteRequisito")) {
            int id = Integer.parseInt(requisicao.getParameter("id"));

            String confirmado = requisicao.getParameter("confirmado");

            if (confirmado != null && confirmado.equals("true")) {
                String resultado = new RequisitoDAO().excluir(id);

                if (resultado == null) {
                    String mensagem = "Requisito excluído com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaRequisito.jsp';</script>");
                } else {
                    String mensagem = "Erro ao excluir requisito!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaRequisito.jsp';</script>");
                }
            } else {
                encaminharPagina("/NovoServlet?param=deleteRequisito&id=" + id + "&confirmado=true");
            }
        } else if (parametro.equals("novaversaoRequisito")) {
            int id = Integer.parseInt(requisicao.getParameter("id"));

            // Consulta o requisito atual
            RequisitoDAO requisitoDAO = new RequisitoDAO();
            Requisito requisitoAtual = requisitoDAO.consultarId(id);

            // Cria uma nova instância do requisito com os mesmos dados
            Requisito versaoAtual = new Requisito();
            versaoAtual.setId(requisitoAtual.getId());
            versaoAtual.setNome(requisitoAtual.getNome());
            versaoAtual.setTipo(requisitoAtual.getTipo());
            versaoAtual.setDescricao(requisitoAtual.getDescricao());
            versaoAtual.setProjetoId(requisitoAtual.getProjetoId());
            versaoAtual.setPrioridade(requisitoAtual.getPrioridade());
            versaoAtual.setComplexidade(requisitoAtual.getComplexidade());
            versaoAtual.setVersaoAtual(requisitoAtual.getVersaoAtual());

            // Salva a nova versão na tabela historico_versoes
            RequisitoDAO historicoDAO = new RequisitoDAO();
            historicoDAO.salvarHistoricoVersao(versaoAtual);

            // Redireciona para a página desejada
            Requisito r = new RequisitoDAO().consultarId(id);
            int novaVersao = requisitoAtual.getVersaoAtual() + 1;
            r.setVersaoAtual(novaVersao);

            requisicao.setAttribute("requisito", r);

            encaminharPagina("cadRequisito.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        //HttpServletResponse e HttpServletRequest UTF-8 Encoding
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        requisicao = request; //recebe o parâmetro HttpServletRequest request
        resposta = response; //recebe o parâmetro HttpServletResponse response

        //Recupera para parâmetro o valor enviado por meio do 'param'
        String parametro = request.getParameter("param");

        //Se parâmetro for "login"
        if (parametro.equals("login")) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);

            UsuarioDAO login = new UsuarioDAO();

            if (login.logar(usuario)) {

                encaminharPagina("inicio.jsp");
            } else {
                //Vamos definir um atributo 'alertMsg' para utilizar index.jsp
                request.setAttribute("alert", "Login invalido! Tente novamente.");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                //O método include inclui o conteúdo na resposta.                
                rd.include(request, response);
            }
        }
//------------------------------------------------------------------------------------------
        //Se parâmetro for "usuario"
        if (parametro.equals("cadUsuario")) {
            String nome = requisicao.getParameter("nome");
            String email = requisicao.getParameter("email");
            String senha = requisicao.getParameter("senha");
            String nivel = requisicao.getParameter("nivel");

            // Recupere o ID do usuário do campo oculto
            int idUsuario = Integer.parseInt(requisicao.getParameter("id"));

            // Verifique se é uma edição ou adição
            if (idUsuario > 0) {
                // Se o ID for maior que 0, é uma edição
                // Recupere o usuário existente do banco de dados
                Usuario usuarioExistente = new UsuarioDAO().consultarId(idUsuario);

                // Atualize os dados do usuário existente com os novos valores
                usuarioExistente.setNome(nome);
                usuarioExistente.setEmail(email);
                usuarioExistente.setSenha(senha);
                usuarioExistente.setNivel(nivel);

                // Chame o método de atualização na classe UsuarioDAO
                String resultado = new UsuarioDAO().editar(usuarioExistente);

                if (resultado == null) {
                    String mensagem = "Usuário editado com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    // Atualiza a página atual
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaUsuario.jsp';</script>");
                } else {
                    String mensagem = "Erro ao editar usuário!";
                    request.setAttribute("mensagem", mensagem);

                    // Atualiza a página atual
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaUsuario.jsp';</script>");
                }

            } else {

                //Cria novo objeto Usuario e preenche com os valores recebidos
                Usuario u = new Usuario();
                u.setNome(nome);
                u.setEmail(email);
                u.setSenha(senha);
                u.setNivel(nivel);

                //Chama o método salvar da classe UsuarioDAO, passando "u" como parâmetro
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                String resultado = usuarioDAO.salvar(u);

                if (resultado == null) {
                    String mensagem = "Usuário cadastrado com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    // Atualiza a página atual
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaUsuario.jsp';</script>");

                } else {
                    String mensagem = "Erro ao cadastrar usuário!";
                    request.setAttribute("mensagem", mensagem);

                    // Atualiza a página atual
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaUsuario.jsp';</script>");

                }
            }
        }
//----------------------------------------------------------------------------------------------------
        //Se parâmetro for "projeto"
        if (parametro.equals("cadProjeto")) {
            String nome = requisicao.getParameter("nome");
            String descricao = requisicao.getParameter("descricao");
            String dataInicio = requisicao.getParameter("dataInicio");
            String status = requisicao.getParameter("status");
            int usuarioId = Integer.parseInt(requisicao.getParameter("usuario"));

            String idProjetoParam = requisicao.getParameter("id");
            int idProjeto = 0;

            if (idProjetoParam != null && !idProjetoParam.isEmpty()) {
                idProjeto = Integer.parseInt(idProjetoParam);
            }

            if (idProjeto > 0) {

                Projeto projetoExistente = new ProjetoDAO().consultarId(idProjeto);

                projetoExistente.setNome(nome);
                projetoExistente.setDescricao(descricao);
                projetoExistente.setDataInicio(dataInicio);
                projetoExistente.setStatus(status);
                projetoExistente.setUsuarioId(usuarioId);

                String resultado = new ProjetoDAO().editar(projetoExistente);

                if (resultado == null) {
                    String mensagem = "Projeto editado com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");
                } else {
                    String mensagem = "Erro ao editar projeto!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");
                }

            } else {

                Projeto p = new Projeto();
                p.setNome(nome);
                p.setDescricao(descricao);
                p.setDataInicio(dataInicio);
                p.setStatus(status);
                p.setUsuarioId(usuarioId);

                ProjetoDAO projetoDAO = new ProjetoDAO();
                String resultado = projetoDAO.salvar(p);

                if (resultado == null) {
                    String mensagem = "Projeto cadastrado com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");

                } else {
                    String mensagem = "Erro ao cadastrar projeto!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");

                }
            }
        }
//----------------------------------------------------------------------------------------------------
        //Se parâmetro for "requisito"
        if (parametro.equals("cadRequisito")) {
            String nome = requisicao.getParameter("nome");
            String tipo = requisicao.getParameter("tipo");
            String descricao = requisicao.getParameter("descricao");
            int projetoId = Integer.parseInt(requisicao.getParameter("projeto"));
            String prioridade = requisicao.getParameter("prioridade");
            String complexidade = requisicao.getParameter("complexidade");
            int versaoAtual = Integer.parseInt(requisicao.getParameter("versao"));

            String idRequisitoParam = requisicao.getParameter("id");
            int idRequisito = 0;

            if (idRequisitoParam != null && !idRequisitoParam.isEmpty()) {
                idRequisito = Integer.parseInt(idRequisitoParam);
            }

            if (idRequisito > 0) {

                Requisito requisitoExistente = new RequisitoDAO().consultarId(idRequisito);

                requisitoExistente.setNome(nome);
                requisitoExistente.setTipo(tipo);
                requisitoExistente.setDescricao(descricao);
                requisitoExistente.setProjetoId(projetoId);
                requisitoExistente.setPrioridade(prioridade);
                requisitoExistente.setComplexidade(complexidade);
                requisitoExistente.setVersaoAtual(versaoAtual);

                String resultado = new RequisitoDAO().editar(requisitoExistente);

                if (resultado == null) {
                    String mensagem = "Requisito editado com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaRequisito.jsp';</script>");
                } else {
                    String mensagem = "Erro ao editar requisito!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaRequisito.jsp';</script>");
                }

            } else {

                Requisito r = new Requisito();
                r.setNome(nome);
                r.setTipo(tipo);
                r.setDescricao(descricao);
                r.setProjetoId(projetoId);
                r.setPrioridade(prioridade);
                r.setComplexidade(complexidade);
                r.setVersaoAtual(versaoAtual);

                RequisitoDAO requisitoDAO = new RequisitoDAO();
                String resultado = requisitoDAO.salvar(r);

                if (resultado == null) {
                    String mensagem = "Requisito cadastrado com sucesso!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");

                } else {
                    String mensagem = "Erro ao cadastrar requisito!";
                    request.setAttribute("mensagem", mensagem);

                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + mensagem + "'); window.location.href='" + request.getContextPath() + "/gerenciaProjeto.jsp';</script>");

                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //Método encaminharPagina
    private void encaminharPagina(String pagina) {
        try {
            //RequestDispatcher fornece a facilidade de despachar a solicitação para outro recurso
            RequestDispatcher rd = requisicao.getRequestDispatcher(pagina);
            //forward (solicitação de ServletRequest, resposta de ServletResponse)
            rd.forward(requisicao, resposta);
        } catch (Exception e) {
            System.out.println("Erro no encaminhamento: " + e);
        }
    }

}
