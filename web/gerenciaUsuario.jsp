
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Usuario"%>
<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários</title>
        <style>
            
        </style>
    </head>
    <body>
        <%@include file="menu.jsp"%>

        <%
            //Arraylist de pessoas chamando PessoaDAO().consultarPessoa()
            ArrayList<Usuario> usuarios = new UsuarioDAO().consultarTodos();
        %>

        <div class="form-container form-container-gerencia">
            <h2>Usuários</h2>

            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Nível</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        for (int i = 0; i < usuarios.size(); i++) {
                    %>
                    <!-- Adicione aqui a lógica para exibir os usuários cadastrados -->
                    <tr>
                        <td><%= usuarios.get(i).getId()%></td>
                        <td><%= usuarios.get(i).getNome()%></td>
                        <td><%= usuarios.get(i).getEmail()%></td>
                        <td><%= usuarios.get(i).getNivel()%></td>
                        <%-- Cria um link para o 'acao' por meio de um get com o respectivo 'param' + o Id do produto --%>
                        <td><a href="/NovoServlet?param=updateUsuario&id=<%= usuarios.get(i).getId()%>"><img src="img/editar.png" width="20" height="20">
                            </a>
                        </td>
                        <td>
                            <!-- Adiciona um link para excluir com uma chamada de função JavaScript -->
                            <a href="#" onclick="confirmarExclusao(<%= usuarios.get(i).getId()%>)"><img src="img/apagarn.png" width="20" height="20">
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <script>
            function confirmarExclusao(usuarioId) {
                var confirmacao = confirm("Tem certeza que deseja excluir este usuário?");
                if (confirmacao) {
                    window.location.href = "/NovoServlet?param=deleteUsuario&id=" + usuarioId;
                }
            }
        </script>
    </body>
</html>
