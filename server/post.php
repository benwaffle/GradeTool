<?php
if (!isset($_POST) || !isset($_POST["notif_due"]) || !isset($_POST["notif_phone"]) || !isset($_POST["notif_body"]))
	exit("Nothing sent. Improper or no POST data provided.");

http_response_code(200);

$notification = array(
	"DueDate" => $_POST["notif_due"],
	"PhoneNumber" => $_POST["notif_phone"],
	"Body" => $_POST["notif_body"]
);

$con = mysql_connect("localhost", "gradetool");
mysql_select_db("gradetool_queue", $con);
mysql_query("INSERT INTO notifications (DueDate, PhoneNumber, Body) VALUES('" . $notification["DueDate"] . "','" . $notification["PhoneNumber"] . "', '" . $notification["Body"] . "')");
mysql_close($con);

echo "Saved message into database.";
?>
