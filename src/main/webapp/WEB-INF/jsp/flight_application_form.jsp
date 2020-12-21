<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/internationalization.js"></script>
<script type="text/javascript">
   $( document ).ready(function() {
      // getroles();

      $('#serviceavailingDate').datepicker({
              	format: "dd/mm/yyyy",
              	autoclose: true,
              	todayHighlight: true
              });
   });

</script>
<section>
 <div class="container back_pg" style="padding-top:10px;" >

   <h2 class="form-signin-heading"><spring:message code="instructions.head" text="Instructions Heading"/></h2>
   <ol class="breadcrumb">
      <li> <spring:message code="instructions.head.one" text="Instructions Heading One"/> </li>
      <li> <spring:message code="instructions.head.two" text="Instructions Heading Two"/> </li>
      <li><spring:message code="instructions.head.three" text="Instructions Heading Three"/></li>
      <li><spring:message code="instructions.head.four" text="Instructions Heading Four"/></li>
      <li><spring:message code="instructions.head.five" text="Instructions Heading Five"/> </li>
   </ol>
   <div class="row">
      <div class="col-lg-12">
         <hr>
         <h2> <spring:message code="form.application.Form" text="Flight Application Form for Patients/ Locals/ Others" /></h2>
         <hr>
      </div>



       <div class="form-group col-lg-4">
               <label for="locales"> <spring:message code="form.choose.lang"  text="Choose Language" /></label>
               <select class="form-control" id="locales">
               <option value=""><spring:message code="form.select" text="Please Select"/></option>
                  <option value="en"><spring:message code="form.lang.english" text="English"/></option>
                  <option value="hi"><spring:message code="form.lang.hindi" text="Hindi"/></option>
               </select>
            </div>


      <div class="form-group col-lg-4">
         <label for="fullname"> <spring:message code="form.fullname"   text="Full Name" /></label>
         <input type="text" class="form-control" id="fullname" >
      </div>
      <div class="form-group col-lg-4">
         <label for="parentname"> <spring:message code="form.parentname"   text="Parent Name" /> </label>
         <input type="text" class="form-control" id="parentname" >
      </div>
      <div class="form-group col-lg-4">
         <label for="mobilenumber"><spring:message code="form.mobilenumber"  text="Mobile Number" /></label>
         <input type="text" class="form-control" id="mobilenumber" >
      </div>
      <div class="form-group col-lg-4">
         <label for="age"> <spring:message code="form.age" text="Age" /></label>
         <input type="text" class="form-control" id="age" >
      </div>
      <div class="form-group col-lg-4">
         <label for="weight"> <spring:message code="form.weight" text="Weight" /></label>
         <input type="text" class="form-control" id="weight" >
      </div>
      <div class="form-group col-lg-4">
         <label for="luggage_weight"><spring:message code="form.estimated.luggage.weight" text="Luggage Weight (KG)" /></label>
         <input type="text" class="form-control" id="luggage_weight" >
      </div>


        <div class="form-group col-lg-4">
                     <label for="locales"> <spring:message code="form.reason.awailing.service"  text="Reasons for availing Flight Service." /></label>
                     <select class="form-control" id="locales">
                     <option value=""><spring:message code="form.select" text="Please Select"/></option>
                        <option value="en"><spring:message code="form.reason.awailing.service.option.one" text="Emergency"/></option>
                        <option value="hi"><spring:message code="form.reason.awailing.service.option.two" text="Examination"/></option>
                         <option value="hi"><spring:message code="form.reason.awailing.service.option.three" text="Officail"/></option>
                          <option value="hi"><spring:message code="form.reason.awailing.service.option.four" text="Other"/></option>
                     </select>
                  </div>


      <div class="form-group col-lg-4">
         <label for="serviceavailingDate"><spring:message code="form.serviceavailingDate"  text="Tentative Date for availing flight Service" /></label>
         <input type="text" class="form-control" id="serviceavailingDate" >
      </div>
       <div class="form-group col-lg-4">
               <label for="address"><spring:message code="form.address"  text="Address" /></label>
              <textarea rows="4" class="form-control" id="address" > </textarea>
            </div>

         <div class="form-group col-lg-4">
                       <label for="correspondence_address"><spring:message code="form.correspondence.address"  text="Correspondence Address" /></label>
                       <textarea rows="4" class="form-control" id="correspondenceaddress" > </textarea>
                    </div>
      <div class="form-group col-lg-4">
         <label for="reservationType"> <spring:message code="form.reservationType"  text="Type of Reservation" /></label>
         <select class="form-control" id="sel1">
            <option>Normal</option>
            <option>Emergency</option>
         </select>
      </div>
      <div class="form-innline col-lg-12">
         <label >
            <h6><spring:message code="form.service.availed.after.15.nov" text="Has the applicant availed the flight service after 15th Nov" />
                      </h6>
         </label>
         &nbsp; &nbsp;
         <label class="radio-inline"><input type="radio" name="oner" value="Y">Yes</label>
         <label class="radio-inline"><input type="radio" name="oner" value="N">No</label>
      </div>
      <div class="form-innline col-lg-12">
         <label >
            <h6><spring:message code="from.service.availed.emergency.situation" text="Has the earlier flight service been availed in Emergency Situation" />
                      </h6>
         </label>
         &nbsp; &nbsp;
         <label class="radio-inline"><input type="radio" name="oner" value="Y">Yes</label>
         <label class="radio-inline"><input type="radio" name="oner" value="N">No</label>
      </div>
   </div>
   <div class="row">
      <div class="col-lg-9">
         <hr>
         <strong><spring:message code="from.give.details.all.the.services.availed" text="Give details of all services availed earlier: -" />
                       </strong>
         <hr>
      </div>
      <div class="col-lg-3 ">
         <br/>
         <button type="button"  class="btn btn-success" data-style="slide-right" onclick="return addNewRow();" >Add Row</button>
         <button type="button"  class="btn btn-danger" data-style="slide-right" onclick="return deleteRow();" >Delete Row</button>
      </div>
   </div>
   <div class="row" >
      <div class="col-lg-4">
         <div class="form-group">
            <label for="username" class="field-label"><spring:message code="form.date.travell" text="Date of Travel" /></label>
            <input maxlength="10"  type="date" class="form-control input-sm" />
         </div>
      </div>
      <div class="col-lg-4">
         <div class="form-group">
            <label for="username" class="field-label"> <spring:message code="form.helipadname" text="From which Helipad Name" /></label>
            <input maxlength="10"  class="form-control input-sm" placeholder="" type="number" />
         </div>
      </div>
      <div class="col-lg-4">
         <div class="form-group">
            <label><strong> <spring:message code="form.category.emergency.general" text=" Category Emergency/General" /> </strong></label>
            <select  id="productname_id" name="hiringCenterList[0].instrumentN" class="form-control input-sm" data-live-search="true" data-width="100%" >
               <option value="">--Select--</option>
               <c:if test="${!empty instList}">
                  <c:forEach items="${instList}" var="instList">
                     <option value="${instList.instrumentName}">${instList.instrumentName}</option>
                  </c:forEach>
               </c:if>
            </select>
         </div>
      </div>
      <div id="addRow" class="col-lg-12">
      </div>
   </div>
   <div class="col-lg-4">
      <br /> <input type="submit" value="<spring:message code="customhiring.submit" text="Submit"/>"
      class="btn btn-success"> <input type="reset"
         value="<spring:message code="customhiring.reset" text="Reset"/>" class="btn btn-danger">
      <br/>
      <br/>
   </div>
   </div>
   </div>
</div>
</section>
<script type="text/javascript">
   var add= 0+${count+1};
   //alert(add);
   var status=0;

    function addNewRow()
   {

   	var row ='<div class="row " id="id'+add+'">'
   	+'<div class="col-lg-4"><div class="form-group"><input maxlength="10" name="hiringCenterList['+add+'].dateOfPur" type="date"   class="form-control" placeholder="UOM"  /></div></div>'
   	+'<div class="col-lg-4"><div class="form-group"><input maxlength="10" name="hiringCenterList['+add+'].rentPerD" type="number"  class="form-control" placeholder="Rent/Day"  /></div></div>'
       +'<div class="col-md-4"><div class="form-group"><select name="hiringCenterList['+add+'].instrumentN" id="productname_id" class="form-control input-sm" data-live-search="true" data-width="100%" >'
                				+'<option value="">--Select--</option><c:if test="${!empty instList}"><c:forEach items="${instList}" var="instList"><option value="${instList.instrumentName}">${instList.instrumentName}</option></c:forEach></c:if></select></div></div>'


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