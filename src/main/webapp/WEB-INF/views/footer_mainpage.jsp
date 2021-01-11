<!--- footer Starts --->
      <div class="container-fluid">
         <div class="row">
            <div style="margin-top:20px; background-color: #0074B3; width:100%;">
               &nbsp;
            </div>
         </div>
      </div>
      <div class="container-fluid" >
         <div class="row" style="background-color: #114368; ">
            <div class="col-lg-4">
               <h3 style="margin-top:0; padding-top:20px; padding-bottom:10px; padding-left:30px; color:#FFF;">Other Department Links</h3>
               <ul class="footer_list">
                  <li ><a href="https://www.himachal.nic.in/en-IN/" target="_blank">Government of Himachal Pradesh</a></li>
                  <li ><a href="https://cmsankalp.hp.gov.in/" target="_blank">CM Helpline</a></li>
                  <li ><a href="http://cmhimachal.nic.in/" target="_blank">CM Relief Refund</a></li>

               </ul>
            </div>
            <div class="col-lg-4">
               <h3 style="margin-top:0; padding-top:20px; padding-bottom:10px; padding-left:30px; color:#FFF;">Important Schemes</h3>
               <ul class="footer_list">
                  <li><a title="Terms and Conditions"  href="${pageContext.request.contextPath}/termsandconditions">Terms and Conditions </a></li>
                  <li><a title="Privacy Policy" href="${pageContext.request.contextPath}/privacypolicy">Privacy Policy </a></li>
                   <li><a title="Refund Policy" href="${pageContext.request.contextPath}/refundpage">Refund Policy </a></li>

               </ul>
            </div>
            <div class="col-lg-4">
               <h3 style="margin-top:0; padding-top:20px; padding-bottom:10px; padding-left:30px; color:#FFF;">&nbsp;</h3>
                &nbsp;
            </div>
         </div>

      </div>
      <!-- Footer Ends -->
     <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/internationalization.js"></script>


      <script>
         /* Toggle between showing and hiding the navigation menu links when the user clicks on the hamburger menu / bar icon */
function myFunction() {
  var x = document.getElementById("myLinks");
  if (x.style.display === "block") {
    x.style.display = "none";
  } else {
    x.style.display = "block";
  }
}

 function disableBack() {window.history.forward()}

    window.onload = disableBack();
    window.onpageshow = function (evt) {if (evt.persisted) disableBack()}
      </script>

   </body>
  </html>