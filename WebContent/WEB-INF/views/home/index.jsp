<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
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
                <div class="row">
                    <div class="col-md-6">
                        <canvas id="myChart" width="400" height="400"></canvas>
                    </div>
                    <div class="col-md-6">
                        <canvas id="myChart2" width="400" height="400"></canvas>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </section>
        </div>
    </div>
    <script src="<%= request.getContextPath() %>/static/js/jquery.slim.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js"></script>
    <script src='<%= request.getContextPath() %>/static/js/chart.min.js'></script>
    <script src='<%= request.getContextPath() %>/static/js/custom-chart.js'></script>
</body>

</html>