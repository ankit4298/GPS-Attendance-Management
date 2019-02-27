<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int result=Integer.parseInt(request.getParameter("result"));
    out.print(result);
//int result=1;
    if(result==1){
        out.print("1");
    }else if(result==0){
        out.print("0");
    }
%>