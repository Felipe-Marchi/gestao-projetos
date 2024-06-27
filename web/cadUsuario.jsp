
<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Usuário</title>
        <style>
        </style>
    </head>
    <body>
        <%@include file="menu.jsp"%>

        <%
            //Recuperando o atributo usuario cujo valor é um objeto do tipo usuario
            //Se estou em uma edição quero recuperar este valor e mostrar no form pelo 'input value'
            Usuario u = (Usuario) request.getAttribute("usuario");

            //Se objeto u for null, estou inserindo novo registro (INSERT)
            if (u == null) {
                //Se u for null crio um novo objeto
                u = new Usuario();
                u.setId(0);
                u.setNome("");
                u.setEmail("");
                u.setSenha("");
                u.setNivel("");

            }
        %>

        <div class="form-container">
            <h2>Cadastro de Usuário</h2>
            <form action="/NovoServlet?param=cadUsuario" method="post">
                <input type="hidden" name="id" value="<%= u.getId()%>">
                <div class="input-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" maxlength="100" placeholder="Digite seu nome" value="<%= u.getNome()%>" required>
                </div>
                <div class="input-group">
                    <label for="email">E-mail</label>
                    <input type="email" id="email" name="email" maxlength="100" placeholder="Digite seu e-mail" value="<%= u.getEmail()%>" required>
                </div>
                <div class="input-group">
                    <label for="senha">Senha</label>
                    <input type="password" id="senha" name="senha" maxlength="60" placeholder="Digite sua senha" value="<%= u.getSenha()%>" required>
                </div>
                <div class="input-group">
                    <label for="nivel">Nível de Acesso</label>
                    <select id="nivel" name="nivel" required>
                        <option value="" <%= u.getNivel() == null ? "selected" : ""%>>Selecione o Nível</option>
                        <option value="Admin" <%= u.getNivel().equals("Admin") ? "selected" : ""%> >Admin</option>
                        <option value="Usuário" <%= u.getNivel().equals("Usuário") ? "selected" : ""%> >Usuário</option>
                    </select>
                </div>
                <button type="submit" class="btn">Salvar</button>
            </form>
        </div>
    </body>
</html>
