<%@page import="dao.UsuarioDAO"%>
<%@page import="entidade.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProjetoDAO"%>
<%@page import="entidade.Projeto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projetos</title>
        <style>

        </style>

    </head>
    <body>

        <%@include file="menu.jsp"%>
        <div class="form-container form-container-gerencia">
            <h2>Projetos</h2>

            <%
                String nivelPermissao = (String)request.getSession().getAttribute("nivelPermissao");
                System.out.println(nivelPermissao);

                // Consulta todos os projetos
                ArrayList<Projeto> projetos = new ProjetoDAO().consultarTodos();

                for (Projeto projeto : projetos) {
                    int usuarioId = projeto.getUsuarioId();
                    Usuario usuarioResponsavel = new UsuarioDAO().consultarId(usuarioId);
            %>
            <div class="project-box">
                <div class="project-name" >
                    <h3><%= projeto.getNome()%></h3>
                    <p>Status: <%= projeto.getStatus()%></p>
                    <span class="clickable-text" onclick="toggleDetails('<%= projeto.getId()%>', 'info_<%= projeto.getId()%>')">
                        <span id="info_<%= projeto.getId()%>">Mais informações</span>
                    </span>
                    <div class="details" id="<%= projeto.getId()%>">
                        <p>Descrição: <%= projeto.getDescricao()%></p>
                        <p>Data de Início: <%= projeto.getDataInicio()%></p>
                        <p>Usuário Responsável: <%= usuarioResponsavel.getNome()%></p>
                    </div>
                    <p><a href="gerenciaRequisito.jsp?projetoId=<%= projeto.getId()%>"><span class="clickable-text">Ver Requisitos</span></a></p>
                </div>
                <div class="botoes-linhas" >
                    <div class="buttons-container" >
                        <div class="button-item">
                            <p>Add Requisito</p>
                            <a href="/cadRequisito.jsp?projetoId=<%= projeto.getId()%>">
                                <img src="img/adicionar.png" width="30" height="30">
                            </a>
                        </div>
                        <div class="button-item">
                            <p>Editar</p>
                            <a href="/NovoServlet?param=updateProjeto&id=<%= projeto.getId()%>">
                                <img src="img/editar.png" width="30" height="30">
                            </a>
                        </div>
                    </div>
                    <div class="buttons-container" >
                        <div class="button-item">
                            <p>Gerar relatório</p>
                            <a href="/Relatorio?projetoId=<%= projeto.getId()%>">
                                <img src="img/ficheiro-pdf.png" width="30" height="30">
                            </a>
                        </div>
                        <div class="button-item">
                            <p>Excluir</p>
                            <a href="#" onclick="confirmarExclusao(<%= projeto.getId()%>)">
                                <img src="img/apagarn.png" width="30" height="30">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>

        <script>
            function toggleDetails(projectId, buttonId) {
                var details = document.getElementById(projectId);
                var infoButton = document.getElementById(buttonId);

                // Altera o estado de exibição dos detalhes
                details.style.display = details.style.display === 'block' ? 'none' : 'block';

                // Atualiza o texto do botão com base no estado atual
                var info = details.style.display === 'block' ? "Ocultar informações" : "Mais informações";
                infoButton.innerText = info;
            }

            function confirmarExclusao(projetoId) {
                var confirmacao = confirm("Tem certeza que deseja excluir este projeto e todos requisitos vinculados?");
                if (confirmacao) {
                    window.location.href = "/NovoServlet?param=deleteProjeto&id=" + projetoId;
                }
            }

        </script>
    </body>
</html>

