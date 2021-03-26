<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập hệ thống</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="bg-light">
	<div class="container">
		<div class="row mt-5">
			<div class="col-5 m-auto">
				<h3 class="mb-4">ĐĂNG NHẬP HỆ THỐNG</h3>
				<p>${ message }</p>
				<form action="<%= request.getContextPath() %>/login" method="POST">
				  <div class="form-group">
				    <label>Email</label>
				    <input type="email" class="form-control" name="email">
				  </div>
				  <div class="form-group">
				    <label>Password</label>
				    <input type="password" class="form-control" name="password">
				  </div>
				  <button type="submit" class="btn btn-primary">Đăng nhập</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>