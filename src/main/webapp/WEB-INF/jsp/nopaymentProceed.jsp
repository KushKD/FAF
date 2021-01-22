<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div class="container">
     <div class="row">

     </div>
     </div>
      <div class="row ">
         <div class="col-lg-3"> &nbsp;  </div>
         <div class="table-responsive col-lg-6 breadcrumb">
            <h4 class="text-left col-lg-12">User  Details:</h4>
            <hr>
            <table class="table table-hover table-bordered">
               <tbody>
                  <tr>
                     <td class="text-center">Application No.</td>
                     <td class="text-center" >${user.userId}
                     </td>
                  </tr>
                  <tr>
                     <td class="text-center">User Full Name:</td>
                     <td class="text-center" >${user.fullName}
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
                     <td class="text-center" >${user.mobileNumber}</input>
                     </td>
                  </tr>
                  <td class="text-center">Amount to Pay</td>
                     <td class="text-center" >0/-</td>
                  </tr>
               </tbody>
            </table>
            <br />
            <a href="${pageContext.request.contextPath}/checkStatus" class="btn-success col-lg-12 text-center"> Check Status of Submitted Application. </a>
            <br/>
         </div>
         <div class="col-lg-3"> &nbsp;  </div>
      </div>

