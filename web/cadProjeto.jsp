
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="entidade.Projeto"%>
<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/validacao.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Projeto</title>
        <style>

        </style>
    </head>
    <body>
        <%@include file="menu.jsp"%>


        <%
            //Recuperando o atributo projeto cujo valor é um objeto do tipo projeto
            //Se estou em uma edição quero recuperar este valor e mostrar no form pelo 'input value'
            Projeto p = (Projeto) request.getAttribute("projeto");

            //Se objeto p for null, estou inserindo novo registro (INSERT)
            if (p == null) {
                //Se p for null crio um novo objeto
                p = new Projeto();
                p.setId(0);
                p.setNome("");
                p.setDescricao("");
                p.setDataInicio("");
                p.setStatus("");
                p.setUsuarioId(0);

            }
        %>

        <div class="form-container">
            <h2>Cadastrar Projeto</h2>
            <form id="cadastro" onsubmit="return validar()" action="/NovoServlet?param=cadProjeto" method="post">
                <input type="hidden" name="id" maxlength="100" value="<%= p.getId()%>">
                <div class="input-group">
                    <label for="nome">Nome do Projeto</label>
                    <input type="text" id="nome" name="nome" placeholder="Digite o nome do projeto" value="<%= p.getNome()%>" required>
                </div>
                <div class="input-group">
                    <label for="descricao">Descrição</label>
                    <textarea id="descricao" name="descricao" maxlength="250" placeholder="Digite a descrição" required><%= p.getDescricao()%></textarea>
                </div>
                <div class="input-group">
                    <label for="data">Data de Início</label>
                    <input type="text" id="dataInicio" name="dataInicio" maxlength="10" placeholder="Digite a data de início" value="<%= p.getDataInicio()%>" required>
                </div>
                <div class="input-group">
                    <label for="status">Status</label>
                    <select id="status" name="status" required>
                        <option value="" <%= p.getStatus() == null ? "selected" : ""%>>Selecione o Status</option>
                        <option value="Não iniciado" <%= p.getStatus().equals("Não iniciado") ? "selected" : ""%> >Não iniciado</option>
                        <option value="Em andamento" <%= p.getStatus().equals("Em andamento") ? "selected" : ""%> >Em andamento</option>
                        <option value="Finalizado" <%= p.getStatus().equals("Finalizado") ? "selected" : ""%> >Finalizado</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="usuario">Usuário Responsável</label>
                    <select id="usuario" name="usuario" required>
                        <option value="" <%= p.getUsuarioId() == 0 ? "selected" : ""%>>Selecione o Usuário</option>
                        <%
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            ArrayList<Usuario> listaUsuarios = usuarioDAO.consultarTodos();
                            for (Usuario usuario : listaUsuarios) {
                        %>
                        <option value="<%= usuario.getId()%>" <%= p.getUsuarioId() == usuario.getId() ? "selected" : ""%>><%= usuario.getNome()%></option>
                        <% }%>
                    </select>
                </div>
                <button type="submit" class="btn">Salvar</button>
            </form> 
        </div>
        <script type="text/javascript">

            const inputData = document.getElementById('dataInicio')

            inputData.addEventListener('keypress', () => {
                let inputLength = inputData.value.length

                if (inputLength === 2 || inputLength === 5) {
                    inputData.value += '/'
                }
            })
        </script>
    </body>
</html>
