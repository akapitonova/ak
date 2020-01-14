$(document).ready(function($) {
             $('#check').on('click', function(e) {
                     e.preventDefault();
         			var password = document.getElementById("pass").value;
         			var confirmpass = document.getElementById("confirmpass").value;
         			var username = document.getElementById("username").value;
         			var firstName = document.getElementById("firstName").value;
                     var lastName = document.getElementById("lastName").value;
                     var phone = document.getElementById("phone").value;
                     var address = document.getElementById("address").value;

         			if (isEmpty(username) ||
         			    isEmpty(password) ||
         			    isEmpty(confirmpass) ||
         			    isEmpty(firstName) ||
         			    isEmpty(lastName) ||
         			    isEmpty(phone) ||
         			    isEmpty(address)) {
                         				alert("All fields should be filled!");
                         				return false;
                         			}
         			if (password != confirmpass) {
         				alert("Password does Not Match.");
         				return false;
         			}
         			$.ajax({
                             type : 'POST',
                             data : {
                                 username : username
                             },
                             url : '/check_user',
                             success: function(data) {
                                 if (data != 'null') {
                                     alert(data);
                                     return false;
                                 } else {
                                     $('#user').submit();
                                 }
                             }
                      });
         		});

         		function isEmpty(str) {
                                 return (!str || 0 === str.length);
                             };
         });