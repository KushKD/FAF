<%@ page import="java.util.*" %>
<%@ page import="java.security.*" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flight_application_form.js"></script>
<script>
   function submitPayuForm() {
   alert("We are here");
         var payuForm = document.forms.payuForm;
         payuForm.submit();
         }

</script>
<body onload="submitPayuForm();">
   <form action="https://test.payu.in/_payment" method="post" name="payuForm">
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
     <input  type="submit" value="Submit" />

   </form>
</body>