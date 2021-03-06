<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <head>

   <title>Dashboard</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
  <body class="app sidebar-mini" oncontextmenu="return false">
    <!-- Navbar-->
    <header class="app-header">
    <!-- <a class="app-header__logo" href="index.html">Himachal Police Transport ID Card</a> -->
      <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
      <!-- Navbar Right Menu-->
      <ul class="app-nav">
        <!--Notification Menu-->
        <li style="display:none;" class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Show notifications"><i class="fa fa-bell-o fa-lg"></i></a>
          <ul class="app-notification dropdown-menu dropdown-menu-right">


          </ul>
        </li>
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
          <ul class="dropdown-menu settings-menu dropdown-menu-right">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
          </ul>
        </li>
      </ul>
    </header>
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="${pageContext.request.contextPath}/resources/images/user.png" alt="User Image">
        <div>
          <p class="app-sidebar__user-name">${pageContext.request.userPrincipal.name}</p>
        <!-- <p class="app-sidebar__user-designation">Mobile Number</p> -->
        </div>
      </div>
      <ul class="app-menu">
        <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/index"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Dashboard</span></a></li>
        <sec:authorize access="hasAuthority('Admin')">
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">User Management</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/createUser"><i class="icon fa fa-circle-o"></i> Create User</a></li>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/createRole" ><i class="icon fa fa-circle-o"></i> Create Role</a></li>
          </ul>
        </li>
        </sec:authorize>

         <sec:authorize access="hasAuthority('Admin')">
        <li><a class="app-menu__item" href="${pageContext.request.contextPath}/applications_all"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">View Applications</span></a></li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('Admin')">
        <li><a class="app-menu__item" href="${pageContext.request.contextPath}/applications"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">Filter Applications</span></a></li>
        </sec:authorize>

        <sec:authorize access="hasAuthority('Admin')">
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/checkpayment"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">Check Payment Status</span></a></li>
                </sec:authorize>





      </ul>
    </aside>






  </body>
</html>