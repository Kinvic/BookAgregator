<#import "parts/common.ftl" as c>

<@c.page>
    List of users
    <a href="/userList/add">Add user</a>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.login}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/userList/edit/${user.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>