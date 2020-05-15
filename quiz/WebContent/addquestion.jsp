<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date,java.text.DateFormat,java.time.LocalTime" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Questions</title>
</head>
<body>

<form action="question" method="post">
  <table cellpadding="10">
             
      
    <tr>
        <td><b>Question:</b></td>
        <td><textarea rows="10" cols="100" name="question" required></textarea></td>
    </tr>
    
    <tr>
        <td><b>A:</b></td>
        <td><textarea rows="2" cols="50" name="a" required></textarea></td>
    </tr>

    <tr>
        <td><b>B:</b></td>
        <td><textarea rows="2" cols="50" name="b" required></textarea></td>
    </tr>

    <tr>
        <td><b>C:</b></td>
        <td><textarea rows="2" cols="50" name="c" required></textarea></td>
    </tr>

    <tr>
        <td><b>D:</b></td>
        <td><textarea rows="2" cols="50" name="d" required></textarea></td>
    </tr>
    
    <tr>
    <td><b>Answer:</b></td>
    <td>
    <input type="radio" name="answer" value="A" required> A &nbsp;
    <input type="radio" name="answer" value="B"> B &nbsp;
    <input type="radio" name="answer" value="C"> C &nbsp;
    <input type="radio" name="answer" value="D"> D &nbsp;
    </td>
    </tr>

    <tr>
        <td colspan="2" align="center"><input type="submit" value="Add"/></td>
    </tr>
  </table>
</form>        

</body>
</html>