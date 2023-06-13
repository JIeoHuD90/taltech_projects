<?php
session_start();
$ctransient = "AccessType";

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  include_once "connect.db.php";
  $access = mysqli_connect($server, $user, $password, $database);

  $query = sprintf("SELECT login_user,password_user,role_user FROM users WHERE login_user LIKE '%s' AND password_user LIKE '%s' AND role_user LIKE 'admin';", $_POST['username'], $_POST['password']);
  $result = mysqli_query($access, $query);
  if (mysqli_num_rows($result) > 0) {
    $_SESSION[$ctransient] = "Admin";
    $_SESSION["user"] = $_POST['username'];
    $credentialsError = null;
    header('Location: index.php');
    exit;
  }
  $query2 = sprintf("SELECT login_user,password_user,role_user FROM users WHERE login_user LIKE '%s' AND password_user LIKE '%s' AND role_user LIKE 'user';", $_POST['username'], $_POST['password']);
  $result2 = mysqli_query($access, $query2);
  if (mysqli_num_rows($result2) > 0) {
    $_SESSION[$ctransient] = "User";
    $_SESSION["user"] = $_POST['username'];
    $query3 = sprintf("SELECT full_name FROM users WHERE login_user LIKE '%s';", $_SESSION['user']);
    $result3 = mysqli_query($access, $query3);
    if (mysqli_num_rows($result3) > 0) {
      while ($row = mysqli_fetch_assoc($result3)) {
        $_SESSION['full_name'] = $row['full_name'];
        $_SESSION['loginError'] = null;
        header('Location: profile.php');
        exit;
      }
    }
    mysqli_close($access);
  } else {
    $loginError = "Invalid credentials.";
  }
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Home</title>
  <script src="script.js" defer></script>
  <meta charset="utf-8" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" href="styles/main.css">
</head>

<body>
  <div class="wrapper">
    <div class="topnav">
      <a class="active" href="index.php">Home</a>
      <a href="agents.php">Agents</a>
      <?php
      if ($_SESSION[$ctransient] == "Admin") {
        echo " <a href='clients.php'>Clients</a>
            <a href='packages.php'>Packages</a>
            <a href='payments.php'>Payments</a>";
      }
      ?>
      <div class="login_register">
        <?php if (isset($_SESSION[$ctransient]) and !empty($_SESSION[$ctransient])) {
          if ($_SESSION[$ctransient] == "Admin") {
            echo "<a style='background-color :rgb(252, 223, 3);color:rgb(0,0,0);'>" . $_SESSION["user"] . "</a>";
            echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
          } else {
            echo "<a style='background-color :rgb(252, 223, 3);color:rgb(0,0,0);' href='profile.php' >" . $_SESSION["user"] . "</a>";
            echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
          }
        } else {
          echo " <nav>
          <ul>
            <li id='login'>
              <a id='login-trigger' href='#'>
                Log in <span>▼</span>
              </a>
              <div id='login-content'>
              <form class='form' action='index.php' method='POST'>
              <input type='text' name='username' id='uname' placeholder='username' required>
              <input type='password' name='password' id='pass' placeholder='password' required>
                  <input type='submit' id='submit' value='Log in'>
                </form>";
          if (!empty($loginError)) {
            echo "<h1 id='error' style='font-size: xx-large;color: red;'>" . $loginError . "</h1>";
          }
          echo "  </div>
            </li>
            <li id='signup'>
              <a href='register.php'>Register</a>
            </li>
          </ul>
        </nav>";
        }
        ?>
      </div>
    </div>

    <div class="row">
      <div class="column1">
        <h2>News</h2>
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen
          book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more
          recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since
          the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised
          in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
      </div>
      <div class="column2">
        <h2>About Us </h2>
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen
          book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more
          recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since
          the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised
          in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
      </div>
      <div class="column3">
        <h2> Why us?</h2>
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen
          book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more
          recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since
          the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised
          in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
      </div>
    </div>
    <div class="push"></div>
  </div>
  <div class="footer">
    <div class='footer-title'>Contacts</div>
    <p>Dolor 4-45,Dolor<br> +dolorsit
      <br>
      <a href="mailto:levagu@taltech.ee">Levagu</a>
    </p>
  </div>
  <script>
    function logoutAlert(e) {
      alert("Logged out successfully");
    }
  </script>
</body>

</html>