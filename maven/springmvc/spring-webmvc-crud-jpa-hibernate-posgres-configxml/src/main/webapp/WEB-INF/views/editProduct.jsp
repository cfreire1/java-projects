<%--
  Created by IntelliJ IDEA.
  User: vm-ubuntu
  Date: 15-06-22
  Time: 19:42
  To change this template use File | Settingmodels | File Templates.
--%>
<%@ include file="includes/include.jsp" %>
<html>
<head>
    <%@ include file="includes/head-master.jsp" %>
</head>
<body>
<%@ include file="includes/body-master.jsp" %>


    <c:forEach items="${model.productos}" var="prod">
        <form:form action="edit-product" method="post"  modelAttribute="producto">
            <form:input path="id" value="${prod.id}"  type="hidden" class="form-control" id="inputidedit" aria-describedby="emailHelp" placeholder="Enter Name"></form:input>
            <div class="form-group">
                <label for="inputidedit">Name</label>
                <form:input path="name" value="${prod.name}"  type="text" class="form-control" id="inputnameedit" aria-describedby="emailHelp" placeholder="Enter Name"></form:input>
            </div>

            <div class="form-group">
                <label for="inputpriceedit">price</label>
                <form:input path="price" value="${prod.price}" type="text" class="form-control" id="inputpriceedit" placeholder="$"></form:input>
            </div>

            <div class="form-group">
                <label for="inputdescripedit">Description</label>
                <form:input path="description" value="${prod.description}" type="text" class="form-control" id="inputdescripedit" placeholder="$"></form:input>
            </div>
            <div class="form-group">
                <label for="inputstockdit">Stock</label>
                <form:input path="stock" value="${prod.stock}"  type="text" class="form-control" id="inputstockdit" placeholder=""></form:input>
            </div>
            <div class="form-group">
                <label for="inputcreate_atedit">create_at</label> Actual: ${prod.create_at}
                <form:input path="create_at" value="${prod.create_at}" type="date" class="form-control" id="inputcreate_atedit" placeholder="yyyy-mm-dd 00:00:00.000"></form:input>
            </div>

<%--            BOTON--%>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                Guardar
            </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">
                                Guardar cambios
                            </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Esta seguro de guardar los cambios?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary">Aceptar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END Modal -->
        </form:form>
    </c:forEach>
    <%@ include file="includes/footer-master.jsp" %>
</body>
</html>
