<#import "base.ftl" as b>

    <@b.page>
        <div class="row">
            <div class="col"><h1>${title}</h1></div>
        </div>
        <div class="row">
            <div class="col">
                <ul>
                    <#list guards?sort_by("firstname") as guard>
                        <li><a href="${guard.id}.html">${guard.firstname} ${guard.lastname}</a></li>
                    </#list>
                </ul>
            </div>
        </div>
    </@b.page>