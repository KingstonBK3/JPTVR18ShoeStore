<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 text-center ">Shoes list:</h3>
<div class="form-group w-50 mx-auto">
        <c:forEach var="shoe" items="${listShoes}">
         <p>  
            <div class="form-group row w-100 mx-auto my-0">
                <label for="name" class="col-sm-5 col-form-label">Shoe name</label>
                <div class="col-sm-7">
                    <input  value="${shoe.id}" type="hidden" name="idShoe">
                    <input  value="${shoe.name}" class="form-control-plaintext" readonly="" type="text" id="name" name="name" aria-describedby="nameShoe" placeholder="Введите название игры" >
                    <small id="nameShoe" class="form-text text-muted"></small>
                </div>    
            </div>
            <div class="form-group row w-100 mx-auto my-0">   
                <label for="brandName" class="col-sm-5 col-form-label">Brand</label>
                <div class="col-sm-7">
                    <input value="${shoe.brandName}" class="form-control-plaintext" readonly="" type="text" id="brandName" name="brandName" aria-describedby="brandNameHelp" 
                           placeholder="Enter shoe brand">
                    <small id="brandNameHelp" class="form-text text-muted"></small>
                </div>
            </div>
            <div class="form-group row w-100 mx-auto my-0">  
                <label for="login" class="col-sm-5 col-form-label">Price</label>
                <div class="col-sm-7">
                    <input value="${shoe.price}" class="form-control-plaintext" readonly="" type="number" id="price" name="price" aria-describedby="priceHelp" placeholder="Enter price">
                    <small id="priceHelp" class="form-text text-muted"></small>
                </div>
            </div>

            <div class="form-group w-100 mx-auto">
                <div class="col-sm-7">
                    <a class="btn btn-primary btn-sm mr-1" href="deleteShoe?idShoe=${shoe.id}">Delete</a>
                    <a class="btn btn-primary btn-sm" href="showEditShoe?idShoe=${shoe.id}">Edit</a>
                </div>
            </div>
          </p>
        </c:forEach>
</div>