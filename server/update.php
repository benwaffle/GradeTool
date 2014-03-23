<?php
// update.php

$AccountSid = "AC81e14140aa197a30d748a50cbeba06f7";
$AuthToken = "28e3ef82f611622bce64b4ebd885fa32";

$con = mysql_connect("localhost", "gradetool");
mysql_select_db("gradetool_queue", $con);

$client = new Services_Twilio($AccountSid, $AuthToken);
$results = mysql_query("SELECT * FROM notifications ORDER BY DueDate DESC");
while ($row = mysql_fetch_assoc($results))
	if (time() - strtotime($row["DueDate"]) <= 0) {
		$sms = $client->account->messages->sendMessage("+19145956189",
			$row["PhoneNumber"], $row["Body"]);
		echo "Sent message {$sms->sid}"; // confirmation message
	}

mysql_close($con);
?>
