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
  $user_check_query = "SELECT * FROM restaurants WHERE email='$email' AND password='$password' LIMIT 1";
  $result = mysqli_query($db, $user_check_query);
  $user = mysqli_fetch_assoc($result);
  $log=0;
  if ($user) { // if user exists
    if ($user['email'] === $email) {

    if ($user['password'] === $password) {
       $_SESSION['resid'] = $user['userid'];
    $_SESSION['success'] = "You are now logged in";
      $log=1;
      header('location: addmenu.php');
    }
  }
  }
  if($log==0){
    array_push($errors, "Wrong username/password combination");}
}
?>