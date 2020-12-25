<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/internationalization.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flight_application_form.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>


<script type="text/javascript">
   $( document ).ready(function() {
      getUserType();
      getRegistrationType();
      getRelationPrefix();
      getReasonAvailingFlightService();
      getFlightDistrictToGoFrom();
      gethalipadDistrict();

      $('#serviceavailingDate').datepicker({
              	format: "dd/mm/yyyy",
              	autoclose: true,
              	todayHighlight: true
              });
   });

</script>
<section>
<form:form method="POST" modelAttribute="flightApplicationForm" action="${pageContext.request.contextPath}/saveDetails" enctype="multipart/form-data" class="form-signin">
<c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
   <div class="container" style="padding-top:10px;" >
      <h2 class="form-signin-heading">
         <spring:message code="instructions.head" text="Instructions Heading"/>
      </h2>
      <ol class="breadcrumb">
         <li> <spring:message code="instructions.head.one" text="Instructions Heading One"/> </li>
         <li> <spring:message code="instructions.head.two" text="Instructions Heading Two"/>  </li>
         <li> <spring:message code="instructions.head.three" text="Instructions Heading Three"/> </li>
         <li> <spring:message code="instructions.head.four" text="Instructions Heading Four"/> </li>
         <li> <spring:message code="instructions.head.five" text="Instructions Heading Five"/> </li>
      </ol>


      <div class="row">
         <div class="col-lg-12">
            <hr> <h2> <spring:message code="form.application.Form" text="Flight Application Form for Patients/ Locals/ Others" /> </h2> <hr>
         </div>

           <spring:bind path="category">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <label> <spring:message code="form.user.category"  text="Category" /> </label>
            <form:select path="category" id="category" class="form-control" >
            </form:select>
             <form:errors  path="category"></form:errors>
         </div>
   </spring:bind>






  <spring:bind path="registrationType">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <form:label for="reservationType" path="registrationType">  <spring:message code="form.reservationType"  text="Type of Reservation" /> </form:label>
            <form:select path="registrationType" class="form-control" id="registrationType">
              </form:select>
             <form:errors  path="registrationType"></form:errors>
         </div>
         </spring:bind>




          <spring:bind path="fullName">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <form:label path="fullName" for="fullname"> <spring:message code="form.fullname"   text="Full Name" /> </form:label>
             <form:input type="text" path="fullName" id="fullName" class="form-control" placeholder="Username" autofocus="true" ></form:input>
           <form:errors  path="fullName"></form:errors>
         </div>
          </spring:bind>


         <spring:bind path="relationPrifix">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <form:label  path="relationPrifix" for="relation_prefix"><spring:message code="form.parentname.prefix"  text="Relation Prefix" /></form:label>
            <form:select  path="relationPrifix" class="form-control" id="relationPrifix">
                  <form:option value="2">  <spring:message code="form.parentname.relation.do" text="D/O" /> </form:option>
            </form:select>
            <form:errors  path="relationPrifix"></form:errors>
         </div>
          </spring:bind>


          <spring:bind path="relationName">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <form:label path="relationName" for="parentname">  <spring:message code="form.parentname.relation.name"   text="Parent Name"  /> </form:label>
            <form:input type="text" path="relationName" id="relationName" class="form-control"  />
            <form:errors  path="relationName"></form:errors>
         </div>
          </spring:bind>


           <spring:bind path="mobileNumber">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <form:label path="mobileNumber" for="mobilenumber"> <spring:message code="form.mobilenumber"  text="Mobile Number" /> </form:label>
            <form:input type="text" path="mobileNumber" id="mobileNumber" class="form-control"  />
            <form:errors  path="mobileNumber"></form:errors>
         </div>
         </spring:bind>


          <spring:bind path="age" >
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <form:label path="age" for="age"> <spring:message code="form.age" text="Age" /> </form:label>
            <form:input type="text"  path="age" id="age" class="form-control" />
            <form:errors  path="age"></form:errors>
         </div>
         </spring:bind>

         <spring:bind path="weight">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <form:label  path="weight"  for="weight"> <spring:message code="form.weight" text="Weight" /> </form:label>
            <form:input type="text"  path="weight"  id="weight" class="form-control" />
             <form:errors  path="weight"></form:errors>
         </div>
         </spring:bind>

         <spring:bind path="luggageWeight">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}" >
            <label for="luggage_weight"> <spring:message code="form.estimated.luggage.weight" text="Luggage Weight (KG)" /> </label>
            <form:input type="text"  path="luggageWeight" id="luggageWeight" class="form-control"  />
            <form:errors  path="luggageWeight"></form:errors>
         </div>
          </spring:bind>


          <spring:bind path="permanentAddress">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <label for="address"> <spring:message code="form.address"  text="Address" /> </label>
            <form:textarea rows="4" path="permanentAddress" id="permanentAddress" class="form-control"  />
             <form:errors  path="permanentAddress"></form:errors>
         </div>
          </spring:bind>

         <spring:bind path="correspondenceAddress">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <label for="correspondence_address">  <spring:message code="form.correspondence.address"  text="Correspondence Address" /> </label>
            <form:textarea rows="4"  path="correspondenceAddress" class="form-control" id="correspondenceAddress" />
            <form:errors  path="correspondenceAddress"></form:errors>
         </div>
         </spring:bind>

      </div>



      <div class="row" style="margin-bottom:10px;">
         <div class="col-lg-12">
            <hr> <strong> <spring:message code="form.details.flight" text="Flight Details" /> </strong> <hr>
         </div>

          <spring:bind path="reasonAvailingFlightService">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <label for="locales">  <spring:message code="form.reason.awailing.service"  text="Reasons for availing Flight Service." /> </label>
            <form:select path="reasonAvailingFlightService" class="form-control" id="reasonAvailingFlightService">
                </form:select>
            <form:errors  path="reasonAvailingFlightService"></form:errors>
         </div>
         </spring:bind>

         <spring:bind path="tentitiveFlightDate">
         <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
            <label for="serviceavailingDate"> <spring:message code="form.serviceavailingDate"  text="Tentative Date for availing flight Service" /> </label>
            <form:input maxlength="10"  path="tentitiveFlightDate" id="tentitiveFlightDate" type="date" class="form-control input-sm" />
            <form:errors  path="tentitiveFlightDate"></form:errors>
         </div>
          </spring:bind>

           <spring:bind path="flightDistrictToGoFrom">
                   <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
                      <label for="locales">  <spring:message code="form.togodistirct"  text="Select District" /> </label>
                      <form:select path="flightDistrictToGoFrom" class="form-control" id="flightDistrictToGoFrom" onchange="getFlightHelipadNameToGoFrom(this.value)">

                      </form:select>
                      <form:errors  path="flightDistrictToGoFrom"></form:errors>
                   </div>
                   </spring:bind>


                    <spring:bind path="flightHelipadNameToGoFrom">
                            <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
                               <label for="locales">  <spring:message code="from.togohalipadname"  text="Select Helipad" /> </label>
                               <form:select path="flightHelipadNameToGoFrom" class="form-control" id="flightHelipadNameToGoFrom">

                               </form:select>
                               <form:errors  path="flightHelipadNameToGoFrom"></form:errors>
                            </div>
                            </spring:bind>


 <spring:bind path="availedFlightBefore15">
         <div class="form-innline col-lg-12 ${status.error ? 'has-error' : ''}">
            <label ><h6>   <spring:message code="form.service.availed.after.15.nov" text="Has the applicant availed the flight service after 15th Nov" /> </h6>
            </label>
            &nbsp; &nbsp;
            <form:radiobutton  path = "availedFlightBefore15" value = "Y" label = "Yes" />
            <form:radiobutton path = "availedFlightBefore15" value = "N" label = "No" />
 <form:errors  path="availedFlightBefore15"></form:errors>
         </div>
          </spring:bind>


 <spring:bind path="earlierFlightServiceEmergency">
         <div class="form-innline col-lg-12 ${status.error ? 'has-error' : ''}">
            <label >
               <h6>  <spring:message code="from.service.availed.emergency.situation" text="Has the earlier flight service been availed in Emergency Situation" />
               </h6>
            </label>
            &nbsp; &nbsp;
             <form:radiobutton path = "earlierFlightServiceEmergency" value = "Y" label = "Yes" />
             <form:radiobutton path = "earlierFlightServiceEmergency" value = "N" label = "No" />
             <form:errors  path="earlierFlightServiceEmergency"></form:errors>
         </div>
      </div>
       </spring:bind>


      <!-- Documentary Proof Start -->
      <div class="row" style="margin-bottom:10px;">
         <div class="col-lg-12"><hr><strong><spring:message code="form.documentry.proff" text="Flight Details" /></strong><hr></div>
         <div class="form-group col-lg-4">
            <label for="aadhaar_doc" class="form-label"><spring:message code="form.documentry.aadhaar" text="Aadhaar Card" /> </label>
            <form:input class="form-control" type="file" id="aadhaar_doc" path="aadhaar_doc" name="aadhaar_doc" />
         </div>
         <div class="form-group col-lg-4">
            <label for="officeCardDoc" class="form-label"><spring:message code="form.documentry.officeid" text="Office Card" /></label>
            <form:input class="form-control" type="file" path="officeCardDoc" id="officeCardDoc" name="officeCardDoc"  />
         </div>
         <div class="form-group col-lg-4">
            <label for="medicalDoc" class="form-label"><spring:message code="form.documentry.medial" text="Medical Report" /> </label>
            <form:input class="form-control" type="file" path="medicalDoc" id="medicalDoc" name="medicalDoc"  />
         </div>
         <div class="form-group col-lg-4">
            <label for="otherDoc" class="form-label"><spring:message code="form.documentry.other" text="Any Other Document" /></label>
            <form:input class="form-control" type="file" id="otherDoc" path="otherDoc" name="otherDoc"  />
         </div>
      </div>
      <!-- Documentary Proof Ends -->





      <div class="row" style="margin-bottom:10px;">
         <div class="col-lg-9">
            <hr>
            <strong>
               <spring:message code="from.give.details.all.the.services.availed" text="Give details of all services availed earlier: -" />
            </strong>
            <hr>
         </div>
         <div class="col-lg-3 ">
            <br/>
            <button type="button"  class="btn btn-success" data-style="slide-right" onclick="return addNewRow();" >Add Row</button>
            <button type="button"  class="btn btn-danger" data-style="slide-right" onclick="return deleteRow();" >Delete Row</button>
         </div>
      </div>
      <div class="row" style="margin-top:10px">

       <spring:bind path="dateTravel">
         <div class="col-lg-4 ${status.error ? 'has-error' : ''}">
            <div class="form-group">
               <form:label path="dateTravel" for="dateTravel" class="field-label"/>
                  <spring:message code="form.date.travell" text="Date of Travel" />
               <form:input path="availedServiceListForm[0].dateTravelled" maxlength="10"  type="date" class="form-control input-sm" />
               <form:errors  path="availedServiceListForm[0].dateTravelled"></form:errors>
            </div>
         </div>
         </spring:bind>


         <spring:bind path="halipadDistrict">
         <div class="col-lg-4 ${status.error ? 'has-error' : ''}">
            <div class="form-group">
               <form:label for="username" path="halipadDistrict" class="field-label"> <spring:message code="form.halipadDistrict" text="From which District" /></form:label>

               <form:select id="halipadDistrict" onchange="gethalipadLocation(this.value)"  path="availedServiceListForm[0].helipadDistrict" class="form-control input-sm" placeholder="" >
               </form:select>
               <form:errors  path="halipadDistrict"></form:errors>
            </div>
         </div>
         </spring:bind>

         <spring:bind path="halipadLocation">
         <div class="col-lg-4 ${status.error ? 'has-error' : ''}">
            <div class="form-group">
               <form:label path="halipadLocation">  <strong> <spring:message code="form.halipadname" text="Helipad Name" /></strong>
               </form:label>
               <form:select path="halipadLocation" id="halipadLocation" name="availedServiceListForm[0].helipadName" class="form-control input-sm" data-live-search="true" data-width="100%" >
                  <form:option value="">--Select--</form:option>

               </form:select>
               <form:errors  path="halipadLocation"></form:errors>
            </div>
         </div>
         </spring:bind>

         <div id="addRow" class="col-lg-12">
         </div>
      </div>

      <!-- Identity Bond Start -->
            <div class="row breadcrumb" style="margin-bottom:10px;">
               <p class="form-signin-heading">
                        <spring:message code="for.identity.bond" text="Instructions Heading"/>
                     </p>
            </div>
            <!-- Identity Bond Ends -->





             <!-- Declaration Start -->
             <spring:bind path="declerationUser">
                        <div class="row breadcrumb " style="margin-bottom:10px;">
                            <label > <h6><spring:message code="form.declaration" text="Declaration" /> </h6> </label>
                             &nbsp; &nbsp;
                              <div class="form-innline col-lg-12 ${status.error ? 'has-error' : ''}">
                             <form:radiobutton path = "declerationUser" value = "Y" label = "Yes" />
                             <form:radiobutton path = "declerationUser" value = "N" label = "No" />
                             <form:errors  path="declerationUser"></form:errors>
                             <div>
                        </div>
                         </spring:bind>
                        <!-- Declaration End -->

<!-- Captcha -->

<!-- Captcha -->


      <div class="col-lg-4">
         <br />
         <input type="submit" value="<spring:message code="customhiring.submit" text="Submit"/>" class="btn btn-success">
          <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
          <br/>
         <br/>
      </div>
   </div>
   </div>
   </div>
   </form:form>
</section>
<script type="text/javascript">
   var add= 0+${count+1};
   //alert(add);
   var status=0;

    function addNewRow()
   {
   gethalipadDistrictadd(add);
   	var row ='<div class="row " id="id'+add+'">'
   	+'<div class="col-lg-4"><div class="form-group"><input maxlength="10" path="availedServiceListForm['+add+'].dateTravelled" name="availedServiceListForm['+add+'].dateTravelled" type="date"   class="form-control" placeholder="Date"  /></div></div>'
   	+'<div class="col-lg-4"><div class="form-group"><select maxlength="10" path="availedServiceListForm['+add+'].dateTravelled" name="availedServiceListForm['+add+'].helipadDistrict" id="helipadDistrict'+add+'"   class="form-control" placeholder="District"  onchange="gethalipadLocationadd(this.value ,'+add+')" ></select></div></div>'
    +'<div class="col-md-4"><div class="form-group"><select path="availedServiceListForm['+add+'].helipadName" name="availedServiceListForm['+add+'].helipadName" id="halipadLocation'+add+'" class="form-control input-sm"  data-width="100%"></select></div></div>'
    +'</div>'

   	add++;
        $("#addRow").append(row);
        $('.bootstrap-select').selectpicker('render');
   }

   function deleteRow()
   {

   	if(add==0){
   		alert("Last Row can Not be deleted !!")
   	}else{

   		$("#id"+(add-1)).remove();
   		add--;
   	}
   }


</script>