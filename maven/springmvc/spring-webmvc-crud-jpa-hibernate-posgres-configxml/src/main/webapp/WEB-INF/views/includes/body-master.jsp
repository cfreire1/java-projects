<div class="container"><%--start Containers--%>
<%--1 Menu--%>
<nav class="navbar navbar-light bg-light">
<%--    <form:form class="form-inline" method="get" action="login">--%>
<%--        <button class="btn btn-primary" type="submit">Login</button>--%>
<%--    </form:form>--%>
</nav>

<%--2 Menu--%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="<c:url value="/product/"/>"> Home</a>
            <a class="nav-item nav-link" href="<c:url value="/product/go-add-product"/>"> Agregar Producto</a>
            <a class="nav-item nav-link" href="<c:url value="/product/list"/>"> Listar Productos</a>
        </div>
    </div>
</nav>
    <br/>

