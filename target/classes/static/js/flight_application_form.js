// Get User Type
function getUserType() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/getUserType",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#category'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].userTypeId + " >" + data.RESPONSE[i].userTypeName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}

//registrationType
function getRegistrationType() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/registrationType",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#registrationType'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].reservationTypeId + " >" + data.RESPONSE[i].reservationTypeName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}

//relationshipPrefix
function getRelationPrefix() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/relationshipPrefix",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#relationPrifix'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].relationshipPrifixId + " >" + data.RESPONSE[i].relationshipPrifixName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}

//reasonAvailingFlightService
function getReasonAvailingFlightService() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/reasonAvailingFlightService",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#reasonAvailingFlightService'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].reasonAvailingFlightId + " >" + data.RESPONSE[i].reasonAvailingFlightName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}

//flightDistrictToGoFrom
function getFlightDistrictToGoFrom() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightDistrictToGoFrom",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#flightDistrictToGoFrom'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].districtId + " >" + data.RESPONSE[i].districtName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}

function getFlightDistrictToGoFrom_dest() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightDistrictToGoFrom",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#flightDistrictToGoFrom_dest'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].districtId + " >" + data.RESPONSE[i].districtName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}

function getLocations() {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightDistrictToGoFrom",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#district'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Select Location---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                if (document.getElementById('lid') != null && document.getElementById('lid').value == data.RESPONSE[i].districtId) {
                    selectRole.append("<option selected value=" + data.RESPONSE[i].districtId + " >" + data.RESPONSE[i].districtName + "</option>")
                } else {
                    selectRole.append("<option value=" + data.RESPONSE[i].districtId + " >" + data.RESPONSE[i].districtName + "</option>")
                }
            }

        },
        error: function(data) {
            console.log(data)
        }

    });


}

//flightHelipadNameToGoFrom
function getFlightHelipadNameToGoFrom(id) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightHelipadNameToGoFrom",
        data: {
            "id": id
        },
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#flightHelipadNameToGoFrom'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "--- Select ---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                if (document.getElementById('flightHelipadNameToGoFrom') != null && document.getElementById('flightHelipadNameToGoFrom').value == data.RESPONSE[i].helipadId) {
                    selectRole.append("<option selected value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                } else {
                    selectRole.append("<option value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                }

            };

        },
        error: function(data) {
            console.log(data)
        }

    });


}

function getFlightHelipadNameToGoFrom_dest(id) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightHelipadNameToGoFrom",
        data: {
            "id": id
        },
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#flightHelipadNameToGoFrom_dest'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "--- Select ---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {

                    selectRole.append("<option value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
              

            };

        },
        error: function(data) {
            console.log(data)
        }

    });


}

//loadhelipads
function loadhelipads(id) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightHelipadNameToGoFrom",
        data: {
            "id": id
        },
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#helipad'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Select Helipad ---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                if (document.getElementById('hid') != null && document.getElementById('hid').value == data.RESPONSE[i].helipadId) {
                    selectRole.append("<option selected value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                } else {
                    selectRole.append("<option value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                }

            };

        },
        error: function(data) {
            console.log(data)
        }

    });


}



function gethalipadDistrict() {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightDistrictToGoFrom",
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#halipadDistrict'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].districtId + " >" + data.RESPONSE[i].districtName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}



function gethalipadDistrictadd(add) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightDistrictToGoFrom",
        success: function(data) {
            console.log(data.RESPONSE)
            var id_ = "#helipadDistrict" + add;
            console.log(id_)
            var selectRole = $(id_); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].districtId + " >" + data.RESPONSE[i].districtName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });
}

// gethalipadLocation();
function gethalipadLocation(id) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightHelipadNameToGoFrom",
        data: {
            "id": id
        },
        success: function(data) {
            console.log(data.RESPONSE)
            var selectRole = $('#halipadLocation'); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "--- Select ---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                if (document.getElementById('halipadLocation') != null && document.getElementById('halipadLocation').value == data.RESPONSE[i].helipadId) {
                    selectRole.append("<option selected value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                } else {
                    selectRole.append("<option value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                }

            };

        },
        error: function(data) {
            console.log(data)
        }

    });


}


function gethalipadLocationadd(id, add) {

    $.ajax({
        type: "GET",
        url: formURL + "/ajax/flightHelipadNameToGoFrom",
        data: {
            "id": id
        },
        success: function(data) {
            console.log(data.RESPONSE)
            var id_ = "#halipadLocation" + add;
            var selectRole = $(id_); // the state select element
            selectRole.find('option').remove();
            selectRole.append("<option value=" + 0 + " >" + "--- Select ---" + "</option>")
            for (i = 0; i < data.RESPONSE.length; i++) {
                selectRole.append("<option value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
            }

        },
        error: function(data) {
            console.log(data)
        }

    });

}

function hasExtension(inputID, exts) {
    var fileName = document.getElementById(inputID).value;
    return (new RegExp('(' + exts.join('|').replace(/\./g, '\\.') + ')$')).test(fileName);
}

//Validations goes here
function validateFields() {

    var category = document.getElementById('category').value;
    var registrationType = document.getElementById('registrationType').value;
    var fullName = document.getElementById('fullName').value;
    var relationPrifix = document.getElementById('relationPrifix').value;
    var relationName = document.getElementById('relationName').value;
    var mobileNumber = document.getElementById('mobileNumber').value;
    var age = document.getElementById('age').value;
    var weight = document.getElementById('weight').value.trim();
    var luggageWeight = document.getElementById('luggageWeight').value;
    var permanentAddress = document.getElementById('permanentAddress').value;
    var correspondenceAddress = document.getElementById('correspondenceAddress').value;
    var reasonAvailingFlightService = document.getElementById('reasonAvailingFlightService').value.trim();
    var comments_Text = document.getElementById('comments_Text').value.trim();
    var tentitiveFlightDate = document.getElementById('tentitiveFlightDate').value.trim();
    var serviceavailingDateto = document.getElementById('serviceavailingDateto').value.trim();
    var flightDistrictToGoFrom = document.getElementById('flightDistrictToGoFrom').value.trim();
    var flightHelipadNameToGoFrom = document.getElementById('flightHelipadNameToGoFrom').value.trim();
    var availedFlightBefore15 = document.getElementsByName('availedFlightBefore15');
    var availedFlightBefore15Array = Array.from(availedFlightBefore15);
    var availedFlightBefore15Checked = availedFlightBefore15Array.some(element => element.checked);
    var earlierFlightServiceEmergency = document.getElementsByName('earlierFlightServiceEmergency');
    var earlierFlightServiceEmergencyArray = Array.from(earlierFlightServiceEmergency);
    var earlierFlightServiceEmergencyChecked = earlierFlightServiceEmergencyArray.some(element => element.checked);
    var declerationUser = document.getElementsByName('declerationUser');
    var declerationUserArray = Array.from(declerationUser);
    var declerationUserChecked = declerationUserArray.some(element => element.checked);
    var earlierService = document.getElementById('earlierService').value;

    var aadhaar_doc = document.getElementById('aadhaar_doc').value.trim();
    var officeCardDoc = document.getElementById('officeCardDoc').value.trim();
    var medicalDoc = document.getElementById('medicalDoc').value.trim();
    var otherDoc = document.getElementById('otherDoc').value.trim();




    if (category == "" || category == null || category == "0") {
        //setFocus(category);
        alert("Please select User Type");
        return false;
    } else if (registrationType == "" || registrationType == null || registrationType == "0") {
        alert("Please Select the Type of Registration.");
        return false;
    } else if (fullName == "" || fullName == null) {
        alert("Please Enter Full Name of Applicant.");
        return false;
    } else if (relationPrifix == "" || relationPrifix == null || relationPrifix == "0") {
        alert("Please Select Relationship Prefix.");
        return false;
    } else if (relationName == "" || relationName == null) {
        alert("Please Select Relationship Name");
        return false;
    } else if (mobileNumber == "" || mobileNumber == null || mobileNumber.length != 10) {
        alert("Please enter a valid 10 digit mobile number");
        return false;
    } else if (age == "" || age == null) {
        alert("Please Enter Age.");
        return false;
    } else if (weight == "" || weight == null) {
        alert("Please enter Weight.");
        return false;
    } else if (luggageWeight == "" || luggageWeight == null) {
        alert(luggageWeight);
        alert("Please enter the Weight of the Luggage.");
        return false;
    } else if (parseInt(luggageWeight) >= parseInt('11')) {
        //  alert(parseInt(luggageWeight)+" int value");
        // alert(parseInt('10')+" int value");
        alert("Luggage Weight cannot be more than 10 KG");
        return false;
    } else if (permanentAddress == "" || permanentAddress == null) {
        alert("Please enter complete Permanent Address");
        return false;
    } else if (reasonAvailingFlightService == "" || reasonAvailingFlightService == null || reasonAvailingFlightService == "0") {
        alert("Please select the reason for availing the Flight Service");
        return false;
    } else if (tentitiveFlightDate == "" || tentitiveFlightDate == null) {
        alert("Please enter Tentative Date");
        return false;
    } else if (serviceavailingDateto == "" || serviceavailingDateto == null) {
        alert("Please select Tentative Date (TO) for avialing flight service");
        return false;
    } else if (flightDistrictToGoFrom == "" || flightDistrictToGoFrom == null || flightDistrictToGoFrom == "0") {
        alert("Please select District");
        return false;
    } else if (flightHelipadNameToGoFrom == "" || flightHelipadNameToGoFrom == null || flightHelipadNameToGoFrom == "0") {
        alert("Please select Helipad");
        return false;
    } else if (!availedFlightBefore15Checked) {
        alert("Weather flight has been availed before 15 November");
        return false;
    } else if (!earlierFlightServiceEmergencyChecked) {
        alert("Any Other Service Availed in Emergency.");
        return false;
    } else if (earlierService == "" || earlierService == null) {
        alert("Please select Weather any other Service availed or not .");
        return false;
    } else if (!declerationUserChecked) {
       // alert(declerationUser);
        alert("Please Select Declaration");
        return false;
    } else if (aadhaar_doc != null && aadhaar_doc != "" && !hasExtension('aadhaar_doc', ['.jpg', '.jpeg', '.png', '.pdf'])) {
        alert("Only PDF and Images are allowed (User Aadhaar Card).");
        return false;
    } else if (officeCardDoc != null && officeCardDoc != "" && !hasExtension('officeCardDoc', ['.jpg', '.jpeg', '.png', '.pdf'])) {
        alert("Only PDF and Images are allowed (Any Official Document Including ID Card).");
        return false;
    } else if (medicalDoc != null && medicalDoc != "" && !hasExtension('medicalDoc', ['.jpg', '.jpeg', '.png', '.pdf'])) {
        alert("Only PDF and Images are allowed (Any Medical Document authorised by any Medical Officer).");
        return false;
    } else if (otherDoc != null && otherDoc != "" && !hasExtension('otherDoc', ['.jpg', '.jpeg', '.png', '.pdf'])) {
        alert("Only PDF and Images are allowed (Any Other Document).");
        return false;
    } else return true;
}


function submit_form() {
    //alert("are we haere");
    var submitData = validateFields();

    if (!submitData) return false;

}

function hide_unhide_docsandComments(value) {
    // alert(value);
    if (value == "5") {
        hide_ctrl('official_div~medical_div', true);
        unhide_ctrl('aadhaar_div~other_div~comments', true);
        officeCardDoc.value = "";
        medicalDoc.value = "";
        otherDoc.value ="";
    } else if (value == "4") {
        hide_ctrl('official_div~other_div~comments', true);
        unhide_ctrl('aadhaar_div~medical_div', true);
        officeCardDoc.value = "";
        medicalDoc.value = "";
        otherDoc.value ="";
    } else if (value == "3") {
        hide_ctrl('medical_div~other_div~comments', true);
        unhide_ctrl('aadhaar_div~official_div', true);
         officeCardDoc.value = "";
         medicalDoc.value = "";
         otherDoc.value ="";
    } else if (value == "2" || value == "1") {
        hide_ctrl('medical_div~official_div~comments', true);
        unhide_ctrl('aadhaar_div~other_div', true);
         officeCardDoc.value = "";
         medicalDoc.value = "";
         otherDoc.value ="";
    }
}

function rowhideUnhide(value) {
    if (value != "Y") {
        hide_ctrl('tableDiv', true);

    } else {
        unhide_ctrl('tableDiv', true);
    }
}


function setFocus(element) {
    element.focus();
    element.scrollIntoView();
}

function alpha(e) {
    var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
}



function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}