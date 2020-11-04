  <%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${loginOn == null || loginOn == false}">
    <div class="jumbotron bg-white">
        <h3 class="w-100 text-center ">Welcome in the Shoes store</h3>
    </div>
</c:if>
<c:if test="${loginOn != null && loginOn == true}">
    <div class="jumbotron bg-white text-center">
        <h4 class="w-100 text-center ">Welcome, ${customer.login}, in the Shoe store!</h4>
    </div>
</c:if>    