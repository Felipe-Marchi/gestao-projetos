

<%@page import="entidade.HistoricoVersao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.HistoricoVersaoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Versões anteriores</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <div class="form-container form-container-gerencia">
            <h2>Versões anteriores</h2>
            <%
                // Recuperar o ID do requisito da URL
                int requisitoId = Integer.parseInt(request.getParameter("requisitoId"));

                // Consultar o histórico de versões do requisito
                HistoricoVersaoDAO historicoDAO = new HistoricoVersaoDAO();
                ArrayList<HistoricoVersao> historicoVersoes = historicoDAO.consultarPorRequisito(requisitoId);

            %>

            <%    // Verifica se há versões anteriores
                if (!historicoVersoes.isEmpty()) {
                    // Exibe as versões anteriores
                    for (HistoricoVersao versao : historicoVersoes) {
            %>
            <div class="project-box">
                <div class="project-name">
                <h3>Versão: <%= versao.getVersao()%></h3>
                <p>Nome: <%= versao.getNome()%></p>
                <p>Tipo: <%= versao.getTipo()%></p>
                <p>Descrição: <%= versao.getDescricao()%></p>
                <p>Prioridade: <%= versao.getPrioridade()%></p>
                <p>Complexidade: <%= versao.getComplexidade()%></p>
            </div>
            </div>
            <%
                }
            } else {
            %>
            <p>Nenhuma versão anterior encontrada para este requisito.</p>
            <%
                }
            %>
    </body>
</div>
</html>
