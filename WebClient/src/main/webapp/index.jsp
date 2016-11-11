<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index Page</title>
</head>
<body>
	<h1>Book Details</h1>

	<form action="submit.jsp">
		<table>
			<tr>
				<td>Book ID</td>
				<td><input name="id"></td>
			</tr>

			<tr>
				<td>Book Name</td>
				<td><input name="name"></td>
			</tr>
			<tr>
				<td>select operation</td>
				<td>
					<table>
						<tr>
							<td><input type="radio" name="choice" value="add" /></td>
							<td>Add</td>
						</tr>

						<tr>
							<td><input type="radio" name="choice" value="find" /></td>
							<td>Find</td>
						</tr>

						<tr>
							<td><input type="radio" name="choice" value="update" /></td>
							<td>Update</td>
						</tr>

						<tr>
							<td><input type="radio" name="choice" value="delete" /></td>
							<td>Delete</td>
						</tr>

						<tr>
							<td><input type="radio" name="choice" value="find all" /></td>
							<td>FindAll</td>
						</tr>


					</table>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="OK"></td>
			</tr>

		</table>
	</form>

</body>
</html>
