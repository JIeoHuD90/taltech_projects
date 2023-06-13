<?php
session_start();
$ctransient = "AccessType";

include_once "connect.db.php";
$access = mysqli_connect($server, $user, $password, $database);
if (!$access) {
    die("Connection To DB FAILED: " . mysqli_connect_error());
}

$delete = $_POST['delete'];
foreach ($delete as $key => $value) {
    $queryDel = sprintf("DELETE FROM agents WHERE ID=%s;", $key);
    mysqli_query($access, $queryDel);
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="script.js" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Agents</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="styles/main.css">
</head>

<body>
    <div class="wrapper">
        <div class="topnav">
            <a href="index.php">Home</a>
            <a class="active" href="agents.php">Agents</a>
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
        <div class="article">
            <h2>Our agents</h2>
            <p>
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has
                survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
                publishing software like Aldus PageMaker including versions of Lorem Ipsum. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the
                release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
        </div>
        <?php
        if (isset($_SESSION[$ctransient]) and $_SESSION[$ctransient] == "Admin") {
            echo '<button class="addButton">';
            echo '<a href="addAgent.php" class="btn">Add Agent</a>';
            echo '</button>';
        }
        ?>
        <form method='POST' action='agents.php'>
            <table id="table1">
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <?php if ($_SESSION[$ctransient] == "Admin") {
                        echo "<th> <input type='submit' value='Delete selected record'></button></th>";
                    } ?>
                </tr>
                <?php
                echo $_SESSION['full_name'];

                $query = 'SELECT * FROM agents;';
                $result = mysqli_query($access, $query);
                if (mysqli_num_rows($result) > 0) {

                    while ($row = mysqli_fetch_assoc($result)) {
                        if ($_SESSION[$ctransient] == "Admin") {
                            echo "<tr>
                    <td>" . $row['agent_name'] . "</td>
                    <td>" . $row['mail'] . "</td>
                    <td>" . $row['country'] . "</td>";
                            echo '<td><input name=delete[' . $row['ID'] . '] type="checkbox"></td>';
                            echo "</tr>";
                        } else {
                            echo "<tr>
                        <td>" . $row['agent_name'] . "</td>
                        <td>" . $row['mail'] . "</td>
                        <td>" . $row['country'] . "</td>";
                            echo "</tr>";
                        }
                    }
                } else {
                    echo "Nothing found";
                }
                mysqli_close($link);

                ?>
            </table>
        </form>

        <div class="push"></div>

        <div class="footer">
            <div class='footer-title'>Contacts</div>
            <p>Dolor 4-45,Dolor<br> +dolorsit
                <br>
                <a href="mailto:levagu@taltech.ee">Levagu</a>
            </p>
        </div>
    </div>
    <script>
        function logoutAlert(e) {
            alert("Logged out successfully");
        }
    </script>
</body>

</html>
