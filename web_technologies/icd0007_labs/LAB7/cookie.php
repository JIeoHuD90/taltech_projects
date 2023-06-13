<?php
session_start();

$sessionName = "ctransient";
$longCookie = "LongTimeCount";
$shortCookie = "ShortTimeCount";
session_name($sessionName);
session_start();
$_SESSION[$sessionName] = "Leonid Vagulin";

if (isset($_COOKIE[$shortCookie])) {
    $cookieContentShort = $_COOKIE[$shortCookie] + 1;
} else $cookieContentShort = 1;
if (isset($_COOKIE[$longCookie])) {
    $cookieContentLong = $_COOKIE[$longCookie] + 1;
} else $cookieContentLong = 1;

setcookie($shortCookie, $cookieContentShort, time() + 120);
setcookie($longCookie, $cookieContentLong, time() + 3600);

echo "Transient cookie = " . $_SESSION[$sessionName] . "<br>";
echo $shortCookie . " = " . $cookieContentShort . "<br>";
echo $longCookie . " = " . $cookieContentLong . "<br>";
?>
<html lang="en">

<head>
    <meta charset="utf-8" />


</head>

<body>
    <a href="cookie.php">cookie</a>
    <a href="cookie2.php">login</a>
    <a href="cookie3.php">counter and reset</a>
</body>

</html>