<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.myclass.dto.UserDto" %>
    
<%
	String contextPath = request.getContextPath();
	List<UserDto> users = (List<UserDto>)request.getAttribute("users");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Danh sách thành viên</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%= contextPath %>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href='<%= contextPath %>/static/css/style.css'>
</head>

<body>

    <div class="d-flex justify-content-between">
        <!-- SIDE BAR -->
        <jsp:include page="../layout/sidebar.jsp"></jsp:include>

        <div id="admin-wrapper">
            <!-- HEADER -->
            <jsp:include page="../layout/navbar.jsp"></jsp:include>

            <!-- CONTENT -->
            <section id="admin-content" class="p-3">
                <h3 class="mb-3">Danh sách thành viên</h3>
                <div class="row">
                    <div class="col-md-8">
                        <a href="<%= contextPath %>/user/add" class="btn btn-primary">Thêm mới</a>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Tìm kiếm...">
                            <div class="input-group-append">
                                <span class="input-group-text" id="basic-addon2"><i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Họ Tên</th>
                            <th>Email</th>
                            <th>Loại người dùng</th>
                            <th>#</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    	<% for(UserDto user : users) {%>
                        <tr>
                            <td><%= user.getId() %></td>
                            <td><%= user.getFullname() %></td>
                            <td><%= user.getEmail() %></td>
                            <td><%= user.getRoleDec() %></td>
                            <td>
                                <a href="<%= contextPath %>/user/edit?id=<%= user.getId() %>" class="btn btn-sm btn-info">
                                    <i class="fa fa-pencil-square-o"></i>
                                </a>
                                <a href="#" class="btn btn-sm btn-danger">
                                    <i class="fa fa-trash-o"></i>
                                </a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </section>
        </div>
    </div>
    <script src="<%= contextPath %>/static/js/jquery.slim.min.js"></script>
    <script src="<%= contextPath %>/static/js/popper.min.js"></script>
    <script src="<%= contextPath %>/static/js/bootstrap.min.js"></script>
</body>

</html>