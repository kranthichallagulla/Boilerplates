<!DOCTYPE html>
<html lang="en">

<head>
<title>Keep-Board</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add
		 button. Handle errors like empty fields -->
		 <form action="/add" method="post">

         		Note Title:<br> <input type="text" name="Title"> <br>
         		Note Content:<br> <input type="text" name="Content"> <br>
         		Note Status:<br> <input type="text" name="Status"> <br> <br>
         		<input type="submit" value="Submit">
         	</form>


	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
</body>

</html>