<#import "base.ftl" as b>

    <@b.page>
        <ul>
            <#list guards as guard>
            <li><a href="${guard.id}.html">${guard.id}</a></li>
            </#list>
        </ul>
    </@b.page>