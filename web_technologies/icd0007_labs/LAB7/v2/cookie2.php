<html lang="en">

<head>
    <meta charset="utf-8" />


</head>

<body>
    <a href="cookie.php">cookie</a>
    <a href="cookie2.php">login</a>
    <a href="cookie3.php">counter and reset</a>
    <?php if (!$_SESSION["valid"] == 1) {
        echo "<form name='pin' action='#' method='POST'>";
        echo "<input type='password' name='pin' id='pin' placeholder='pin' required>";
        echo "<button>login</button>";
    }
    ?>

    </form>

</body>

</html>
<?php

session_start();
ini_set('session.cache_limiter', 'public');
session_cache_limiter(false);

if ($_SESSION["valid"] == 1) {
    echo "Name: " . $_SESSION["name"] . "<br>";
    echo "Age: " . $_SESSION["age"] . "<br>";
    echo "Location: " . $_SESSION["location"] . "<br>";
    echo "<br><form action='#' method='POST'><button name='logout'>Logout</button></form><br>";
    echo "Session counter: ", $_SESSION["counter"], "<br>";
    if (isset($_SESSION["counter"])) {
        ++$_SESSION["counter"];
    } else {
        $_SESSION["counter"] = 1;
    }
} else {
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        if ($_POST["pin"] == "1234") {
            $_SESSION["valid"] = 1;
            $_SESSION["name"] = "Name";
            $_SESSION["age"] = "25";
            $_SESSION["location"] = "Moon";
            echo "Name: " . $_SESSION["name"] . "<br>";
            echo "Age: " . $_SESSION["age"] . "<br>";
            echo "Location: " . $_SESSION["location"] . "<br>";
            if (isset($_SESSION["counter"])) {
                ++$_SESSION["counter"];
            } else {
                $_SESSION["counter"] = 1;
            }
            echo "<br><form action='#' method='POST'><button name='logout'>Logout</button></form><br>";
            echo "Session counter: ", $_SESSION["counter"], "<br>";
        }
    }
}
/* if (isset($_SESSION["counter"])) {
    ++$_SESSION["counter"];
} else {
    $_SESSION["counter"] = 1;
} */
if (isset($_POST["logout"])) {
    session_unset();
    session_destroy();
    header('Location: cookie.php');
    exit;
}


?>