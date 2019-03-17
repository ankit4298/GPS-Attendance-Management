<%@page import="java.io.OutputStream"%>
<%@page import="pure_java.GetImageDB"%>
<jsp:useBean id="photo" class="pure_java.GetImageDB" scope="session" />
<%
    try {
        String eid = request.getParameter("eid");
        GetImageDB getImageDB = new GetImageDB();

        byte[] imgData = photo.getPhoto(eid);
        response.setContentType("image/jpeg");
        OutputStream o = response.getOutputStream();
        o.write(imgData);
        o.flush();
        o.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>