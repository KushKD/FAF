<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>






<main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-dashboard"></i> Dashboard</h1>
          <p>Lahaul and Spiti Flight Service</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-6 col-lg-3">
          <div class="widget-small primary coloured-icon"><i class="icon fa fa-id-badge fa-3x"></i>
            <div class="info">
            <a href="${pageContext.request.contextPath}/applications_all" style=" text-decoration: none !important;">
                          <h4>View Applications</h4></a>

            </div>
          </div>
        </div>

         <div class="col-md-6 col-lg-3">
                  <div class="widget-small primary coloured-icon"><i class="icon fa fa-id-badge fa-3x"></i>
                    <div class="info">
                    <a href="${pageContext.request.contextPath}/applications" style=" text-decoration: none !important;">
                                  <h4>Filter Applications</h4></a>

                    </div>
                  </div>
                </div>

         <sec:authorize access="hasAuthority('Admin')">
           <div class="col-md-6 col-lg-3">
                  <div class="widget-small warning coloured-icon"><i class="icon fa fa-user fa-3x"></i>
                    <div class="info">
                     <a href="${pageContext.request.contextPath}/createUser" style=" text-decoration: none !important;">
                                   <h4>Create User</h4></a>

                    </div>
                  </div>
                </div>
                </sec:authorize>
 <sec:authorize access="hasAuthority('Admin')">
   <div class="col-md-6 col-lg-3">
          <div class="widget-small danger coloured-icon"><i class="icon fa fa-file-excel-o fa-3x"></i>
            <div class="info">
             <a href="${pageContext.request.contextPath}/generateReport" style=" text-decoration: none !important;">
                           <h4>Generate Reports</h4></a>

            </div>
          </div>
        </div>
 </sec:authorize>




      </div>



      </div>
    </main>