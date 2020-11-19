<?php
session_start();
$email    = "";

$password = "";

$errors = array(); 
$db = mysqli_connect('localhost:3307', 'root', '', 'foodshala');

if (isset($_POST['login'])) {
  // receive all input values from the form
  $email = mysqli_real_escape_string($db, $_POST['email']);
  $password = mysqli_real_escape_string($db, $_POST['password']);
  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($email)) { array_push($errors, "Email is required"); }
  if (empty($password)) { array_push($errors, "Password is required"); }
  
  // first check the database to make sure 
  // a user does not already exist with the same username and/or email
  $user_check_query = "SELECT * FROM users WHERE email='$email' AND password='$password' LIMIT 1";
  $result = mysqli_query($db, $user_check_query);
  $user = mysqli_fetch_assoc($result);
  $log=0;
  if ($user) { // if user exists
    if ($user['email'] === $email) {

    if ($user['password'] === $password) {
       $_SESSION['email'] = $email;
    $_SESSION['success'] = "You are now logged in";
      $log=1;
      header('location: shop.php');
    }
  }
  }
  if($log==0){
    array_push($errors, "Wrong username/password combination");
   // header('location: userlogin.php');
  }
}

if (isset($_POST['register'])) {
  session_start();

// initializing variables

$email    = "";
$fullname = "";
$phone = "";
$password = "";
$location    = "";
$errors = array(); 

// connect to the database
$db = mysqli_connect('localhost:3307', 'root', '', 'foodshala');

  // receive all input values from the form
  $fullname = mysqli_real_escape_string($db, $_POST['fullname']);
  $email = mysqli_real_escape_string($db, $_POST['email']);
 // $ownname = mysqli_real_escape_string($db, $_POST['ownname']);
  $password = mysqli_real_escape_string($db, $_POST['password']);
    $location = mysqli_real_escape_string($db, $_POST['location']);
  //$city = mysqli_real_escape_string($db, $_POST['city']);
  //$country = mysqli_real_escape_string($db, $_POST['country']);
  $phone = mysqli_real_escape_string($db, $_POST['phone']);

  //$postcode = mysqli_real_escape_string($db, $_POST['postcode']);
  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($fullname)) { array_push($errors, "Name is required");  echo "name required";}
  if (empty($email)) { array_push($errors, "Email is required"); }
  if (empty($password)) { array_push($errors, "Password is required"); }
  
  // first check the database to make sure 
  // a user does not already exist with the same username and/or email
  $user_check_query = "SELECT * FROM users WHERE email='$email' LIMIT 1";
  $result = mysqli_query($db, $user_check_query);
  $user = mysqli_fetch_assoc($result);
  
    if ($user['email'] === $email) {
      array_push($errors, "email already exists");
    }
  

  // Finally, register user if there are no errors in the form
  if (count($errors) == 0) {
    $password = md5($password);//encrypt the password before saving in the database

    $query = "INSERT INTO users (password,fullname,email,phone,location) 
              VALUES('$password','$fullname','$email','$phone','$location')";
    mysqli_query($db, $query);
    $_SESSION['email'] = $email;
    $_SESSION['success'] = "You are now logged in";
    header('location: shop.php');
  }
}

?>