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
$delete = $_POST['delete'];
foreach ($delete as $key => $value) {
    $queryDel = sprintf("DELETE FROM clients WHERE ID=%s;", $key);
    mysqli_query($access, $queryDel);
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Packages</title>
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
            <a class=active href='packages.php'>Packages</a>
            <a href='payments.php'>Payments</a>";
            }
            ?>
            <div class="login_register">
                <?php if (isset($_SESSION[$ctransient]) and !empty($_SESSION[$ctransient])) {
                    if ($_SESSION[$ctransient] != 'Admin') {
                        echo "<a href='profile.php' >" . $_SESSION["user"] . "</a>";
                        echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
                    } elseif ($_SESSION[$ctransient] == 'Admin') {
                        echo "<a style='background-color :rgb(252, 223, 3);color:rgb(0,0,0);'>" . $_SESSION["user"] . "</a>";
                        echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
                    }
                }

                ?>
            </div>
        </div>
        <div class="article">
            <h2>Our Packages</h2>
            <p>
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has
                survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
                publishing software like Aldus PageMaker including versions of Lorem Ipsum. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the
                release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
        </div>

        <table id="table1">
            <tr>
                <th>Package ID</th>
                <th>Package Name</th>
                <th>Due date</th>
                <th>Client Name</th>
                <th>Cost</th>
            </tr>
            <?php
            $query = "SELECT P.ID,client_name,P.package_name,due_date,amount_value FROM packages P INNER JOIN clients C ON C.package_name=P.package_name;";
            $result = mysqli_query($access, $query);
            if (mysqli_num_rows($result) > 0) {

                while ($row = mysqli_fetch_assoc($result)) {
                    echo "<tr>
                 <td>" . $row['ID'] . "</td>
                 <td>" . $row['package_name'] . "</td>
                 <td>" . $row['due_date'] . "</td>
                 <td>" . $row['client_name'] . "</td>
                 <td>" . $row['amount_value'] . "</td>
                 </tr>";
                }
                echo "</pre>";
            } else {
                echo "Nothing found";
            }
            mysqli_close($link);
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