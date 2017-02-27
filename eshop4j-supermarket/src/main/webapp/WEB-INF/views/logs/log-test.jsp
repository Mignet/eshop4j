<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.linkwee.core.bytecode.*" %>
<%
	InputStream is = new FileInputStream("/data/TestClass.class");
	byte[] b = new byte[is.available()];
	is.read(b);
	is.close();

	out.println("<textarea style='width:1000px;height=800px'>");
	out.println(JavaClassExecuter.execute(b));
	out.println("</textarea>"); 
%>

