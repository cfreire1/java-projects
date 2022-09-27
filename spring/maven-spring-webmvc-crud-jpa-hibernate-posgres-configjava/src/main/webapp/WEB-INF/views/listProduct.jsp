<%--
  Created by IntelliJ IDEA.
  User: vm-ubuntu
  Date: 13-06-22
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="includes/include.jsp" %>
<html>
<head>
    <%@ include file="includes/head-master.jsp" %>
</head>
<body>
<%@ include file="includes/body-master.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm">
            <%--    Boton Buscar        --%>
            <form:form class="form-inline" method="get" action="search">
                <input name="searchid" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form:form>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <%--     Iteracion de productos       --%>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">name</th>
                    <th scope="col">description</th>
                    <th scope="col">price</th>
                    <th scope="col">stock</th>
                    <th scope="col">fecha</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${model.productos}" var="prod">
                    <tr>
                        <th scope="row">${prod.id}</th>
                        <td><c:out value="${prod.name}"/></td>
                        <td><c:out value="${prod.description}"/></td>
                        <td>$<c:out value="${prod.price}"/></td>
                        <td><c:out value="${prod.stock}"/></td>
                        <td><c:out value="${prod.create_at}"/></td>
                        <td>
                            <%--Boton delete--%>
                            <form:form action="delete-product" method="get">
                                <input type="hidden" name="deleteid" value="${prod.id}" />
                                <button class="btn btn-primary" type="submit">Delete</button>
                            </form:form>
                        </td>
                        <td>
                            <%--Boton editar--%>
                            <form:form action="go-edit-product" method="get">
                                <input type="hidden" name="editid" value="${prod.id}" />
                                <button class="btn btn-primary" type="submit">Editar</button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>



<%@ include file="includes/footer-master.jsp" %>
</body>
</html>
