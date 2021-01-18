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
               <h4 class="text-left col-lg-12">Payment Status</h4>
               <hr>
               <table class="table table-hover table-bordered">
                  <tbody>
                     <tr>
                        <td class="text-center"> Payment Status</td>
                        <td class="text-center" >
                           ${paymnetdetails.status}
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">Transaction Id</td>
                        <td class="text-center" >
                           ${paymnetdetails.txnid}
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">Amount</td>
                        <td class="text-center" >
                        ${paymnetdetails.amount}
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">Application ID</td>
                        <td class="text-center" >
                        ${paymnetdetails.productinfo}
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">Name</td>
                        <td class="text-center" >
                        ${paymnetdetails.firstname}
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">Mobile Number</td>
                        <td class="text-center" >
                        ${paymnetdetails.phone}
                        </td>
                     </tr>
                     <tr>
                        <td class="text-center">Email</td>
                        <td class="text-center" >
                        ${paymnetdetails.email} </td>
                     </tr>
                  </tbody>
               </table>
               <br />
               <a href="${pageContext.request.contextPath}/checkStatus" class="btn-success col-lg-12 text-center"> Check Status of Submitted Application </a>
               <br/>
            </div>
            <div class="col-lg-3"> &nbsp;  </div>
         </div>
         <!-- Section Three Ends -->
      </form:form>
</section>