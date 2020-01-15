$(document).ready(function($) {
    $('.removeButton').on('click', function(e) {
        e.preventDefault();
		var $cartItemId = $(this).attr( "id");

            $.ajax({
                    type : 'POST',
                    data : {
                           cartItemId : $cartItemId
                     },
                     url : '/cart/removeCartItem',
                     success: function(data) {
                         location.reload();
                     }
             });
    });
});
