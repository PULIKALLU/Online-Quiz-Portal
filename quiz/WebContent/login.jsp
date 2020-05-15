

<!DOCTYPE html>
<head>
  <title>Login Form</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<script>
</script>
  <div class="login-page">
  <div class="form">
      <form class="login-form" action="user" method="post">
      <select name="usertype">
      <option value="Teacher">Teacher</option>
      <option value="Student">Student</option>
      </select>
      <input type="text" name="username" placeholder="username.." />
      <input type="password" name="password" placeholder="password.."/>
      <input type="submit" name="submit" value="login"/>
      <p class="message">Not registered? <a href="register.jsp">Create an account</a></p>
    </form>
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script  src="index.js"></script>
</body>
</html>
