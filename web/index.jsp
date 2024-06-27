<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tela de Login</title>
        <style>

        </style>
    </head>  
    <body>

        <%
            String mensagem = (String) request.getAttribute("alert");
            if (mensagem == null) {
                mensagem = "";
            }
        %>

        <script type="text/javascript">
            var msg = "<%=mensagem%>";
            if (msg !== "") {
                alert(msg);
            }
        </script>

        <div class="form-login">
            <h2>Login</h2>
            <img src="img/user-icon.png" alt="User Icon" class="user-icon">
            <form name="formLogin" action="/NovoServlet?param=login" method="post">
                <div class="input-group">
                    <label for="email">E-mail</label>
                    <input type="text" name="email" "id="email" placeholder="Digite seu e-mail" value="felipe@email.com">
                    <label for="senha">Senha</label>
                    <input type="password" name="senha" id="senha" placeholder="Digite sua senha" value="Felipe">
                </div>
                <button type="submit "id="loginBtn" class="btn">Entrar</button>
            </form>
        </div>
    </body>
</html>
