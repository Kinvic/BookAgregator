<#import "parts/common.ftl" as c>

<@c.page>
    <form action="/admin" method="post">
        <div class="form-row align-items-center">
            <div class="col-auto">
                <label class="sr-only" for="inlineFormInputName">Login</label>
                <input type="text" name="username" class="form-control"placeholder="Login">
            </div>
            <div class="col-auto">
                <label class="sr-only" for="inlineFormInputGroupUsername">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </form>
</@c.page>