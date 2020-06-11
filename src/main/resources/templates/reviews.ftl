<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

<#if user??>
    <form action="reviews/add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="exampleFormControlInput1">Название обзора</label>
            <input type="text" name="title" class="form-control">
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Текст</label>
            <textarea class="form-control" name="text"rows="3"></textarea>
        </div>
        <div class="form-group">
            <label for="exampleFormControlFile1">Example file input</label>
            <input type="file" class="form-control-file" name="file">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
<#else>
</#if>

<#list reviews as review>
<div class="card mb-3">
    <img src="/img/${review.filename}" class="card-img-top" alt="..." width="936px" height="180">
    <div class="card-body">
        <h5 class="card-title">${review.title}</h5>
        <p class="card-text">${review.text}</p>
        <p class="card-text"><small class="text-muted">${review.authorName}</small></p>
    </div>
</div>
<#else>
    No reviews
</#list>

</@c.page>