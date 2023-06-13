<html lang="en">

<head>
    <meta charset="utf-8" />


</head>

<body>
    <a href="cookie.php">cookie</a>
    <a href="cookie2.php">login</a>
    <a href="cookie3.php">counter and reset</a><br>
</body>

</html>
<?php

session_start();

ini_set('session.cache_limiter', 'public');
session_cache_limiter(false);


if (isset($_POST["reset"])) {
    $_SESSION["counter"] = 0;
}
if (isset($_SESSION["valid"])) {
    echo "Counter: " . $_SESSION["counter"];
    echo "<br><form action='#' method='POST'><button name='reset'>Reset</button></form><br>";
} else {
    echo "Login first to see the counter";
}


?>