<?php
session_start();
$ctransient = "AccessType";
if ($_SESSION[$ctransient] != "Admin") {
    header("Location: index.php");
    exit();
}
include_once "connect.db.php";
$access = mysqli_connect($server, $user, $password, $database);
if (!$access) {
    die("Connection To DB FAILED: " . mysqli_connect_error());
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Payments</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="styles/main.css">
</head>

<body>
    <div class="wrapper">
        <div class="topnav">
            <a href="index.php">Home</a>
            <a href="agents.php">Agents</a>
            <?php
            if ($_SESSION[$ctransient] == "Admin") {
                echo " <a href='clients.php'>Clients</a>
            <a href='packages.php'>Packages</a>
            <a class=active href='payments.php'>Payments</a>";
            }
            ?>
            <div class="login_register">

                <?php if (isset($_SESSION[$ctransient]) and $_SESSION[$ctransient] == "Admin") {
                    echo "<a style='background-color :rgb(252, 223, 3);color:rgb(0,0,0);'>" . $_SESSION["user"] . "</a>";
                    echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
                } ?>
            </div>
        </div>
        <div class="article">
            <h2>Our Payments</h2>
            <p>
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has
                survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
                publishing software like Aldus PageMaker including versions of Lorem Ipsum. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the
                release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
        </div>

        <?php if ($_SESSION[$ctransient] == "Admin") {
            echo "<table id='table1'>
            <tr>
                <th>Client ID</th>
                <th>Client name</th>
                <th>Package Name</th>
                <th>Amount</th>
                <th>Package ID</th>
                <th>Due</th>
                
            </tr>";
            $query = 'SELECT C.personal_id,C.client_name,PP.package_name,PP.amount_value,PP.ID,PP.due_date FROM  packages PP  INNER JOIN clients C ON PP.package_name=C.package_name;';
            $result = mysqli_query($access, $query);
            if (mysqli_num_rows($result) > 0) {
                while ($row = mysqli_fetch_assoc($result)) {
                    echo "<tr>
                <td>" . $row['personal_id'] . "</td>
                <td>" . $row['client_name'] . "</td>
                <td>" . $row['package_name'] . "</td>
                <td>" . $row['amount_value'] . "</td>
                <td>" . $row['ID'] . "</td>
                <td>" . $row['due_date'] . "</td>";
                    echo
                    '</tr>';
                }
                echo "</pre>";
            } else {
                echo "Nothing found";
            }
            mysqli_close($access);
        }
        ?>

        </table>
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