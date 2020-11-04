<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <h3 class="w-100 text-center">Editing shoes</h3>
    <form action="updateShoe?idShoe=${idShoe}" method="POST">
        <div class="form-group w-50 mx-auto">    
            <label for="name">Shoe name</label>
            <input type="hidden" name="idShoe" value="${shoe.id}">
            <input value="${shoe.name}"  type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" placeholder="Shoe name">
            <small id="nameHelp" class="form-text text-muted"></small>
        </div>
        <div class="form-group w-50 mx-auto">    
            <label for="brandName">Brand</label>
            <input value="${shoe.brandName}"  type="text" class="form-control" id="brandName" name="brandName" aria-describedby="brandNameHelp" placeholder="Brand">
            <small id="brandNameHelp" class="form-text text-muted"></small>
        </div>
        <div class="form-group w-50 mx-auto">    
            <label for="price">Price</label>
            <input value="${shoe.price}"  type="number" class="form-control" id="price" name="price" aria-describedby="priceHelp" placeholder="Price">
            <small id="priceHelp" class="form-text text-muted"></small>
        </div>
        <div class="form-group w-50 mx-auto text-center">
            <button type="submit" class="btn btn-primary w-50 mt-4">change</button>
        </div>                  
    </form>