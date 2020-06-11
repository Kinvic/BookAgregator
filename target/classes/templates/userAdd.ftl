<#import "parts/common.ftl" as c>

<@c.page>
    Add user

    <form action="/userList/add" method="post">
        <div><label> Login : <input type="text" name="login"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label>Roles:</label></div>
        <div><label><input type="checkbox" name="ADMIN">Admin</label></div>
        <div><label><input type="checkbox" name="MODERATOR">Moderator</label></div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div><input type="submit" value="Add"/></div>
    </form>
</@c.page>