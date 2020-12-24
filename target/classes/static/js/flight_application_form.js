
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
            if(document.getElementById('flightHelipadNameToGoFrom') != null && document.getElementById('flightHelipadNameToGoFrom').value == data.RESPONSE[i].helipadId ){
                            selectRole.append("<option selected value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                        }else {
                            selectRole.append("<option value=" + data.RESPONSE[i].helipadId + " >" + data.RESPONSE[i].helipadName + "</option>")
                        }

            };

        },
        error: function(data) {
            console.log(data)
        }

    });


}
