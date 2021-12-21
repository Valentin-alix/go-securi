<html>
    <head>
        <title>Liste des agents | Go Securi</title>
    </head>
    <body>
        <h1>Bienvenue sur le site de Go Securi</h1>
        <h2>Liste des agents</h2>
        <ul>
            <#list guards as guard>
            <li><a href="${guard.id}.html">${guard.id}</a></li>
            </#list>
        </ul>
    </body>
</html>