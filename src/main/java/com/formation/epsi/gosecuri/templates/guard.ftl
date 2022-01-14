<#import "base.ftl" as b>

    <@b.page>
        <div class="guard">
            <div class="row">
                <div class="col">
                    <h1 class="guard__heading">${title}</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-6">
                    <h2 class="guard__name">${guard.firstname} ${guard.lastname}</h2>
                    <h3 class="guard__job">${guard.job}</h3>
                </div>
                <div class="col-sm-12 col-md-6">
                    <img class="img-fluid" src="images/${guard.id}.jpg" alt="Carte d’identité de ${guard.firstname} ${guard.lastname}">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-6">
                    <ul>
                        <#list guard.equipments as equipment>
                            <#if equipment??><li class="guard__check-left">${equipment.name}</li><#else>erreur, cet équipement n’existe pas</#if>
                        </#list>
                    </ul>
                </div>
            </div>
            <a href="index.html" class="btn btn-primary">Retour à l’accueil</a>
        </div>
    </@b.page>
