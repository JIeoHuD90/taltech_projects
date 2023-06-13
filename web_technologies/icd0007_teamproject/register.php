<?php
/*This php script starts the session and if already logged in redirects to the first page*/
session_start();
$ctransient = "AccessType";
if (isset($_SESSION[$ctransient])) {
    header("Location: index.php");
    exit();
}
?>
<?php

/*This script validates registration credentials*/
$date = $fname = $lname  = $email  = $user_password = $user_password2 = $uname = "";
$passMatchError = $passError = $passLenghtError = $unameError = $fnameError = $lnameError = $emailValidError = $emailLenghtError = $dateValidError = $dateLimitError = $dateCheckError = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $user_password = $_POST["password"];
    $user_password2 = $_POST["password2"];
    if ($user_password != $user_password2) {
        $passMatchError = "Passwords do not match!<br>";
    }
    if ($user_password == $uname) {
        $passError = "Password and username must differ!<br>";
    }
    if (!isset($_POST["password"]) and !isset($_POST["password2"])) {
        $passLenghtError = "Passwords length must be greater than 0 symbols!<br>";
    }

    $personalId = test_input($_POST["personalId"]);
    $personalId = filter_var($personalId, FILTER_SANITIZE_STRING);
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

    $country = test_input($_POST["country"]);
    $country = filter_var($country, FILTER_SANITIZE_STRING);
    if ($country != 'Estonia' and $country != 'Germany' and $country != 'Finland' and $country != 'Sweden') {
        $countryError = 'Invalid country input!<br>';
    }

    $uname = test_input($_POST["uname"]);
    $uname = filter_var($uname, FILTER_SANITIZE_STRING);
    if (strlen($lname) > 64 && strlen($lname) < 2) {
        $unameError = "Invalid username length!<br>";
    }
    $fname = test_input($_POST["fname"]);
    $fname = filter_var($fname, FILTER_SANITIZE_STRING);
    if (strlen($fname) > 64 && strlen($fname) < 2) {
        $fnameError = "Invalid First Name length!<br>";
    }
    if (!preg_match("/^([0-9a-zA-Z' ]+)$/", $fname)) {
        $fnameError = "Invalid characters in first name!<br>";
    }
    $lname = test_input($_POST["lname"]);
    $lname = filter_var($lname, FILTER_SANITIZE_STRING);
    if (strlen($lname) > 64 && strlen($lname) < 2) {
        $lnameError = "Invalid Last Name length!<br>";
    }
    if (!preg_match("/^([0-9a-zA-Z' ]+)$/", $lname)) {
        $lnameError = "Invalid characters in last name!<br>";
    }
    $fullName = $fname . " " . $lname;
    $email = test_input($_POST["email"]);
    $email = filter_var($email, FILTER_SANITIZE_EMAIL);
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $emailValidError = ("$email is not valid!<br>");
    }
    if (strlen($email) > 64) {
        $emailLenghtError = "Email must be less than 64 characters!<br>";
    }
    $date = test_input($_POST["date"]);
    $date = filter_var($date, FILTER_SANITIZE_NUMBER_INT);
    $dateExploded = explode("-", $date);
    foreach ($dateExploded as $val) {
        if (!is_numeric($val)) {
            $dateValidError = ("$date is not valid!<br>");
        }
    }
    $d1 = new DateTime(date("Y-m-d"));
    $d2 = new DateTime($date);

    $diff = $d2->diff($d1);

    if ($diff->y < 18) {
        $dateLimitError = "Must be at least 18 years old!<br>";
    }
    if (checkdate($dateExploded[1], $dateExploded[2], $dateExploded[0]) and $dateExploded[0] > 1920) {
        $date = $dateExploded[0] . "-" . $dateExploded[1] . "-" . $dateExploded[2];
    } else {
        $dateCheckError = "$date is not valid!<br>";
    }
    if (empty($passMatchError) && empty($passError) && empty($passLenghtError) && empty($unameError) && empty($fnameError) && empty($lnameError) && empty($emailValidError) && empty($emailLenghtError) && empty($dateValidError) && empty($countryError) && empty($phoneError) && empty($personalIdError)) {
        include_once "connect.db.php";
        $access = mysqli_connect($server, $user, $password, $database);
        if (!$access) {
            die("Connection To DB FAILED: " . mysqli_connect_error());
        }
        $query2 = sprintf("SELECT C.phone, mail, personal_id,login_user FROM clients C INNER JOIN users U ON C.client_name=U.full_name WHERE personal_id LIKE '%s' OR C.phone LIKE '%s' OR mail LIKE '%s' OR login_user LIKE '%s' ", $personalId, $phone, $email, $uname);
        $result = mysqli_query($access, $query2);
        if (mysqli_num_rows($result) > 0) {
            echo "<script type='text/javascript'>alert('User already exists');</script>";
            header("Location: profile.php");
            die();
        }

//////////////

//$hashed_password = password_hash($user_password, PASSWORD_DEFAULT);
//$query = sprintf("INSERT INTO users (login_user,password_user,role_user,full_name,email,dob) VALUES('%s','%s','user','%s','%s','%s');", $uname, $hashed_password, $fullName, $email, $date);

//////////////


        $query = sprintf("INSERT INTO users (login_user,password_user,role_user,full_name,email,dob) VALUES('%s','%s','user','%s','%s','%s');", $uname, $user_password, $fullName, $email, $date);
        mysqli_query($access, $query);
        $query3 = sprintf("INSERT INTO clients (client_name,dob,mail,phone,personal_id,country) SELECT users.full_name,users.dob,users.email,'%s','%s','%s' FROM users WHERE users.login_user LIKE '%s';", $phone, $personalId, $country, $uname);
        mysqli_query($access, $query3);
        $success = "You are successfully registered.";
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
    <script src="script.js" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Registration</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="styles/forms.css">

</head>

<body>
    <div class="wrapper">
        <div class="topnav">
            <a href="index.php">Home</a>
            <a href="agents.php">Agents</a>
            <div class="login_register">
                <?php if (isset($_SESSION[$ctransient]) and $_SESSION[$ctransient] == "Admin") {
                    $logout = "logout.php";
                    echo "<a href=logout.php onclick=logoutAlert(event)>Logout</a>";
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
                          </form>
                        </div>
                      </li>
                      <li id='signup'>
                        <a href='register.php'>Register</a>
                      </li>
                    </ul>
                  </nav>";
                } ?>
            </div>
        </div>
        <div class="registration-page"></div>
        <div class="regform">
            <form class="registration-form" action="register.php" method="POST">
                <label for="fname">First name:</label><br>
                <input type="text" name="fname" id="fname" placeholder="firstname" pattern="[A-Za-z]{2,}" required>
                <label for="lname">Last name:</label><br>
                <input type="text" name="lname" id="lname" placeholder="lastname" pattern="[A-Za-z.]{2,}" required>
                <label for="email">Email:</label><br>
                <input type="email" name="email" id="email" placeholder="email address" required>
                <label for="date">Date of birth :</label><br>
                <input type="date" name="date" id="date" min="1920-01-02" max="<?php
                                                                                $dateMAX = strtotime(date("Y-m-d") . "-18 year");
                                                                                echo date("Y-m-d", $dateMAX); ?>" required>
                <label for="personalId">Personal ID:</label>
                <input type="text" name="personalId" id="personalId" placeholder="Personal ID" required>
                <label for="phone">Phone number:</label>
                <input type="tel" name="phone" id="phone" placeholder="Phone number" pattern="[+,0-9]{5,12}" required>
                <br>
                <br>
                <label for="country">Country:</label>
                <select id="country" name="country" required>
                    <option value='' selected="selected">Select Country</option>
                    <option value="Estonia">Estonia</option>
                    <option value="Germany">Germany</option>
                    <option value="Finland">Finland</option>
                    <option value="Sweden">Sweden</option>
                </select>
                <br>
                <br>
                <label for="uname">Username:</label><br>
                <input type="text" name="uname" id="uname" placeholder="username" required>
                <label for="password">Password:</label><br>
                <input type="password" name="password" id="password" placeholder="password" required>
                <label for="password2">Repeat password:</label><br>
                <input type="password" name="password2" id="password2" placeholder="repeat password" required><br>
                <button>register</button>
            </form>
            <span id="error"><?php if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                                    echo "$passMatchError $passError $passLenghtError $unameError $fnameError $lnameError $emailValidError $emailLenghtError $dateValidError";
                                } ?></span>
        </div>
    </div>
    <div class="push"></div>
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
