<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flight_application_form.js"></script>

<section class="container" style="margin-top:30px; margin-bottom:30px;">
   <div class="row">
      <h2 class="col-lg-12 text-center"  style="color:#77332F;"> Official Login </h2>
      <br>
   </div>
   <div class="row">
      <div class="col-lg-4"> &nbsp </div>
      <div class="col-lg-4" style="padding:10px; marging:10px; background-color:#F2F2F2; ">
         <form  method="post" class="loginForm"  action="${pageContext.request.contextPath}/login">
            <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>SIGN IN</h3>
            <div class="form-group col-lg-12">
               <label class="control-label" for="username" >Username:</label><br>
               <input class="form-control" type="text" maxlength="10" onkeypress="return alpha(event)"  required autofocus name="username" id="username">
            </div>
            <div class="form-group col-lg-12">
               <label class="control-label" for="password" >Password:</label><br>
               <input class="form-control" type="password" name="password"  maxLength="10"  size="10" id="password">
            </div>
            <div class="form-group col-lg-12">
               <input class="btn btn-success btn-block col-lg-12" type="submit" name="submit" class="btn btn-info btn-md" value="submit">
               <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            </div>
            <div class="form-group">
               <c:if test="${param.error != null}">
                  <p>Login Failed</p>
               </c:if>
            </div>
         </form>
      </div>
      <div class="col-lg-3"> &nbsp </div>
   </div>
</section>