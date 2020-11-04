<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="updateCustomerRoles" method="POST">
    <h3 class="w-100 text-center ">Change user details</h3>
    <div class="form-group w-50 mx-auto">    
        <label for="newLogin">Login</label>
        <input value="${editCustomer.id}" type="hidden" name="customerId">
        <input value="${editCustomer.login}" type="text" class="form-control" id="newLogin" name="newLogin" aria-describedby="newLoginHelp" placeholder="Login">
        <small id="newLoginHelp" class="form-text text-muted"></small>
    </div>
    <div class="form-group w-50 mx-auto">    
        <label for="newPassword">Password</label>
        <input value="" type="text" class="form-control" id="password" name="newPassword" aria-describedby="newPasswordHelp" placeholder="Password">
        <small id="newPasswordHelp" class="form-text text-muted"></small>
    </div>
    <div class="form-group w-50 mx-auto">    
        <label for="currentRole">Current role</label>
        <input value="${topRoleEditCustomer}" type="text" class="form-control" id="currentRole" name="currentRole" aria-describedby="currentRoleHelp" placeholder="Current role">
        <small id="currentRoleHelp" class="form-text text-muted"></small>
    </div>
    <div class="form-group w-50 mx-auto">    
        <label for="newRole">Assigned role</label>
        <select class="custom-select" name="newRole" id="newRole">
          <c:forEach var="role" items="${listRoles}">
              <c:if test="${role.name eq topRoleEditCustomer}">
                  <option value="${role.id}" selected="">${role.name}</option>
              </c:if>
              <c:if test="${role.name ne topRoleEditCustomer}">
                  <option value="${role.id}">${role.name}</option>
              </c:if>
          </c:forEach>
        </select> 
    </div>
    <div class="form-group w-50 mx-auto text-center">
        <button type="submit" class="btn btn-primary w-50 mt-4">Change</button>
    </div>