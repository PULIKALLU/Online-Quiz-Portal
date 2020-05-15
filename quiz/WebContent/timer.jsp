<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script language ="javascript" >
var tim;
var hour= 00;
var min = <%= Integer.parseInt(session.getAttribute("min").toString()) %>;
var sec = <%= Integer.parseInt(session.getAttribute("sec").toString()) %>;
var f = new Date();
function f1() 
{
    f2();
    document.getElementById("starttime").innerHTML = "Your started your Exam at " + f.getHours() + ":" + f.getMinutes();
}
function f2() 
{
  if (parseInt(sec) > 0)
  {
      sec = parseInt(sec) - 1;
      document.getElementById("showtime").innerHTML = "Your Left Time  is :"+hour+" hours:"+min+" Minutes :" + sec+" Seconds";
      tim = setTimeout("f2()", 1000);
  }
  else
  {
    if (parseInt(sec) == 0)
    {
       min = parseInt(min) - 1;
       if (parseInt(min) == -1)
       {
           clearTimeout(tim);
           location.href ="Student.jsp";
       }
       else
       {
           sec = 60;
           document.getElementById("showtime").innerHTML = "Your Left Time  is :" + min + " Minutes ," + sec + " Seconds";
           tim = setTimeout("f2()", 1000);
       }
    }
  }
}
</script>
<body onload="f1()" >
<form id="form1" runat="server">
<div>
  <table width="100%" align="center">
    <tr>
      <td colspan="2"></td>
    </tr>
    <tr>
      <td>
        <div id="starttime"></div><br />
        <div id="endtime"></div><br />
        <div id="showtime"></div>
      </td>
    </tr>
    <tr>
      <td>
          <br />
      </td>
    </tr>
  </table>
  <br />
</div>
</form>
  <iframe src="quiz.jsp" height="600" width="1000" style="position:absolute border:none"></iframe>
</body>
</html>
