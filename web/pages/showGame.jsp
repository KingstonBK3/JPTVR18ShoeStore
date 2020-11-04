<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="updateShoe" method="POST">
    <h3 class="w-100 text-center ">Shoe: ${shoe.name}</h3>
    <div class="form-group w-50 mx-auto">
        <label for="name">Shoe name</label>
        <input  value="${shoe.id}" type="hidden" name="idResource">
        <input  value="${shoe.name}" disabled type="text" class="form-control" id="name" name="name" aria-describedby="nameShoe" placeholder="Enter shoe name" >
        <small id="nameShoe" class="form-text text-muted"></small>
    </div>
    <div class="form-group w-50 mx-auto">    
        <label for="url">Brand</label>
        <input value="${shoe.devName}" disabled type="text" class="form-control" id="devName" name="devName" aria-describedby="devNameHelp" placeholder="Enter shoe brand">
        <small id="devNameHelp" class="form-text text-muted"></small>
    </div>
    <div class="form-group w-50 mx-auto">    
        <label for="price">Price</label>
        <input value="${shoe.price}" disabled type="number" class="form-control" id="price" name="price" aria-describedby="priceHelp" placeholder="Enter price">
        <small id="priceHelp" class="form-text text-muted"></small>
    </div>
    <div class="form-group w-50 mx-auto">    
      <a class="btn btn-primary" href="deleteShoe?idShoe=${shoe.id}">Delete</a>
        <button type="submit" class="btn btn-primary">Change</button>
    </div>
</form>