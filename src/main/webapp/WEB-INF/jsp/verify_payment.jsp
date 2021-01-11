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



<main class="app-content">

  <form:form method="POST" modelAttribute="checkPayment" action="${pageContext.request.contextPath}/checkPaymentPost" class="form-signin">
            <h2 class="form-signin-heading"> Please enter Application ID</h2>
              <c:if test="${not empty successMessage}">
                                <div id="serverError" class="successMessage">${successMessage}</div>
                            </c:if>
                             <br>
                            <c:if test="${not empty serverError}">
                                <div id="serverError" class="plErroMessage">${serverError}</div>
                            </c:if>


                        <div class="row">



                          <spring:bind path="application_id">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                            <form:input path="application_id" name="application_id" class="form-control" type="text" placeholder="Enter Application Id" required="required"  />
                              <form:errors  path="application_id"></form:errors>
                            </div>
                             </spring:bind>




            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
               <c:remove var="successMessage" scope="session" />

               </div>
        </form:form>



    </div>




</main>