<#import "base.ftl" as b>

    <@b.page>
        ${guard.firstname} ${guard.lastname}
        ${guard.job}
        <ul>
            <#list guard.equipments as equipment>
                <#if equipment??><li>${equipment.name}</li><#else>erreur, cet équipement n’existe pas</#if>
            </#list>
        </ul>
    </@b.page>