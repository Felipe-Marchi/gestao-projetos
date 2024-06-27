
<%@page import="entidade.Projeto"%>
<%@page import="dao.ProjetoDAO"%>
<%@page import="entidade.Requisito"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Requisito</title>
        <style>

        </style>
    </head>
    <body>
        <%@include file="menu.jsp"%>


        <%

            //Recuperando o atributo requisito cujo valor é um objeto do tipo requisito
            //Se estou em uma edição quero recuperar este valor e mostrar no form pelo 'input value'
            Requisito r = (Requisito) request.getAttribute("requisito");

            //Se objeto r for null, estou inserindo novo registro (INSERT)
            if (r == null) {
                //Se r for null crio um novo objeto
                r = new Requisito();
                r.setId(0);
                r.setNome("");
                r.setTipo("");
                r.setDescricao("");
                r.setProjetoId(0);
                r.setPrioridade("");
                r.setComplexidade("");
                r.setVersaoAtual(0);

            }
        %>

        <div class="form-container">
            <h2>Cadastrar Requisito</h2>
            <form action="/NovoServlet?param=cadRequisito" method="post">
                <input type="hidden" name="id" value="<%= r.getId()%>">
                <div class="input-group">
                    <label for="nome">Nome do Requisito</label>
                    <input type="text" id="nome" name="nome" maxlength="100" placeholder="Digite o nome do requisito" value="<%= r.getNome()%>" required>
                </div>
                <div class="input-group">
                    <label for="tipo">Tipo</label>
                    <select id="tipo" name="tipo" required>
                        <option value="" <%= r.getTipo() == null ? "selected" : ""%>>Selecione o Tipo</option>
                        <option value="Funcional" <%= r.getTipo().equals("Funcional") ? "selected" : ""%> >Funcional</option>
                        <option value="Não funcional" <%= r.getTipo().equals("Não funcional") ? "selected" : ""%> >Não funcional</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="descricao">Descrição</label>
                    <textarea id="descricao" name="descricao" maxlength="250" placeholder="Digite a descrição" required><%= r.getDescricao()%></textarea>
                </div>
                <div class="input-group">
                    <label for="projeto">Vincular ao Projeto</label>
                    <select id="projeto" name="projeto" required <%= r.getProjetoId() == 0 ? "selected" : ""%> <%= request.getParameter("projetoId")%>>
                        <option value="" <%= r.getProjetoId() == 0 ? "selected" : ""%>>Selecione o Projeto</option>
                        <%
                            ProjetoDAO projetoDAO = new ProjetoDAO();
                            ArrayList<Projeto> listaProjetos = projetoDAO.consultarTodos();
                            for (Projeto projeto : listaProjetos) {
                        %>
                                <option value="<%= projeto.getId()%>" <%= (r.getProjetoId() == projeto.getId() || (request.getParameter("projetoId") != null
                                        && Integer.parseInt(request.getParameter("projetoId")) == projeto.getId())) ? "selected" : ""%>><%= projeto.getNome()%></option>
                        <% }%>
                    </select>                    
                </div>               
                <div class="input-group">
                    <label for="prioridade">Prioridade</label>
                    <select id="prioridade" name="prioridade" required>
                        <option value="" <%= r.getPrioridade() == null ? "selected" : ""%>>Selecione a Prioridade</option>
                        <option value="Alta" <%= r.getPrioridade().equals("Alta") ? "selected" : ""%> >Alta</option>
                        <option value="Média" <%= r.getPrioridade().equals("Média") ? "selected" : ""%> >Média</option>
                        <option value="Baixa" <%= r.getPrioridade().equals("Baixa") ? "selected" : ""%> >Baixa</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="complexidade">Complexidade</label>
                    <select id="complexidade" name="complexidade" required>
                        <option value="" <%= r.getComplexidade() == null ? "selected" : ""%>>Selecione a Complexidade</option>
                        <option value="Alta" <%= r.getComplexidade().equals("Alta") ? "selected" : ""%> >Alta</option>
                        <option value="Média" <%= r.getComplexidade().equals("Média") ? "selected" : ""%> >Média</option>
                        <option value="Baixa" <%= r.getComplexidade().equals("Baixa") ? "selected" : ""%> >Baixa</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="versao">Versão</label>
                    <%
                        // Recupera o valor da versão atual do requisito
                        int versaoAtual = r.getVersaoAtual();

                        // Se a versão atual for 0 (novo requisito), define o valor como 1
                        if (versaoAtual == 0) {
                            versaoAtual = 1;
                        }
                    %>
                    <input type="text" id="versao" name="versao" value="<%= versaoAtual%>" readonly>
                </div>
                <button type="submit" class="btn">Salvar</button>
            </form> 
        </div>
    </body>
</html>
