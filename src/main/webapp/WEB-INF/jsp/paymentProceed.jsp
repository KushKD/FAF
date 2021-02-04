<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <form method="POST" name="payuForm"  action="https://secure.payu.in/_payment">
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
     <div class=" col-lg-12 breadcrumb">
      <h6 style="color:red;" class="text-left">**It is advisable not to click the back button while proceeding for payment. </h6>
     </div>
     </div>
     </div>
      <div class="row ">
         <div class="col-lg-3"> &nbsp;  </div>
         <div class="table-responsive col-lg-6 breadcrumb">
            <h4 class="text-left col-lg-12">Verify the Details before proceeding :</h4>
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
                  <c:if test="${user.category.userTypeName == 'Patient' && user.age>9 }">
                     <td class="text-center" >750/-</td>
                  </c:if>
                  <c:if test="${user.category.userTypeName == 'Other' && user.age>9 }">
                  <td class="text-center" >7000/-</td>
                  </c:if>
                  <c:if test="${user.category.userTypeName == 'Local' && user.age>9 }">
                     <td class="text-center" >1500/-
                     </td>
                  </c:if>
                  <c:if test="${user.category.userTypeName == 'Official' && user.age>9 }">
                     <td class="text-center" > 1500/-
                     </td>
                  </c:if>
                  <c:if test="${user.category.userTypeName == 'Patient' && user.age<=9 }">
                     <td class="text-center" >
                     750/-
                     </td>
                  </c:if>
                  <c:if test="${user.category.userTypeName == 'Other' && user.age<=9 }">
                     <td class="text-center" >750/-
                     </td>
                  </c:if>
                  <c:if test="${user.category.userTypeName == 'Local' && user.age<=9 }">
                     <td> 750/-
                     </td>
                  </c:if>
                  </tr>
               </tbody>
            </table>
            <br />
            <input type="hidden" name="key" value="<%= session.getAttribute("merchant_key") %>" />
                  <input type="hidden" name="hash" value="<%= session.getAttribute("hash") %>"/>
                  <input type="hidden" name="txnid" value="<%= session.getAttribute("txnid") %>" />
                  <input type="hidden" name="amount" value="<%= session.getAttribute("amount") %>" />
                  <input type="hidden" name="firstname" id="firstname" value="<%= session.getAttribute("firstname") %>" />
                  <input type="hidden" name="email" id="email" value="<%= session.getAttribute("email") %>" />
                  <input type="hidden" name="phone" value="<%= session.getAttribute("phone") %>" />
                  <input type="hidden" name="productinfo" value="<%= session.getAttribute("productinfo") %>" />
                  <input type="hidden" name="surl" value="<%= session.getAttribute("surl") %>" size="64" />
                  <input type="hidden" name="furl" value="<%= session.getAttribute("furl") %>" size="64" />
            <input class="col-lg-12 btn-success" name="submit" type="submit" value="Proceed to Payment ." />
             <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            <br/>
         </div>
         <div class="col-lg-3"> &nbsp;  </div>
      </div>
      <!-- Section Three Ends -->
      </form>
