<?php
$salutation = $date = $fname = $lname = $mname = $email  = $comment = $age = $phone = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $salutation = test_input($_POST["salutation"]);
    $salutation = filter_var($salutation, FILTER_SANITIZE_STRING);
    if (($salutation != "Mr") and ($salutation != "Ms") and ($salutation != "other")) {
        echo ("Don't destroy my salutation");
        die();
    }
    $fname = test_input($_POST["fname"]);
    $fname = filter_var($fname, FILTER_SANITIZE_STRING);
    $mname = test_input($_POST["mname"]);
    $mname = filter_var($mname, FILTER_SANITIZE_STRING);
    $lname = test_input($_POST["lname"]);
    $lname = filter_var($lname, FILTER_SANITIZE_STRING);
    $fullName = $salutation . " " . $fname . " " . $mname . " " . $lname;
    if (1 === preg_match('~[0-9]~', $fullName)) {
        echo ("No numbers allowed in names and salutation!");
        die();
    }
    $email = test_input($_POST["email"]);
    $email = filter_var($email, FILTER_SANITIZE_EMAIL);
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo ("$email not valid");
        die();
    }
    $age = test_input($_POST["age"]);
    $age = filter_var($age, FILTER_SANITIZE_NUMBER_INT);
    if (!filter_var($age, FILTER_VALIDATE_INT)) {
        echo ("$age not valid");
        die();
    }
    if ($age < 18) {
        echo ("$age is not valid");
        die();
    }
    $date = test_input($_POST["date"]);
    $date = filter_var($date, FILTER_SANITIZE_NUMBER_INT);
    $dateExploded = explode("-", $date);
    foreach ($dateExploded as $val) {
        if (!is_numeric($val)) {
            echo ("$date is not invalid");
            die();
        }
    }
    if (checkdate($dateExploded[1], $dateExploded[2], $dateExploded[0]) and $dateExploded[0] >= date("Y") and $dateExploded[2] >= date("d") and $dateExploded[1] >= date("m")) {
        $date = $dateExploded[0] . "-" . $dateExploded[1] . "-" . $dateExploded[2];
    } else {
        echo ("$date is not valid");
        die();
    }
    $comment = test_input($_POST["comment"]);
    $comment = filter_var($comment, FILTER_SANITIZE_STRING);
    $phone = test_input($_POST["phone"]);
    $fp = "data/data.csv";
    $cvsData = $salutation . "\t" . $fname . "\t" . $mname . "\t" . $lname . "\t" . $age . "\t" . $email . "\t" . $phone . "\t" . $date . "\t" . $comment . "\n";
    file_put_contents($fp, $cvsData, FILE_APPEND | LOCK_EX);
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

<html lang="en">

<head>
    <title>Registration</title>
    <link rel="stylesheet" href="main.css">
</head>

<body>

    <h1><?php echo ("Registrated successful " . "$salutation "  . "$fname " . "$mname "  . "$lname"); ?></h1>
    <ul><?php echo ("First Name: " . $fname . "<br/>\n");
        echo ("Middle Name: " . $mname . "<br/>\n");
        echo ("Last Name: " . $lname . "<br/>\n");
        echo ("Age: " . $age . "<br/>\n");
        echo ("E-mail: " . $email . "<br/>\n");
        echo ("Phone: " . $phone . "<br/>\n");
        echo ("Trip-Start: " . $date . "<br/>\n");
        echo ("Comment: " . $comment . "<br/>\n"); ?></ul>
    <form action="download.php" method="post">
        <input type="submit" name="submit" value="Download File" />
    </form>
    <p2><?php $fp = file("data/data.csv", FILE_SKIP_EMPTY_LINES);
        echo ("Registered number: " . count($fp)); ?></p2>

</body>

</html>