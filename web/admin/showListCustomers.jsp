<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="showShoe" method="POST" id="formShowShoe">   
    
        <h3 class="w-100 text-center">List of customers:</h3>
            <div class="form-group w-50 mx-auto">
              <c:forEach var="entry" items="${customersMap}" varStatus="status">
                    <p>${status.index + 1}. ${entry.key.login} role: ${entry.value} 
                      <a href="editCustomerRoles?customerId=${entry.key.id}">Edit</a>
                    </p>
                </c:forEach>
            </div>

</form>