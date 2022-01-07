<#macro page>
<!DOCTYPE html>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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