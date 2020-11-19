<?php
if (isset($_POST['adddish'])) {
  session_start();

// initializing variables

$dishname    = "";
$category = "";
$price = "";
$image = "";
$errors = array(); 

// connect to the database
$db = mysqli_connect('localhost:3307', 'root', '', 'foodshala');

  // receive all input values from the form
  $dishname = mysqli_real_escape_string($db, $_POST['ndish']);
  $price = mysqli_real_escape_string($db, $_POST['dprice']);
 // $ownname = mysqli_real_escape_string($db, $_POST['ownname']);
  $category = mysqli_real_escape_string($db, $_POST['category']);
    $image = mysqli_real_escape_string($db, $_POST['img']);
  //$city = mysqli_real_escape_string($db, $_POST['city']);
  //$country = mysqli_real_escape_string($db, $_POST['country']);
  //$phone = mysqli_real_escape_string($db, $_POST['phone']);

  //$postcode = mysqli_real_escape_string($db, $_POST['postcode']);
  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  $resid=$_SESSION['resid'];
  $q= "SELECT * FROM restaurants WHERE resid='$resid'";
  $result = mysqli_query($db, $q);
  $user = mysqli_fetch_assoc($result);

  $resname=$user['res_name'];
    $query = "INSERT INTO menu (category,dish_name,res_name,price,image,resid) 
              VALUES('$category','$dishname','$resname','$price','$image','$resid')";
    mysqli_query($db, $query);
   
    header('location: addmenu.php');
}
?>