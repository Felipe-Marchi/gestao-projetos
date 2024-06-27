<%@page import="dao.ProjetoDAO"%>
<%@page import="entidade.Projeto"%>
<%@page import="dao.RequisitoDAO"%>
<%@page import="entidade.Requisito"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requisitos</title>
        <style>

        </style>

    </head>
    <body>

        <%@include file="menu.jsp"%>
        <div class="form-container form-container-gerencia">
            <h2>Requisitos</h2>

            <%
                // Declaração da lista de Requisitos fora do bloco condicional
                ArrayList<Requisito> requisitos = new ArrayList<>();

                // Verifica se foi passado um parâmetro de projetoId na URL
                String projetoIdParam = request.getParameter("projetoId");

                // Se projetoIdParam não for nulo e não estiver vazio
                if (projetoIdParam != null && !projetoIdParam.isEmpty()) {
                    int projetoId = Integer.parseInt(projetoIdParam);
                    // Consulta os Requisitos do projeto específico
                    requisitos = new RequisitoDAO().consultarPorProjeto(projetoId);
                } else {
                    // Caso não seja fornecido um projetoId, consulta todos os Requisitos
                    requisitos = new RequisitoDAO().consultarTodos();
                }

                // Agora você pode percorrer a lista de Requisitos
                for (Requisito requisito : requisitos) {
                    int projetoId = requisito.getProjetoId();
                    Projeto projetoVinculado = new ProjetoDAO().consultarId(projetoId);
            %>
            <div class="project-box">
                <div class="project-name" >
                    <h3><%= requisito.getNome()%></h3>
                    <p>Tipo:  <%= requisito.getTipo()%></p>
                    <span class="clickable-text" onclick="toggleDetails('<%= requisito.getId()%>', 'info_<%= requisito.getId()%>')">
                        <span id="info_<%= requisito.getId()%>">Mais informações</span>
                    </span>
                    <div class="details" id="<%= requisito.getId()%>">
                        <p>Descrição: <%= requisito.getDescricao()%></p>
                        <p>Projeto vinculado: <%= projetoVinculado.getNome()%></p>
                        <p>Prioridade: <%= requisito.getPrioridade()%></p>
                        <p>Complexidade: <%= requisito.getComplexidade()%></p>
                        <p>Versão atual: <%= requisito.getVersaoAtual()%></p>
                    </div>
                    <p><a href="historicoVersao.jsp?requisitoId=<%= requisito.getId()%>"><span class="clickable-text">Versões anteriores</span></a></p>
                </div>
                <div class="buttons-container" >
                    <div class="button-item">
                        <p>Nova versão</p>
                        <a href="/NovoServlet?param=novaversaoRequisito&id=<%= requisito.getId()%>">
                            <img src="img/versaon.png" width="30" height="30">
                        </a>
                    </div>
                    <div class="button-item">
                        <p>Editar</p>
                        <a href="/NovoServlet?param=updateRequisito&id=<%= requisito.getId()%>">
                            <img src="img/editar.png" width="30" height="30">
                        </a>
                    </div>
                    <div class="button-item">
                        <p>Excluir</p>
                        <a href="#" onclick="confirmarExclusao(<%= requisito.getId()%>)">
                            <img src="img/apagarn.png" width="30" height="30">
                        </a>
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

            function confirmarExclusao(RequisitoId) {
                var confirmacao = confirm("Tem certeza que deseja excluir este requisito e todas suas versões?");
                if (confirmacao) {
                    window.location.href = "/NovoServlet?param=deleteRequisito&id=" + RequisitoId;
                }
            }
        </script>
    </body>
</html>
