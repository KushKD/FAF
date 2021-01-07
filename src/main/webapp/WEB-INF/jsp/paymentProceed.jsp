<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/internationalization.js"></script>
<section>
   <body>
      <form:form method="POST"  modelAttribute="paymentForm" action="${pageContext.request.contextPath}/paymentpagepost"  class="form-signin">
         <c:if test="${not empty successMessage}">
            <div class="row">
               <div id="serverError" style="color:#77332F; display: block ;" class="text-center successMessage col-lg-12 breadcrumb">${successMessage}</div>
            </div>
         </c:if>
         <br>
         <c:if test="${not empty serverError}">
            <div class="row">
               <div id="serverError" style="color:#77332F; display: block ;" class="plErroMessage text-center  col-lg-12 breadcrumb">${serverError}</div>
            </div>
         </c:if>
         <!-- Section Three -->
         <div class="row ">
            <div class="col-lg-3"> &nbsp;  </div>
            <div class="table-responsive col-lg-6 breadcrumb">
               <h4 class="text-left col-lg-12">Verify the Details before proceeding :</h4>
               <hr>
               <table class="table table-hover table-bordered">
                  <tbody>
                     <tr>
                        <td class="text-center">Registration No.</td>
                        <td class="text-center" >
                           <spring:bind path="user_id_transaction_id">
                              <div class="form-group  ${status.error ? 'has-error' : ''}">
                                 <form:input type="text" path="user_id_transaction_id"   class="form-control text-center" value="${user.userId}" readonly="true"  name="user_id_transaction_id" ></form:input>
                                 <form:errors  path="user_id_transaction_id"></form:errors>
                              </div>
                           </spring:bind>
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">User Full Name:</td>
                        <td class="text-center" >
                           <spring:bind path="name">
                              <div class="form-group  ${status.error ? 'has-error' : ''}">
                                 <form:input type="text" path="name"   class="form-control text-center" value="${user.fullName}" name="name" readonly="true" ></form:input>
                                 <form:errors  path="name"></form:errors>
                              </div>
                           </spring:bind>
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">Age</td>
                        <td class="text-center" > ${user.age}  </td>
                     </tr>
                     <tr>
                        <td class="text-center">Luggage Weight</td>
                        <td class="text-center"  > ${user.luggageWeight} (KG) </td>
                     </tr>
                     <tr class="text-center">
                        <td>Location to go From</td>
                        <td class="text-center"> ${user.flightDistrictToGoFrom.districtName}  </td>
                     </tr>
                     <tr class="text-center">
                        <td>Helipad to go From</td>
                        <td class="text-center"  > ${user.flightHelipadNameToGoFrom.helipadName}  </td>
                     </tr>
                     <tr>
                        <td class="text-center">Travel Date</td>
                        <td class="text-center" > ${user.tentitiveFlightDate}  </td>
                     </tr>
                     <tr>
                        <td class="text-center">Type of User</td>
                        <td class="text-center" > ${user.category.userTypeName}  </td>
                     </tr>
                     <tr>
                        <td class="text-center">Mobile Number</td>
                        <td class="text-center" >
                           <spring:bind path="phone">
                              <div class="form-group  ${status.error ? 'has-error' : ''}">
                                 <form:input type="text" path="phone"   class="form-control text-center" value="${user.mobileNumber}"  name="phone" readonly="true" ></form:input>
                                 <form:errors  path="phone"></form:errors>
                              </div>
                           </spring:bind>
                        </td>
                     </tr>
                     <td class="text-center">Amount to Pay</td>
                     <c:if test="${user.category.userTypeName == 'Patient' && user.age>9 }">
                        <td class="text-center" >
                           <spring:bind path="amount">
                              <div class="form-group  ${status.error ? 'has-error' : ''}">
                                 <form:input  type="text" path="amount" readonly="true"   class="form-control"  name="amount" value="750" ></form:input>
                                 <form:errors  path="amount"></form:errors>
                              </div>
                           </spring:bind>
                        </td>
                     </c:if>
                     <c:if test="${user.category.userTypeName == 'Other' && user.age>9 }">
                        <spring:bind path="amount">
                           <div class="form-group  ${status.error ? 'has-error' : ''}">
                              <form:input  type="text" path="amount" readonly="true"   class="form-control text-center"  name="amount" value="7000" ></form:input>
                              <form:errors  path="amount"></form:errors>
                           </div>
                        </spring:bind>
                     </c:if>
                     <c:if test="${user.category.userTypeName == 'Local' && user.age>9 }">
                        <td class="text-center" >
                           <spring:bind path="amount">
                              <div class="form-group  ${status.error ? 'has-error' : ''}">
                                 <form:input  type="text" path="amount" readonly="true"   class="form-control text-center"  name="amount" value="1500" ></form:input>
                                 <form:errors  path="amount"></form:errors>
                              </div>
                           </spring:bind>
                        </td>
                     </c:if>
                     <c:if test="${user.category.userTypeName == 'Official' && user.age>9 }">
                      <td class="text-center" >
                        <spring:bind path="amount">
                           <div class="form-group  ${status.error ? 'has-error' : ''}">
                              <form:input  type="text" path="amount"  readonly="true"  class="form-control text-center"  name="amount" value="1500" ></form:input>
                              <form:errors  path="amount"></form:errors>
                           </div>
                        </spring:bind>
                        </td>
                     </c:if>
                     <c:if test="${user.category.userTypeName == 'Patient' && user.age<=9 }">
                      <td class="text-center" >
                        <spring:bind path="amount">
                           <div class="form-group  ${status.error ? 'has-error' : ''}">
                              <form:input  type="text" path="amount" readonly="true"    class="form-control text-center"  name="amount" value="750" ></form:input>
                              <form:errors  path="amount"></form:errors>
                           </div>
                        </spring:bind>
                        </td>
                     </c:if>
                     <c:if test="${user.category.userTypeName == 'Other' && user.age<=9 }">
                      <td class="text-center" >
                        <spring:bind path="amount">
                           <div class="form-group  ${status.error ? 'has-error' : ''}">
                              <form:input  type="text" path="amount" readonly="true"   class="form-control text-center"  name="amount" value="750" ></form:input>
                              <form:errors  path="amount"></form:errors>
                           </div>
                        </spring:bind>
                        </td>
                     </c:if>
                     <c:if test="${user.category.userTypeName == 'Local' && user.age<=9 }">
                     <td>
                        <spring:bind path="amount">
                           <div class="form-group  ${status.error ? 'has-error' : ''}">
                              <form:input  type="text" path="amount" readonly="true"   class="form-control text-center"  name="amount" value="750" ></form:input>
                              <form:errors  path="amount"></form:errors>
                           </div>
                        </spring:bind>
                        </td>
                     </c:if>
                     </tr>
                  </tbody>
               </table>
               <br />
               <input type="submit"  value="Proceed to Pay" class="col-lg-12 btn btn-success">
               <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
               <br/>
            </div>
            <div class="col-lg-3"> &nbsp;  </div>
         </div>
         <!-- Section Three Ends -->
      </form:form>
</section>