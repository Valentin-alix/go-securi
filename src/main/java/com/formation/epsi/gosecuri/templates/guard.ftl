<#import "base.ftl" as b>

    <@b.page>
        ${guard.firstname} ${guard.lastname}
        ${guard.job}
        <img src="images/${guard.id}.jpg" alt="Carte d’identité de ${guard.firstname} ${guard.lastname}">
        <ul>
            <#list guard.equipments as equipment>
                <#if equipment??><li>${equipment.name}</li><#else>erreur, cet équipement n’existe pas</#if>
            </#list>
        </ul>
    </@b.page>