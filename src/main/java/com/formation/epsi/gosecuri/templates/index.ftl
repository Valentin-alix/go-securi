<#import "base.ftl" as b>

    <@b.page>
        <ul>
            <#list guards?sort_by("firstname") as guard>
            <li><a href="${guard.id}.html">${guard.firstname} ${guard.lastname}</a></li>
            </#list>
        </ul>
    </@b.page>