

<body onload="bodyOnloadHandler()" style="min-height:300px;">
<div class="container">
<div class= "row">
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
     <input class="col-lg-12 btn-success" type="submit" value="Proceed to payment gateway." />

   </form>
   </div>
   </div>

<script type="text/javascript">
    function bodyOnloadHandler() {
                console.log("body onload");
            }
</script>

   </body>
