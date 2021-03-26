<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.myclass.entity.Role" %>
<%@ page import="com.myclass.entity.User" %>
<%@ page import="com.myclass.constants.UrlConstant" %>

<%
	List<Role> roles = (List<Role>)request.getAttribute("roles");
	User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Thêm mới thành viên</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href='<%= request.getContextPath() %>/static/css/style.css'>
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
                <h3 class="mb-4">Thêm mới thành viên</h3>
                <form method="post" action="<%= request.getContextPath() %><%= UrlConstant.URL_ROLE_EDIT %>">
                    <div class="row">
                        <div class="col-md-6">
                        	<input type="hidden" name="id" value="<%= user.getId() %>" />
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" name="email" class="form-control" value="<%= user.getEmail() %>" />
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu</label>
                                <input type="password" name="password" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Họ tên</label>
                                <input type="text" name="fullname" class="form-control" value="<%= user.getFullname() %>"  />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Avatar</label>
                                <input type="text" name="avatar" class="form-control" value="<%= user.getAvatar() %>"  />
                            </div>
                            <div class="form-group">
                                <label>Loại người dùng</label>
                                <select class="form-control" name="roleId">
                                <% 
                                	for(Role role : roles) { 
                                		String selected = role.getId() == user.getRoleId() ? "selected" : "";
                                %>
                               
                                	<option value="<%= role.getId() %>" <%= selected %>>
                                		<%= role.getDescription() %>
                                	</option>
                                <% } %>
                                </select>
                            </div>
                        </div>
                        <div class="col-12 mt-3">
                            <button type="submit" class="btn btn-success">Lưu lại</button>
                            <a class="btn btn-secondary" href="<%= request.getContextPath() %>/user">Quay lại</a>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
    <script src="<%= request.getContextPath() %>/static/js/jquery.slim.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js"></script>
</body>

</html>