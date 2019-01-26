<%@page import="pure_java.CreateJSON" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    String uname=request.getParameter("uname");
    String header=request.getHeader("LOGIN");
    
    CreateJSON cj=new CreateJSON();
    String jsonText=cj.createJSONresponse(uname);
    
    out.println(jsonText);
    
    if(uname==null){
        uname="did not recieve";
    }

%>