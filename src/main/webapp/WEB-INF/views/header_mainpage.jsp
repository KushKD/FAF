<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>ePushpak</title>
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- Main CSS-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   </head>
   <head>
   <body >
      <!--Top Bar Starts-->
      <section class="container-fluid top_bar"  >
         <div class="row">
            <div class="col-xl-7 top_bar_col1 ">&nbsp;</div>
            <div class="col-xl-5 top_bar_col2" style="margin-top:10px" >
               <div class="flex_style">
                  <ul style="display:none;" class="accessibility">
                     <li > <a  href="#">A<sub>-</sub></a></li>
                     <li> <a  href="#">A</a></li>
                     <li > <a   href="#">A<sub>+</sub></a></li>
                  </ul>
                  <div class="register_department" ><a class="text_decorate" href="${pageContext.request.contextPath}/login" >Department Login</a> </div>
                  <div class="form-group col-lg-5">
                     <select class="form-control" id="locales">
                        <option value="">
                           <spring:message code="form.choose.lang" text="Choose Language"/>
                        </option>
                        <option value="en">
                           <spring:message code="form.lang.english" text="English"/>
                        </option>
                        <option value="hi">
                           <spring:message code="form.lang.hindi" text="Hindi"/>
                        </option>
                     </select>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <!-- Top Bar Ends-->
      <!-- Navigation and Header-->
      <div class="header_logo_megamenu back_pg" >
         <div class="row ">
            <div class="col-xl-1 col-lg-1 col-md-1 col-sm-1 col-1" style="margin-left: 50px;"> <img src="${pageContext.request.contextPath}/resources/images/logo_gov_hp.png"  class="logo_hp"   alt="Himachal Pradesh Government"></div>
            <div class="col-xl-5 col-lg-5 col-md-10 col-sm-9 col-9 padding_">
               <div>
                  <p class="text-left text_logo_one">ePushpak (V1.0)</p>
               </div>
               <div>
                  <p class="text_logo_two">Department of Information Technology, Government of Himachal Pradesh  </p>
               </div>
            </div>
            <div class="col-xl-5 col-lg-5 col-md-12">
               <nav class="navbar_" style="margin-top: 15px;">
                  <ul >
                     <li><a href="${pageContext.request.contextPath}/"> <img src="${pageContext.request.contextPath}/resources/images/vector/house.svg" class="icon_mega_menu"><span class="parent-menga-menu">Home</span></a></li>
                     <li>
                        <a href="#"> <img src="${pageContext.request.contextPath}/resources/images/vector/information.svg" class="icon_mega_menu"><span class="parent-menga-menu">Information</span></a>
                        <div class="mega-menu">

                           <div class="row">
                              <!-- First Row-->
                              <div  class="col-lg-4" style="float:left">
                                 <div><span class="mega-menu-header"> Important Notifications </span></div>
                                 <ul >
                                    <li>
                                       <a href="${pageContext.request.contextPath}/resources/pdf/Regarding_Term_and_condition_of_winter_helicopter.pdf" target="_blank">
                                          <div class="mega-menu-div-design">
                                             <i class="fa fa-caret-right mega-menu-div-design-fa-icon" aria-hidden="true"></i>
                                             <span class="mega-menu-div-design-span"> Terms and Condition for Winter Helicopter Service </span>
                                          </div>
                                       </a>
                                    </li>




                                 </ul>
                              </div>
                              <!-- First Row Ends-->


                           </div>
                        </div>
                     </li>


                  </ul>
               </nav>
            </div>
         </div>
      </div>
      <!-- Section More Options-->
      <section class="secondary_menu_options" style="width: auto"  >
         <div class="row">
            <div class="hide_unhide">
               <ul class="secondary_menu">
                  <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                  <li><a href="${pageContext.request.contextPath}/contactus">Contact Us</a></li>
                  <li><a href="https://cmsankalp.hp.gov.in/" target="_blank">Grievance</a></li>
                  <li><a href="${pageContext.request.contextPath}/gallery">Gallery</a></li>
                  <li><a href="${pageContext.request.contextPath}/applicationform">Flight Application Form</a></li>
                  <li><a href="${pageContext.request.contextPath}/checkStatus">Check Application Status</a></li>
               </ul>
            </div>
            <div class="topnav">
               <a href="#home" class="active">&nbsp;</a>
               <!-- Navigation links (hidden by default) -->
               <div id="myLinks">
                  <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                  <li><a href="${pageContext.request.contextPath}/contactus">Contact Us</a></li>
                  <li><a href="https://cmsankalp.hp.gov.in/" target="_blank">Grievance</a></li>
                  <li><a href="${pageContext.request.contextPath}/gallery">Gallery</a></li>
                  <li><a href="${pageContext.request.contextPath}/applicationform">Flight Application Form</a></li>
                  <li><a href="${pageContext.request.contextPath}/checkStatus">Check Application Status</a></li>
               </div>
               <!-- "Hamburger menu" / "Bar icon" to toggle the navigation links -->
               <a href="javascript:void(0);" class="icon" onclick="myFunction()">
               <i class="fa fa-bars"></i>
               </a>
            </div>
         </div>
      </section>
      <!--
         </body>
         </html>

         -->