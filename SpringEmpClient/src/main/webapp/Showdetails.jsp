<%@page import="com.java.cli.Employdetails"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="3">
<tr>
	<th>Details Id</th>
	<th>Employ Id</th>
	<th>Project Name</th>
</tr>

<%
		 URL url = new URL("http://localhost:9922/details");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                    + conn.getResponseCode());
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	            (conn.getInputStream())));
	        String output;
	        String res="";
	        while ((output = br.readLine()) != null) {
	        	res+=output;
	         //  out.println(output);
	            
	        }
	        ObjectMapper objectMapper = new ObjectMapper();
	        Employdetails[] details = objectMapper.readValue(res, Employdetails[].class);
	      //  out.println(employ.length);
	        for(Employdetails e : details) {
  %>
  	<tr>
  		<td> <%=e.getEid() %> </td>
  		<td> <%=e.getEmpid() %> </td>
  		<td><%=e.getProjectname() %>  </td>

  	</tr>
  <%
	       	
	        }
	     // out.println(res);
	  %>
	  </table>
</body>
</html>