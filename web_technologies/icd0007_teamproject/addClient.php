<?php
/*This php script starts the session and if not logged in redirects to the first page*/
session_start();
$ctransient = "AccessType";
if ($_SESSION[$ctransient] != "Admin") {
    header("Location: index.php");
    exit();
}

/*Form validation*/
$duedate = $cost = $package = $name = $country = $birthday = $personalId = $phone = $email = "";
$nameError = $birthdayError = $personalIdError = $phoneError = $emailLenghtError = $emailFormatError = "";

$DateNow = date("Y-m-d");
$userDate = $_POST["birthday"];
$timestamp = strtotime($userDate);
$userDate =  date('Y-m-d', $timestamp);

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $cost = test_input($_POST['cost']);
    $cost = filter_var($cost, FILTER_SANITIZE_STRING);
    if (strlen($cost) > 15) {
        $costError = "Cost must be less than 15 characters!<br>";
    }
    if (!preg_match("/^([0-9a-zA-Z' $€£]+)$/", $cost)) {
        $costError = "Invalid characters in cost!<br>";
    }

    $duedate = test_input($_POST['duedate']);
    $duedate = filter_var($duedate, FILTER_SANITIZE_NUMBER_INT);
    $dateMIN = strtotime(date("Y-m-d") . "+1 year");
    $dateMAX = strtotime(date("Y-m-d") . "+4 year");

    $package = test_input($_POST['package']);
    if (strlen($package) > 64 and strlen($package) < 2) {
        $packageError = "Invalid package name length!<br>";
    }
    if (!preg_match("/^([0-9a-zA-Z' ]+)$/", $package)) {
        $packageError = "Invalid characters in package name!<br>";
    }

    $country = test_input($_POST["country"]);
    $country = filter_var($country, FILTER_SANITIZE_STRING);
    if ($country != 'Estonia' and $country != 'Germany' and $country != 'Finland' and $country != 'Sweden') {
        $countryError = 'Invalid country input!<br>';
    }

    $name = test_input($_POST["name"]);
    $name = filter_var($name, FILTER_SANITIZE_STRING);
    if (strlen($name) > 64 or strlen($name) < 2) {
        $nameError = "Invalid name length!<br>";
    }
    if (!preg_match("/^([a-zA-Z' ]+)$/", $name)) {
        $nameError = "Invalid characters in name!<br>";
    }

    $birthday = test_input($_POST["birthday"]);
    $birthday = filter_var($birthday, FILTER_SANITIZE_STRING);
    if ($userDate > $DateNow) {
        $birthdayError = "Birthday cannot be in the future!<br>";
    }

    $personalId = test_input($_POST["personalId"]);
    if (strlen($personalId) > 20 and strlen($personalId) < 5) {
        $personalIdError = "Invalid Personal ID length!<br>";
    }
    if (!preg_match("/^([0-9]+)$/", $personalId)) {
        $personalIdError = "Invalid characters in Personal ID!<br>";
    }

    $phone = test_input($_POST["phone"]);
    if (strlen($phone) > 20 and strlen($phone) < 3) {
        $phoneError = "Invalid phone length!<br>";
    }
    if (!preg_match("/^([+0-9]+)$/", $phone)) {
        $phoneError = "Invalid characters in phone number!<br>";
    }

    $email = test_input($_POST["email"]);
    $email = filter_var($email, FILTER_SANITIZE_STRING);
    if (strlen($email) > 64 or strlen($email) < 5) {
        $emailError = "Invalid email length!<br>";
    }
    if (!filter_var($_POST["email"], FILTER_VALIDATE_EMAIL)) {
        $emailError = "Invalid email format!<br>";
    }

    if (empty($costError) and empty($packageError) and empty($nameError)  and empty($birthdayError) and empty($personalIdError) and empty($phoneError) and empty($emailError)) {
        include_once "connect.db.php";
        $access = mysqli_connect($server, $user, $password, $database);
        if (!$access) {
            die("Connection To DB FAILED: " . mysqli_connect_error());
        }
        $query = sprintf("INSERT INTO clients(client_name,dob,mail,phone,personal_id,country,package_name) VALUES('%s','%s','%s','%s','%s','%s','%s');", $name, $birthday, $email, $phone, $personalId, $country, $package);
        $query2 = sprintf("SELECT package_name FROM packages WHERE package_name LIKE '%s';", $package);
        mysqli_query($access, $query);
        mysqli_close($link);

        $result = mysqli_query($access, $query2);
        if (mysqli_num_rows($result) == 0) {
            $query3 = sprintf("INSERT INTO packages(package_name,due_date,amount_value) VALUES('%s','%s','%s');", $package, $duedate, $cost);
            mysqli_query($access, $query3);
            mysqli_close($link);
        }
        $success = "A client was saved successfully.";
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
    <title>Add Client</title>
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
            <form action="addClient.php" method="POST">
                <label for="name">Full name:</label>
                <input type="text" name="name" id="name" placeholder="Full name" required>
                <label for="birthday">Date of birth:</label>
                <input type="date" name="birthday" id="birthday" min="1920-01-02" max="<?php
                                                                                        $dateMAX = strtotime(date("Y-m-d") . "-18 year");
                                                                                        echo date("Y-m-d", $dateMAX); ?>" required>
                <label for="personalId">Personal ID:</label>
                <input type="text" name="personalId" id="personalId" placeholder="Personal ID" required>
                <label for="phone">Phone number:</label>
                <input type="tel" name="phone" id="phone" placeholder="Phone number" pattern="[+,0-9]{5,12}" required>
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" placeholder="Email" required>
                <label for="package">Package:</label>
                <input type="text" name="package" id="package" placeholder="Select Package" required><br>
                <label for="Country">Country:</label>
                <select id="country" name="country" required>
                    <option value='' selected="selected">Select Country</option>
                    <option value="Estonia">Estonia</option>
                    <option value="Germany">Germany</option>
                    <option value="Finland">Finland</option>
                    <option value="Sweden">Sweden</option>
                </select><br>
                <label for="duedate">Due Date:</label>
                <input type="date" name="duedate" id="duedate" min="<?php
                                                                    $dateMIN = strtotime(date("Y-m-d") . "1 month");
                                                                    echo date("Y-m-d", $dateMAX); ?>" max="<?php
                                                                                                            $dateMAX = strtotime(date("Y-m-d") . "+4 year");
                                                                                                            echo date("Y-m-d", $dateMAX); ?>" required>
                <label for="cost">Cost:</label>
                <input type="text" name="cost" id="cost" placeholder="Cost" required>
                <button type="submit" name="submit">Add Client</button>
            </form>
            <span id="error"><?php if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                                    echo "$duedateError $costError $packageError $nameError $birthdayError $personalIdError $phoneError $emailLenghtError $emailFormatError";
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