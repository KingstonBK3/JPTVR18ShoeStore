<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="index">Shoes store</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
    <c:if test="${topRoleCurrentCustomer ne null}">
      <li class="nav-item active">
        <a class="nav-link" href="logout">Log out <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="showFormAddShoes">Add new shoe</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="listShoes">Shoes list</a>
      </li>
        <li class="nav-item">
          <c:if test="${topRoleCurrentCustomer eq 'ADMIN'}">
            <a class="nav-link" href="showListCustomers">Users list</a>
          </c:if>
      </li>
    </c:if>
    <c:if test="${topRoleCurrentCustomer eq null}"> 
      <li class="nav-item">
        <a class="nav-link" href="showFormLogin">Login</a>
      </li>
    </c:if>
      <li class="nav-item dropdown">
        <div class="dropdown-menu">
          <c:if test="${topRoleCurrentCustomer ne null}">
            <a class="dropdown-item" href="logout">Log out </a>
            <a class="dropdown-item" href="showFormAddShoes">Add new shoe</a>
            <a class="dropdown-item" href="listShoes">Shoes list</a>
            <c:if test="${topRoleCurrentCustomer eq 'ADMIN'}">
                <a class="dropdown-item" href="showListShoes">Users list</a>
            </c:if>
          </c:if>
        <c:if test="${topRoleCurrentCustomer eq null}"> 
            <a class="dropdown-item" href="showFormLogin">Login</a>
        </c:if>
        </div>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<div class="jumbotron bg-white"></div>