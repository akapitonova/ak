$(document).ready(function($) {
    $('#pay').on('click', function(e) {
		var $total = document.getElementById("total").getAttribute('value');
		var $balance = document.getElementById("userBalance").getAttribute('value');

		if (parseFloat($total) > parseFloat($balance)) {
		    alert("You do not have enough funds in your account!");
		    return false;
		}
		return true;
	});
});