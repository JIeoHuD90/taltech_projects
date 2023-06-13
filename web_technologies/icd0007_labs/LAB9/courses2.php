<!DOCTYPE html>
<html lang="en">

<head>

</head>

<body>
    <a href="courses2.php?semester=1">autumn</a>
    <a href="courses2.php?semester=2">spring</a>
    <a href="courses2.php?semester=3">autumn-spring</a>
    <form action="courses2.php" method="POST">
        <label for="fname">Search :</label><br>
        <input type="text" name="search" id="search" placeholder="Enter course code or name" required><br>
        <p>Search option:</p>
        <input type="radio" id="id" name="IDorNAME" value="id">
        <label for="id">ID</label><br>
        <input type="radio" id="name" name="IDorNAME" value="name">
        <label for="name">NAME</label><br>
        <p>Sort option:</p>
        <input type="radio" id="asc" name="sort" value="asc">
        <label for="asc">ASC</label><br>
        <input type="radio" id="desc" name="sort" value="desc">
        <label for="desc">DESC</label><br>
        <button>Search</button>
    </form>


</body>

</html>
<?php
include_once "connect.db.php";
$access = mysqli_connect($server, $user, $password, $database);
if (!$access) {
    die("Connection To DB FAILED: " . mysqli_connect_error());
}
$sem = test_input($_GET['semester']);
$sem = filter_var($sem, FILTER_SANITIZE_NUMBER_INT);
$sort = test_input($_GET['sort']);
$search = test_input($_POST['search']);
$search = "%" . $search . "%";
$search = "'" . $search . "'";

if ($sort == 'idDESC') {
    $sortOption = ' ORDER BY course_code DESC';
}
if ($sort == 'ectsDESC') {
    $sortOption = ' ORDER BY ects_credits DESC';
}
if ($sort == 'ectsASC') {
    $sortOption = ' ORDER BY ects_credits ASC';
}
if ($sort == 'semesterDESC') {
    $sortOption = 'ORDER BY semester_name DESC';
}
if ($sort == 'semesterASC') {
    $sortOption = ' ORDER BY semester_name ASC';
}
if ($sort == 'nameASC') {
    $sortOption = ' ORDER BY course_name ASC';
}
if ($sort == 'nameDESC') {
    $sortOption = ' ORDER BY course_name DESC';
}
if (empty($_POST['search']) or !empty($sem)) {

    if (!empty($sem) and ($sort == 'idASC' or $sort == 'ectsASC' or $sort == 'semesterASC' or $sort == 'nameASC')) {
        echo "<pre>
            <table id='table1'>
             <tr>";
        echo  "<th><a href='courses2.php?semester=" . $sem . "&sort=idDESC'>Course Code ID</a></th>
          <th><a href='courses2.php?semester=" . $sem . "&sort=ectsDESC'>ECTS Credits</a></th>
         <th><a href='courses2.php?semester=" . $sem . "&sort=semesterDESC'>Semester</a></th>
         <th><a href='courses2.php?semester=" . $sem . "&sort=nameDESC'>Course Name</a></th></tr>";
    } elseif (!empty($sem) and ($sort == 'idDESC' or $sort == 'ectsDESC' or $sort == 'semesterDESC' or $sort == 'nameDESC')) {
        echo "<pre>
            <table id='table1'>
             <tr>";
        echo  "<th><a href='courses2.php?semester=" . $sem . "&sort=idASC'>Course Code ID</a></th>
          <th><a href='courses2.php?semester=" . $sem . "&sort=ectsASC'>ECTS Credits</a></th>
         <th><a href='courses2.php?semester=" . $sem . "&sort=semesterASC'>Semester</a></th>
         <th><a href='courses2.php?semester=" . $sem . "&sort=nameASC'>Course Name</a></th></tr>";
    } elseif (!empty($sem)) {
        echo "<pre>
            <table id='table1'>
             <tr>";
        echo  "<th><a href='courses2.php?semester=" . $sem . "&sort=idDESC'>Course Code ID</a></th>
          <th><a href='courses2.php?semester=" . $sem . "&sort=ectsDESC'>ECTS Credits</a></th>
         <th><a href='courses2.php?semester=" . $sem . "&sort=semesterDESC'>Semester</a></th>
         <th><a href='courses2.php?semester=" . $sem . "&sort=nameDESC'>Course Name</a></th></tr>";
    }
}
if (!empty($_GET['semester']) and empty($_POST['search'])) {

    if (!$sem = filter_var($sem, FILTER_VALIDATE_INT)) {
        die('Please, dont drop my table(INVALID INPUT)');
    }
    if ($sem == 1 or $sem == 2 or $sem == 3) {
        listCourses($access, $sem, $sortOption);
    } else {
        die('Please, dont drop my table(INVALID INPUT)');
    }
}
if (!empty($search) and isset($_POST['search'])) {
    echo "<pre>
            <table id='table1'>
             <tr>";
    echo  "<th>Course Code ID</th>
          <th>ECTS Credits</th>
         <th>Semester</th>
         <th>Course Name</th></tr>";
    if ($_POST['IDorNAME'] == 'id') {
        $sortOption = "";
    } else {
        $sortOption = " ORDER BY course_code DESC";
    }
    if ($_POST['sort' == 'asc']) {
        $sortOption = ' ORDER BY course_name ASC';
    } else {
        $sortOption = ' ORDER BY course_name DESC';
    }
    $searchOP = sprintf("course_name LIKE %s OR course_code LIKE %s%s", $search, $search, $sortOption);
    listCourses($access, 0, $searchOP);
}

function listCourses($link, $semester, $sortOP)
{
    if ($semester > 0) {
        $query = sprintf("SELECT course_code,ects_credits,course_name, semester_name FROM courses_210731 C INNER JOIN  semesters_210731 S ON C.Semesters_ID=S.ID WHERE Semesters_ID = %s %s;", $semester, $sortOP);
    } else {
        $query = sprintf("SELECT course_code,ects_credits,course_name, semester_name FROM courses_210731 C INNER JOIN  semesters_210731 S ON C.Semesters_ID=S.ID WHERE  %s;",  $sortOP);
    }
    $result = mysqli_query($link, $query);
    if (mysqli_num_rows($result) > 0) {

        while ($row = mysqli_fetch_assoc($result)) {
            echo "<tr>
                     <td>" . $row['course_code'] . "</td>
                     <td>" . $row['ects_credits'] . "</td>
                     <td>" . $row['semester_name'] . "</td>
                     <td>" . $row['course_name'] . "</td>
                     </tr>";
        }
        echo "</pre>";
    } else {
        echo "Nothing found";
    }
    mysqli_close($link);
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
