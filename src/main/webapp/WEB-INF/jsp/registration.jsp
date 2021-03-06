<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flight_application_form.js"></script>

<script type="text/javascript">
   $( document ).ready(function() {
       getroles();
   });

</script>
<main class="app-content">
   <form:form method="POST" modelAttribute="registerUser" action="${pageContext.request.contextPath}/saveuser" class="form-signin">
      <h2 class="form-signin-heading">Create account</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="username">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" onkeypress="return alpha(event)" oncopy="return false" onpaste="return false"  path="username" class="form-control" placeholder="Username"
               autofocus="true"></form:input>
            <form:errors  path="username"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="mobileNumber">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:input type="text"  required="required" oncopy="return false" onpaste="return false" path="mobileNumber" maxlength="10" minlength="10"  class="form-control" onKeyPress="return isNumber(event)"  name="mobileNumber" placeholder="Mobile Number" ></form:input>
            <form:errors  path="mobileNumber"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="emailAddress">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:input type="text"  required="required" oncopy="return false" onpaste="return false" path="emailAddress" maxlength="40" minlength="10"  class="form-control"  name="emailAddress" placeholder="Email Address" ></form:input>
            <form:errors  path="emailAddress"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="gender">
               <div class="form-group  ${status.error ? 'has-error' : ''}">
                  <form:label path="gender" for="gender">Select Gender</form:label>
                  <form:select path="gender" name="gender" class="form-control" id="gender">
                  <form:option value="">--Please Select--</form:option>
                  <form:option value="M">Male</form:option>
                  <form:option value="F">Female</form:option>
                  <form:option value="O">Other</form:option>
                  </form:select>
                  <form:errors  path="gender"></form:errors>
               </div>
            </spring:bind>
      <spring:bind path="roleId">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:label path="roleId" for="roles">Select Roles</form:label>
            <form:select path="roleId" name="roleId" class="form-control" id="roles">
            </form:select>
            <form:errors  path="roleId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="password">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="password" path="password" oncopy="return false" onpaste="return false" class="form-control" placeholder="Password"></form:input>
            <form:errors path="password"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="passwordConfirm">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="password" oncopy="return false" onpaste="return false" path="passwordConfirm" class="form-control"
               placeholder="Confirm your password"></form:input>
            <form:errors path="passwordConfirm"></form:errors>
         </div>
      </spring:bind>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
</main>