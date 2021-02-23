<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Search Page</title>
</head>
<body>
  <form action="search" method="post">
  	From: <input type="text" name="fromYear" value="${param.fromYear}">
  	<br>
  	To: <input type="text" name="toYear" value="${param.toYear}">
  	<br>
  	Genre: <select name="genre">
  				<c:forEach items="${genres}" var="genre">
  					<option label="${genre.name}">${genre.name}</option> 
  				</c:forEach>
  	       </select> 
  	       <button>Search</button>
  </form>
  <table>
  	<thead>
  		<tr>
  			<th>Title</th>
  			<th>Year</th>
  			<th>IMDB</th>
  		</tr>
  	</thead>
  	<tbody>
  		<c:forEach items="${movies}" var="movie">
  		   <tr>
  		   		<td>${movie.title}</td>
  		   		<td>${movie.year}</td>
  		   		<td><a href="http://www.imdb.com/title/${movie.imdb}" target="_blank">Visit me!</a></td>
  		   </tr>
  		</c:forEach>
  	</tbody>
  </table>
</body>
</html>