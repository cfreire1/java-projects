<%--
  Created by IntelliJ IDEA.
  User: vm-ubuntu
  Date: 15-06-22
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="includes/include.jsp" %>
<html>
<head>
    <%@ include file="includes/head-master.jsp" %>
</head>
<body>
<%@ include file="includes/body-master.jsp" %>

    <!-- Content here -->

    <form:form action="add-product" method="post"  modelAttribute="producto">
        <div class="form-group">
            <label for="inputnameadd">Name</label>
            <form:input path="name"  type="text" class="form-control" id="inputnameadd" aria-describedby="emailHelp" placeholder="Enter Name"></form:input>
        </div>

        <div class="form-group">
            <label for="inputpriceadd">price</label>
            <form:input path="price" type="text" class="form-control" id="inputpriceadd" placeholder="$"></form:input>
        </div>

        <div class="form-group">
            <label for="inputdescrpadd">Description</label>
            <form:input path="description" type="text" class="form-control" id="inputdescrpadd" placeholder="..."></form:input>
        </div>
        <div class="form-group">
            <label for="inputstockdit">Stock</label>
            <form:input path="stock"  type="text" class="form-control" id="inputstockdit" placeholder=""></form:input>
        </div>
        <div class="form-group">
            <label for="inputcreate_atedit">create_at</label>
            <form:input path="create_at" type="date" class="form-control" id="inputcreate_atedit" placeholder="yyyy-mm-dd 00:00:00.000"></form:input>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>

    <%@ include file="includes/footer-master.jsp" %>
</body>
</html>
