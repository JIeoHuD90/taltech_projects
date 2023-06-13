<?php
/*This php script starts the session and if not logged in redirects to the first page*/
session_start();
$ctransient = "AccessType";
if ($_SESSION[$ctransient] != "Admin") {
    header("Location: index.php");
    exit();
}

/*Form validation*/
$name = $mail = $country = "";
$nameError = $mailError = $countryError = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = test_input($_POST["name"]);
    $name = filter_var($name, FILTER_SANITIZE_STRING);
    if (strlen($name) > 64 or strlen($name) < 2) {
        $nameError = "Invalid name length!<br>";
    }
    if (!preg_match("/^([a-zA-Z' ]+)$/", $name)) {
        $nameError = "Invalid characters in name!<br>";
    }

    $mail = test_input($_POST["mail"]);
    $mail = filter_var($mail, FILTER_SANITIZE_EMAIL);
    if (strlen($mail) > 64 or strlen($mail) < 5) {
        $mailError = "Invalid email length!<br>";
    }
    if (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
        $mailError = 'Invalid email format!<br>';
    }

    $country = test_input($_POST["country"]);
    $country = filter_var($country, FILTER_SANITIZE_STRING);
    if ($country != 'Estonia' and $country != 'Germany' and $country != 'Finland' and $country != 'Sweden') {
        $countryError = 'Invalid country input!<br>';
    }

    if (empty($nameError) && empty($mailError) && empty($countryError)) {
        include_once "connect.db.php";
        $access = mysqli_connect($server, $user, $password, $database);
        if (!$access) {
            die("Connection To DB FAILED: " . mysqli_connect_error());
        }
        $query = sprintf("INSERT INTO agents(agent_name,mail,country) VALUES('%s','%s','%s');", $name, $mail, $country);
        mysqli_query($access, $query);
        mysqli_close($link);
        $success = "An agent was saved successfully.";
        echo "<script type='text/javascript'>alert('$success');</script>";
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
    <title>Add Agent</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="styles/forms.css">

</head>

<body>
    <div class="wrapper">
        <div class="topnav">
            <a href="index.php">Home</a>
            <a href="agents.php">Agents</a>
            <a href="clients.php">Clients</a>
            <a href="packages.php">Packages</a>
            <a href="payments.php">Payments</a>
            <div class="login_register">
                <?php
                /*Displays currently signed in user and logout button*/
                if ($_SESSION[$ctransient] == "Admin") {
                    echo "<a style='background-color :rgb(252, 223, 3);color:rgb(0,0,0);'>" . $_SESSION["user"] . "</a>";
                    echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
                } ?>
            </div>
        </div>

        <div class="regform">
            <form action="addAgent.php" method="POST">
                <label for="name">Name:</label>
                <input type="text" name="name" id="name" placeholder="name" required>
                <label for="mail">Email:</label>
                <input type="text" name="mail" id="mail" placeholder="mail" required>
                <label for="Country">Country:</label>
                <select id="country" name="country" required>
                    <option value='' selected="selected">Select Country</option>
                    <option value="Estonia">Estonia</option>
                    <option value="Germany">Germany</option>
                    <option value="Finland">Finland</option>
                    <option value="Sweden">Sweden</option>
                </select>
                <button type="submit" name="submit">Add Agent</button>
            </form>
            <span id="error"><?php if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                                    echo "$nameError $mailError $countryError";
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