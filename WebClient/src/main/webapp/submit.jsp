<%@page import="bean.stateless.ejb.BookFacade"%>
<%@page import="bean.entity.ejb.Book"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form Submission</title>
</head>
<body>

	<%!Hashtable<String, String> table = new Hashtable<String, String>();
  InitialContext            initialContext;%>

	<%
	  table.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
	  table.put("java.naming.provider.url", "t3://localhost:7001");
	  table.put("java.naming.factory.url.pkgs", "weblogic.corba.j2ee.naming.url:weblogic.corba.client.naming");
	  table.put("java.naming.security.principal", "***");
	  table.put("java.naming.security.credentials", "***");
	  initialContext = new InitialContext(table);
	%>
	
	<%int id = Integer.parseInt(request.getParameter("id"));
	  String name = request.getParameter("name");
	  String choice = request.getParameter("choice");
	  Book book = new Book();
	  book.setId(id);
	  book.setName(name);
	  String jndiName = "jndi/books#bean.stateless.ejb.BookFacade";
	  BookFacade facade = (BookFacade)initialContext.lookup(jndiName);
	%>
	
	<%if(choice.equalsIgnoreCase("add")) {
	     facade.add(book);
	     out.print("book is added successfully");
	     out.print(facade.getBooks());
	  }else if(choice.equalsIgnoreCase("delete")) {
	    facade.remove(book);
	    out.print("book is removed successfully");
      out.print(facade.getBooks());
	  }else if(choice.equalsIgnoreCase("update")) {
	      out.print(facade.getBooks());
	    facade.update(book);
	    out.print("book is updated successfully");
	      out.print(facade.getBooks());
	  }else if(choice.equalsIgnoreCase("find")) {
	    out.println(facade.find(book.getId()));
	  }else{
	    out.print(facade.getBooks());
	  }
	
	  %>
	
	
	
</body>
</html>