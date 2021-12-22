<#macro page>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>${title} | Go Securi</title>
</head>
<body>
    <header><h1>${title}</h1></header>
    <main>
        <#nested>
    </main>
</body>
</html>
</#macro>