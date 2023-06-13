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
    <title>Clients</title>
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
                echo " <a class=active href='clients.php'>Clients</a>
            <a href='packages.php'>Packages</a>
            <a href='payments.php'>Payments</a>";
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
            <h2>Our clients</h2>
            <p>
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has
                survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
                publishing software like Aldus PageMaker including versions of Lorem Ipsum. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the
                release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
        </div>
        <form method='POST' action='clients.php'>
            <?php
            if (isset($_SESSION[$ctransient]) and $_SESSION[$ctransient] == "Admin") {
                echo '<button class="addButton">';
                echo '<a href="addClient.php" class="btn">Add Client</a>';
                echo '</button>';
            }
            ?><?php
                if ($_SESSION[$ctransient] == "Admin") {
                    echo "<table id='table1'>
            <tr>
                <th>Client ID</th>
                <th>Company Name</th>
                <th>Date of birth</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Personal ID</th>
                <th>Country</th>
                <th>Designated Agent</th>
                <th>Package</th>
                <th> <input type='submit' value='Delete selected record'></button></th>
            </tr>";
                    $query = 'SELECT C.ID,client_name,dob,C.mail,phone,personal_id,C.country,agent_name,package_name FROM agents A INNER JOIN clients C ON A.country=C.country WHERE A.country=C.country AND agent_name LIKE "%John%" ORDER BY C.ID ASC;';
                    $result = mysqli_query($access, $query);
                    if (mysqli_num_rows($result) > 0) {

                        while ($row = mysqli_fetch_assoc($result)) {
                            if ($_SESSION[$ctransient] == "Admin") {
                                echo "<tr>
                    <td>" . $row['ID'] . "</td>
                    <td>" . $row['client_name'] . "</td>
                    <td>" . $row['dob'] . "</td>
                    <td>" . $row['mail'] . "</td>
                    <td>" . $row['phone'] . "</td>
                    <td>" . $row['personal_id'] . "</td>
                    <td>" . $row['country'] . "</td>
                    <td>" . $row['agent_name'] . "</td>
                    <td>" . $row['package_name'] . "</td>";
                                echo '<td><input name=delete[' . $row['ID'] . '] type="checkbox"></td>';
                                echo "</tr>";
                            } else {
                                echo "<tr>
                    <td>" . $row['ID'] . "</td>
                    <td>" . $row['client_name'] . "</td>
                    <td>" . $row['dob'] . "</td>
                    <td>" . $row['mail'] . "</td>
                    <td>" . $row['phone'] . "</td>
                    <td>" . $row['personal_id'] . "</td>
                    <td>" . $row['country'] . "</td>
                    <td>" . $row['agent_name'] . "</td>
                    <td>" . $row['package_name'] . "</td>
                    </tr>";
                            }
                        }
                    } else {
                        echo "Nothing found";
                    }
                }
                mysqli_close($link);

                ?>
        </form>
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