    <?php
    session_start();
    $ctransient = "AccessType";
    if ($_SESSION[$ctransient] == "Admin" and $_SESSION[$ctransient] != "User") {
        header("Location: index.php");
        exit();
    }
    include_once "connect.db.php";
    $access = mysqli_connect($server, $user, $password, $database);
    if (!$access) {
        die("Connection To DB FAILED: " . mysqli_connect_error());
    } ?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>Profile</title>
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
                <a  href='packages.php'>Packages</a>
                <a href='payments.php'>Payments</a>";
                }
                ?>
                <div class="login_register">
                    <?php if (isset($_SESSION[$ctransient]) and !empty($_SESSION[$ctransient])) {
                        echo "<a href='profile.php' >" . $_SESSION["user"] . "</a>";
                        echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
                    }
                    ?>
                </div>
            </div>
            <?php if (isset($_SESSION[$ctransient]) and $_SESSION[$ctransient] == "User") {
                echo '<button class="addButton">';
                echo '<a href="choosePackage.php" class="btn">Choose Package</a>';
                echo '</button>';
            } ?>
            <table id="table1">
                <tr>
                    <th>Package</th>
                    <th>Designated Agent</th>
                    <th>Cost</th>
                    <th>Due</th>
                    <th>Personal ID</th>
                </tr>



        </div>

        <?php
        $query = sprintf("SELECT C.package_name,agent_name,amount_value,due_date,personal_id FROM clients C INNER JOIN agents A ON C.country=A.country INNER JOIN packages P ON C.package_name=P.package_name WHERE client_name LIKE '%s';", $_SESSION['full_name']);
        $result = mysqli_query($access, $query);
        if (mysqli_num_rows($result) > 0) {

            while ($row = mysqli_fetch_assoc($result)) {
                echo "<tr>
                                    <td>" . $row['package_name'] . "</td>
                                    <td>" . $row['agent_name'] . "</td>
                                    <td>" . $row['amount_value'] . "</td>
                                    <td>" . $row['due_date'] . "</td>
                                    <td>" . $row['personal_id'] . "</td>
                                    </tr>";
            }
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