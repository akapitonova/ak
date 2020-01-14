$(document).ready(function($) {
    $('#check').on('click', function(e) {
    			var firstName = document.getElementById("firstName").value;
    			var lastName = document.getElementById("lastName").value;
    			var phone = document.getElementById("phone").value;
    			var address = document.getElementById("address").value;
    			if (isEmpty(firstName) || isEmpty(lastName) || isEmpty(phone) || isEmpty(address) ) {
    				alert("All fields should be filled!");
    				return false;
    			}
    			return true;
    		});

    		function isEmpty(str) {
                return (!str || 0 === str.length);
            };
});