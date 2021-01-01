<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flight_application_form.js"></script>
<main class="app-content">


	<c:if test="${not empty helipadName}">
		<input class="form-control col-md-6"  id="hid" type="hidden" value="${helipadName}"  />
	</c:if>
	<c:if test="${not empty location}">
		<input class="form-control col-md-6"  id="lid" type="hidden" value="${location}"  />
	</c:if>
	<c:if test="${not empty applications}">
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<div class="tile-body">
						<div class="table-responsive">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Full Name</th>
										<th>Mobile Number</th>
										<th>Payment Status</th>
										<th>Application Status</th>
										<th>View Details</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${applications}" var="application" varStatus="loopCounter">
										<tr>
											<td>
												<c:out value="${loopCounter.count}"/>
											</td>
											<td>${application.fullName}</td>
											<td>${application.mobileNumber}</td>
											<td class="text-center" style="color:black;">--</td>
											 <c:if test = "${application.applicationStatus == 'P'}">
                                                     <td class="text-center btn-warning" style="color:white;">Pending</td>
                                             </c:if>
                                              <c:if test = "${application.applicationStatus == 'A'}">
                                              <td class="text-center btn-success" style="color:white;">Approved</td>
                                               </c:if>
                                              <c:if test = "${application.applicationStatus == 'R'}">
                                               <td class="text-center btn-danger" style="color:white;">Rejected</td>
                                                </c:if>




											<td class="text-center btn-primary" style="color:white;" > <a href="${pageContext.request.contextPath}/getUserDetails/${application.userId}" style="color:white; text-decoration:none;"> View Details </a> </td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div></div>
				</div>
			</c:if>
		</main>
		<script type="text/javascript">

  function getBarriersOnLoad(){
  if(document.getElementById('lid') != null && document.getElementById('lid').value  != null ){
          loadhelipads(document.getElementById('lid').value);
          }
      }

        function getHelipadList(data){

                loadhelipads(data);

            }

  $( document ).ready(function() {
 $('#demoDate').datepicker({
        	format: "dd/mm/yyyy",
        	autoclose: true,
        	todayHighlight: true
        });

      getLocations();
      getBarriersOnLoad();

  });
   </script>

<script type="text/javascript">
   $('#sampleTable').DataTable();


   </script>