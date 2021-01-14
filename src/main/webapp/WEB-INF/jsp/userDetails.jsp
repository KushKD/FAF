<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flight_application_form.js"></script>
<main class="app-content">
   <div class="row user">
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage col-lg-12 ">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage col-lg-12">${serverError}</div>
      </c:if>
      <div class="col-md-12">
         <div class="tab-content">
            <div id="user-timeline">
               <div class="timeline-post">
                  <h2> Applicant Details</h2>
                  <hr>
                  <div class="row">
                     <div class="col-lg-4">
                        <label> Full Name: </label>
                        <label>${userdata.fullName}</label>
                     </div>
                     <div class="col-lg-4">
                        <label> Mobile Number: </label>
                        <label>${userdata.mobileNumber}</label>
                     </div>
                     <div class="col-lg-4">
                        <label> Age: </label>
                        <label>${userdata.age}</label>
                     </div>
                  </div>
                  <div class="row">
                     <div class="clearfix"></div>
                     <div class="col-md-4">
                        <label> Weight: </label>
                        <label>${userdata.weight}</label>
                     </div>
                     <div class="clearfix"></div>
                     <div class="col-md-4">
                        <label> Luggage Weight: </label>
                        <label>${userdata.luggageWeight} (KG)</label>
                     </div>
                     <div class="clearfix"></div>
                     <div class="col-md-4">
                        <label>${userdata.relationPrifix.relationshipPrifixName}</label> &nbsp; &nbsp;
                        <label>${userdata.relationName}</label>
                     </div>
                  </div>
                  <div class="row">
                     <div class="clearfix"></div>
                     <div class="col-md-4">
                        <label> Type of Reservation: </label>
                        <label>${userdata.registrationType.reservationTypeName}</label>
                     </div>
                     <div class="clearfix"></div>
                     <div class="col-md-4">
                        <label> User Type: </label>
                        <label>${userdata.category.userTypeName}</label>
                     </div>
                     <div class="clearfix"></div>
                     <div class="col-md-4">
                        <label>Permanent Address: </label>
                        <label>${userdata.permanentAddress}</label>
                     </div>
                  </div>
                  <div class="clearfix"></div>
                  <div class="col-md-4">
                     <label>Correspondence Address: </label>
                     <label>${userdata.correspondenceAddress}</label>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   </div>
   <!-- Section Two -->
   <div class="row user">
      <div class="col-md-12">
         <div class="tab-content">
            <div id="user-timeline">
               <div class="timeline-post">
                  <h2>Flight Booking Details</h2>
                  <hr>
                  <div class="row">
                     <div class="col-lg-4">
                        <label> Reason for availing Flight Service </label>
                        <label>${userdata.reasonAvailingFlightService.reasonAvailingFlightName}</label>
                     </div>
                     <div class="col-lg-4">
                        <label> Tentative Date for availing flight Service:  </label>
                        <label>${userdata.tentitiveFlightDate}</label>
                     </div>
                     <div class="col-lg-4">
                        <label> Boarding Location </label>
                        <label>${userdata.flightDistrictToGoFrom.districtName}</label>
                     </div>
                  </div>
                  <div class="row">
                     <div class="clearfix"></div>
                     <div class="col-md-4">
                        <label> Boarding Helipad: </label>
                        <label>${userdata.flightHelipadNameToGoFrom.helipadName}</label>
                     </div>
                     <div class="clearfix"></div>
                     <div class="col-md-12">
                        <label> Has the applicant availed the flight service after 15th Nov : </label>
                        <label>${userdata.availedFlightBefore15}</label>
                     </div>
                     <div class="col-md-12">
                        <label> Has the earlier flight service been availed in Emergency Situation : </label>
                        <label>${userdata.earlierFlightServiceEmergency}</label>
                     </div>
                  </div>

                  <div class="row">
                   <div class="clearfix"></div>
                    <div class="col-lg-4">
                                           <label> Destination Location </label>
                                           <label>${userdata.district_id_dest.districtName}</label>
                                        </div>
                                       <div class="col-md-4">
                                          <label> Destination Helipad: </label>
                                          <label>${userdata.helipad_id_dest.helipadName}</label>
                                       </div>

                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   </div>
   <!-- Section Two Ends -->
   <!-- Section Three -->
   <div class="row user">
      <div class="col-md-12">
         <div class="tab-content">
            <div id="user-timeline">
               <div class="timeline-post">
                  <h2>Attached Documents</h2>
                  <hr>
                  <div class="row">
                   <div class="col-lg-3"> &nbsp;  </div>
                     <div class="table-responsive">
                        <table class="table table-hover table-bordered" id="sampleTable">
                           <thead>
                              <tr>
                                 <th>Attachment</th>
                                 <th>Download Attachment</th>
                              </tr>
                           </thead>
                           <tbody>
                              <c:if test="${not empty userdata.aadhaar_doc}">
                                 <tr>

                                    <td>Aadhaar Card</td>
                                    <td class="text-center btn-success" style="color:white;" > <a href="${pageContext.request.contextPath}/downloadFile/${userdata.aadhaar_doc}" style="color:white; text-decoration:none;"> View Details </a> </td>
                                 </tr>
                              </c:if>
                              <c:if test="${not empty userdata.officeCardDoc}">
                                 <tr>

                                    <td>Official Document</td>
                                    <td class="text-center btn-success" style="color:white;" > <a href="${pageContext.request.contextPath}/downloadFile/${userdata.officeCardDoc}" style="color:white; text-decoration:none;"> View Details </a> </td>
                                 </tr>
                              </c:if>
                              <c:if test="${not empty userdata.medicalDoc}">
                                 <tr>

                                    <td>Medical Document</td>
                                    <td class="text-center btn-success" style="color:white;" > <a href="${pageContext.request.contextPath}/downloadFile/${userdata.medicalDoc}" style="color:white; text-decoration:none;"> View Details </a> </td>
                                 </tr>
                              </c:if>
                              <c:if test="${not empty userdata.otherDoc}">
                                 <tr>
                                    <td>
                                       <c:out value="${loopCounter.count}"/>
                                    </td>
                                    <td>Other Document</td>
                                    <td class="text-center btn-success" style="color:white;" > <a href="${pageContext.request.contextPath}/downloadFile/${userdata.otherDoc}" style="color:white; text-decoration:none;"> View Details </a> </td>
                                 </tr>
                              </c:if>
                           </tbody>
                        </table>
                     </div>
                     <div class="col-lg-3"> &nbsp;  </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   </div>
   <!-- Section Three Ends -->


    <!-- Section Four -->
    <c:if test="${not empty userdata.userFormDataPreviousServiceEntities}">
      <div class="row user">
         <div class="col-md-12">
            <div class="tab-content">
               <div id="user-timeline">
                  <div class="timeline-post">
                     <h2>Other EarlierServices Availed</h2>
                     <hr>


                     <div class="row">
                     			<div class="col-md-12">
                     				<div class="tile">
                     					<div class="tile-body">
                     						<div class="table-responsive">
                     							<table class="table table-hover table-bordered" id="sampleTable">
                     								<thead>
                     									<tr>
                     										<th>S.No</th>
                     										<th>Date</th>
                     										<th>Location</th>
                     										<th>Helipad Name</th>


                     									</tr>
                     								</thead>
                     								<tbody>
                     									<c:forEach items="${userdata.userFormDataPreviousServiceEntities}" var="application" varStatus="loopCounter">
                     										<tr>
                     											<td>
                     												<c:out value="${loopCounter.count}"/>
                     											</td>
                     											<td>${application.date}</td>
                     											<td>${application.districtId.districtName}</td>
                     											<td>${application.helipadId.helipadName}</td>


                     										</tr>
                     									</c:forEach>
                     								</tbody>
                     							</table>
                     						</div>
                     					</div>
                     					<div></div>
                     				</div>





                  </div>
               </div>
            </div>
         </div>
      </div>
      </div>
      </c:if>
      </div>
      <!-- Section Four Ends -->







      <!-- Section Payment transactionUser -->
       <c:if test="${not empty transactionUser}">
       <div class="row user">
            <div class="col-md-12">
               <div class="tab-content">
                  <div id="user-timeline">
                     <div class="timeline-post">
                        <h2>Transaction Details</h2>
                        <hr>
                        <div class="row">
                           <div class="col-lg-4">
                              <label> Transaction Status </label>
                              <label>${transactionUser.paymentStatus}</label>
                           </div>
                           <div class="col-lg-4">
                              <label> Transaction ID  </label>
                              <label>${transactionUser.transactionId}</label>
                           </div>
                           <div class="col-lg-4">
                              <label> Bank Reference Number: </label>
                              <label>${transactionUser.bankRefNumber}</label>
                           </div>
                        </div>
                        <div class="row">
                           <div class="clearfix"></div>
                           <div class="col-md-4">
                              <label> Phone Number: </label>
                              <label>${transactionUser.phone}</label>
                           </div>
                           <div class="clearfix"></div>
                           <div class="col-md-12">
                              <label> Email </label>
                              <label>${transactionUser.email}</label>
                           </div>

                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         </div>
         </c:if>
       <!-- Section Payment Ends -->








      <!-- Section Five -->
      <c:if test="${userdata.applicaionStatus == 'P'}">
         <div class="row user">
            <div class="col-md-12">
               <div class="tab-content">
                  <div id="user-timeline">
                     <div class="timeline-post">
                        <h2>Approve/Reject Application</h2>
                        <hr>

                        <div class="row">
                        <div class="col-lg-12">
<form:form method="POST"  modelAttribute="actionForm" action="${pageContext.request.contextPath}/updateApplication" class="form-signin">
        <spring:bind path="action">
                <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                   <form:label  path="action" > Please Select any Action </form:label>
                   <form:select  path="action" class="form-control" id="earlierService">
                         <form:option value=""> --Select-- </form:option>
                         <form:option value="A"> Approve </form:option>
                         <form:option value="R"> Reject </form:option>
                   </form:select>
                   <form:errors  style="color:red;" path="action"></form:errors>
                </div>
                 </spring:bind>

      <spring:bind path="comments">
              <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                 <label for="comments"> <spring:message code="form.addressss"  text="Comments" /> </label>
                 <form:textarea rows="4" path="comments" id="comments" class="form-control" onkeypress="return alpha(event)"  oncopy="return false" onpaste="return false" />
                  <form:errors style="color:red;"  path="comments"></form:errors>
              </div>
               </spring:bind>

      <spring:bind path="user_id">
                    <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                       <form:input type="hidden" path="user_id" id="user_id" class="form-control"  value="${userdata.userId}" />
                        <form:errors style="color:red;"  path="user_id"></form:errors>
                    </div>
                     </spring:bind>

 <input type="submit"  value="<spring:message code="customhiring.submit" text="Submit"/>" class="btn btn-success">
          <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
</form:form>
</div>
                        </div>


                     </div>
                  </div>
               </div>
            </div>
         </div>
         </div>
         </c:if>
         <!-- Section Five Ends -->
</main>