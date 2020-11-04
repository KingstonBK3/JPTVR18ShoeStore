
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <form action="login" method="POST">
        <h4 class="w-100 text-center ">Enter login data</h4>
        <div class="form-group w-50 mx-auto">    
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp" placeholder="Login">
            <small id="emailHelp" class="form-text text-muted"></small>
        </div>
        <div class="form-group w-50 mx-auto">    
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" aria-describedby="passwordHelp" placeholder="Password">
            <small id="emailHelp" class="form-text text-muted"></small>
        </div>
        <div class="form-group w-50 mx-auto text-center">
            <button type="submit" class="btn btn-primary w-50 mt-4">Login</button>
                <small id="emailHelp" class="form-text text-muted">
                   Account exists? <a href="showFormAddCustomer">Sign up</a>
                </small>
        </div>
    </form>