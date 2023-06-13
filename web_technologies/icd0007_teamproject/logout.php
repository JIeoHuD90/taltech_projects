<?php
$LogoutMessage = "Logged out successfully";
echo "<script type='text/javascript'>alert('$LogoutMessage');</script>";
?>

<?php
session_start();
session_unset();
session_destroy();
header('Location: index.php');
exit;
?>
