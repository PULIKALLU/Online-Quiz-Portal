

<!DOCTYPE html>

<head>
  <title>Register Form</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<script>
function func()
 {  
    if(document.getElementByName("password").value != document.getElementByName("password1").value)
	{
		window.alert("passwords does not match!");
		return false;
	}
	
}

</script>
  <div class="login-page">
  <div class="form">
    <form class="register-form"  onsubmit="func()" action="user"  method="post">
      <select name="usertype">
      <option value="Teacher">Teacher</option>
      <option value="Student">Student</option>
      </select>
      <input type="text" name="username" placeholder="username.." />
      <input type="password" id="p" name="password" placeholder="password.."/>
      <input type="text" id="p1" name="password1" placeholder="confirm.."/>
      <input type="submit" name="submit" value="register" />
      <p class="message">Already registered? <a href="login.jsp">Sign In</a></p>
    </form>
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script  src="index.js"></script>

</body>
</html>
