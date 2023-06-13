<?php
/*This php script starts the session and if not logged in redirects to the first page*/
session_start();
$ctransient = "AccessType";
if ($_SESSION[$ctransient] != "Admin" and $_SESSION[$ctransient] != "User") {
    header("Location: index.php");
    exit();
}
include_once "connect.db.php";
$access = mysqli_connect($server, $user, $password, $database);
if (!$access) {
    die("Connection To DB FAILED: " . mysqli_connect_error());
}
/*Form validation*/
$personalId = $packagelId = $phone = $country = "";
$personalIdError = $packagelIdError = $phoneLenghtError = $phoneError = $countryError = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $packageId = test_input($_POST["packageId"]);
    $packageId = filter_var($packageId, FILTER_SANITIZE_STRING);
    if ($packageId != 'package1' and $packageId != 'package2' and $packageId != 'package3' and $packageId != 'package4') {
        $packageIdError = 'Invalid package input!';
    }
    if (empty($packageIdError)) {
        $query2 = sprintf("UPDATE clients SET package_name='%s'  WHERE client_name LIKE '%s';", $packageId, $_SESSION['full_name']);
        echo $query2;
        mysqli_query($access, $query2);
        header('Location: profile.php');
    }
}

function test_input($data)
{
    $data = str_replace(array('\'', '"', ',', ';', '<', '>'), ' ', $data);
    $data = trim($data);
    $data = trim(preg_replace('/\t+/', '', $data));
    $data = trim(preg_replace('/\n+/', '', $data));
    $data = trim(preg_replace('/\t+/', '', $data));
    $data = trim(preg_replace('/\r+/', '', $data));
    $data = trim(preg_replace('/\0+/', '', $data));
    $data = trim(preg_replace('/\v+/', '', $data));
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    $data = strip_tags($data);
    return $data;
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Choose Package</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="styles/forms.css">

</head>

<body>
    <div class="wrapper">
        <div class="topnav">
            <a href="index.php">Home</a>
            <a href="agents.php">Agents</a>

            <div class="login_register">
                <?php
                /*Displays currently signed in user and logout button*/
                if (isset($_SESSION[$ctransient])) {
                    echo "<a href='profile.php' >" . $_SESSION["user"] . "</a>";
                    echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
                } ?>
            </div>
        </div>

        <div class="regform">
            <form action="choosePackage.php" method="POST">

                <label for="packageId">Package ID:</label>
                <select id="packageId" name="packageId" required>
                    <option value='' selected="selected">Package ID</option>
                    <option value="package1">Package 1</option>
                    <option value="package2">Package 2</option>
                    <option value="package3">Package 3</option>
                    <option value="package4">Package 4</option>
                </select>

                <button type="submit" name="submit">Choose Package</button>
            </form>
            <span id="error"><?php if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                                    echo "$packageIdError";
                                } ?></span>
        </div>
    </div>
    <script>
        function logoutAlert(e) {
            alert("Logged out successfully");
        }
    </script>
</body>

</html>