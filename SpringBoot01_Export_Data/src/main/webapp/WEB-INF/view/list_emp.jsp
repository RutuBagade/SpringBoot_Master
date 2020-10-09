<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" >
<head>

<link rel="stylesheet" type="text/css"
 href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />


</head>
<body>
 <div class="container">
  <header>
   <h1>Generating Report in Different Formats</h1>
  </header>
  <spring:url value="/report/?type=xls" var="xlsURL" />
 <spring:url value="/report/?type=pdf" var="pdfURL" />
 <spring:url value="/report/?type=csv" var="csvURL" />
  <div class="starter-template">
   <h1>Employee List  :
   <a class="btn btn-primary" href="${xlsURL }" role="button">Export To Excel</a> || <a class="btn btn-primary" href="${pdfURL }" role="button">Export To PDF</a> || <a class="btn btn-primary" href="${csvURL }" role="button">Export To CSV </a></h1>
   <table
    class="table table-striped table-hover table-condensed table-bordered">
    <tr>
     <th>Id</th>
     <th>Name</th>
      <th>Age</th>
     <th>Department</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
     <tr>
      <td>${employee.id}</td>
      <td>${employee.name}</td>
      <td>${employee.age}</td>
      <td>${employee.dept}</td>
     </tr>
    </c:forEach>
   </table>
  </div>

 </div>

 <script type="text/javascript"
  src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>