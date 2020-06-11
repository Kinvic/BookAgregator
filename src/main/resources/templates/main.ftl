<#import "parts/common.ftl" as c>

<@c.page>
<form method="get" action="/">
<div class="form row">
    <label for="colFormLabel" class="col-md-1 col-form-label">Поиск</label>
    <div class="col-md-6">
    <div class="input-field-wrapper">
        <progress
                class="overlay"
                value='0'
                max='100'
        ></progress>
        <input type="text" class="input-field-with-overlay" name="search" value="${search}" placeholder="Введите название книги, автора или ISBN">
    </div>
    </div>
    <div class="col-md-1">
        <button type="submit" class="btn btn-primary">Найти</button>
    </div>
    <div class="col-md-4">
    <label for="inputState">Сортировать по:</label>
    <select id="inputState" class="form-control">
        <option selected>Магазинам</option>
        <option value="1">Цене по возрастанию</option>
        <option value="2">Цене по убыванию</option>
        <option value="3">Названию</option>
    </select>
    </div>
</div>
</form>
<#list books as book>
<div class="card my-3">
    <div class="row no-gutters align-items-center">
        <div class="col-md-1">
            <img src="${book.image}" class="card-img">
        </div>
        <div class="col-md-9">
            <div class="card-body">
                <h5 class="card-title">${book.title}</h5>
                <p class="card-text">${book.author}</p>
                <b class="card-text">${book.price}грн.</b>
                <a href="${book.URL}" class="btn btn-primary" target="_blank">Перейти в магазин</a>
            </div>
        </div>
        <div class="col-md-1">
            <img src="https://www.yakaboo.ua/ua/skin/frontend/bootstrap/yakaboo/images/logo.png">
        </div>
    </div>
</div>
<#else>
<div class="my-3">No books
</div>
</#list>
</@c.page>
