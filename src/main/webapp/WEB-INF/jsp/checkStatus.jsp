<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flight_application_form.js"></script>
<main class="app-content" style="min-height:350px;">
   <form:form method="POST" class="form-horizontal" id="form" modelAttribute="checkStatus" action="${pageContext.request.contextPath}/checkStatusApp">
      <div class="container" style="padding:10px; margin-top:20px; margin:bottom:20px;">
         <h4 class="text-center">Check Application Status</h4>
         <ol class="breadcrumb">
            <li> &nbsp; </li>
         </ol>
         <c:if test="${not empty successMessage}">
            <div id="serverError" class="successMessage">${successMessage}</div>
         </c:if>
         <br>
         <c:if test="${not empty serverError}">
            <div id="serverError" class="plErroMessage">${serverError}</div>
         </c:if>

         <div class="form-inline">
         <spring:bind path="applicationId">
            <label class="col-lg-3 col-md-3 col-xs-12 col-sm-12" for="id1"><strong>Check Application Status</strong></label>
            <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 ${status.error ? 'has-error' : ''}">
               <form:input class="form-control " path="applicationId" name="applicationId" type="text" id="applicationId" />
               <form:errors  path="applicationId"></form:errors>
            </div>
            </spring:bind>
            <input type="submit"  value="Submit" text="Submit" class="btn btn-success pull-left col-lg-3 col-md-3 col-xs-12 col-sm-12">
             <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            <c:remove var="successMessage" scope="session" />
         </div>

      </div>
   </form:form>
   </div>
   <c:if test="${not empty applicationData}">
     <div class="container">
      <div class="row">
      <div class="col-lg-1">&nbsp; </div>
      <div class="col-lg-10">
      <hr>
      <div class="tile">
         <div class="tile-body">
            <div class="table-responsive">

               <table class="table table-hover table-bordered" id="sampleTable">
                  <thead>
                     <tr>
                        <th class="text-center">Application Status</th>
                        <th class="text-center">Comments</th>
                        <th class="text-center">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${applicationData}" var="application" varStatus="loopCounter">
                        <tr>
                           <c:if test = "${application.applicationStatus == 'P'}">
                              <td class="text-center btn-warning" style="color:white;">Pending</td>
                           </c:if>
                           <c:if test = "${application.applicationStatus == 'A'}">
                              <td class="text-center btn-success" style="color:white;">Approved</td>
                           </c:if>
                           <c:if test = "${application.applicationStatus == 'R'}">
                              <td class="text-center btn-danger" style="color:white;">Rejected</td>
                           </c:if>
                            <td class="text-center" style="color:#000000;"><strong>${application.comments}</strong></td>
                             <c:if test = "${application.applicationStatus == 'A'}">
                           <td class="text-center btn-primary" style="color:white;" > <a href="${pageContext.request.contextPath}/generatePdf/${application.userId}" target= "_blank" style="color:white; text-decoration:none;"> Download </a> </td>
                           </c:if>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div>
         <div class="col-lg-1">&nbsp; </div>
         </div>
      </div>
      </div>
   </c:if>
</main>