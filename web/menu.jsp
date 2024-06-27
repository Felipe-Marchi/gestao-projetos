<%-- 
    Document   : menu
    Created on : 22 de nov. de 2023, 14:04:18
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
        }

        #sidebar {
            width: 200px;
            background: linear-gradient(to right, #505050, #282828);
            padding-top: 20px;
            position: fixed;
            height: 100%;
            overflow: auto;
            border-right: 4px solid black; /* Adiciona uma borda azul à direita */
        }

        #sidebar a {
            padding: 10px;
            text-decoration: none;
            font-size: 18px;
            color: #818181;
            display: block;
        }

        #sidebar a:hover {
            color: #f1f1f1;
        }

        .submenu {
            padding-left: 20px;
            display: none;
        }

    </style>
</head>
<body>

    <div id="sidebar">
        <a href="inicio.jsp">Início</a>
        
        <a href="#" onclick="toggleSubmenu('projetos')">Projetos</a>
        <div id="projetos" class="submenu">
            <a style="font-size: 14px;" href="cadProjeto.jsp">Criar Projeto</a>
            <a style="font-size: 14px;" href="gerenciaProjeto.jsp">Gerenciar Projetos</a>            
        </div>
        
        <a href="#" onclick="toggleSubmenu('requisitos')">Requisitos</a>
        <div id="requisitos" class="submenu">
            <a style="font-size: 14px;" href="cadRequisito.jsp">Criar Requisito</a>
            <a style="font-size: 14px;" href="gerenciaRequisito.jsp">Gerenciar Requisitos</a>            
        </div>
        
        <a href="#" onclick="toggleSubmenu('usuarios')">Usuários</a>
        <div id="usuarios" class="submenu">
            <a style="font-size: 14px;" href="cadUsuario.jsp">Criar Usuário</a>
            <a style="font-size: 14px;" href="gerenciaUsuario.jsp">Gerenciar Usuários</a>            
        </div>
        
        <a href="index.jsp">Sair</a>
    </div>

    <script>
        function toggleSubmenu(submenuId) {
            var submenu = document.getElementById(submenuId);
            submenu.style.display = (submenu.style.display === 'block') ? 'none' : 'block';
        }
    </script>
</body>
</html>
