<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="jp.trans_it.world.model.entity.Country"%>

<%
	List<Country> list = (List<Country>) request.getAttribute("countries");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>世界の国々</title>
	</head>
	<body>
		<table>
			<tr>
				<th><a href="top?sort=Name">名前</a></th>
				<th><a href="top?sort=Continent">州</a></th>
				<th><a href="top?sort=Population">人口</a></th>
				<th><a href="top?sort=SurfaceArea">面積</a></th>
			</tr>
<%
	for(Country country : list) {
%>
			<tr>
				<td><%= country.getName() %></td>
				<td><%= country.getContinent() %></td>
				<td><%= country.getPopulation() %></td>
				<td><%= country.getSurfaceArea() %></td>
			</tr>
<%
	}
%>
		</table>
	</body>
</html>