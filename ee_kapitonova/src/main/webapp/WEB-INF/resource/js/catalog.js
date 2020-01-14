$(document).ready(function($) {
    $('button').on('click', function(e) {
        e.preventDefault();

		var $product = $(this);
		var $prodId = $(this).attr( "id");
		var $prodInputQty = $('input[name$="'+$prodId+'"]');
		var $prodQty = $prodInputQty.val();
		var $prodStoreQty = $prodInputQty.data("qty");

		if ($prodQty > $prodStoreQty || $prodQty == 0 || $prodQty == null) {
		    alert("Invalid item quantity. Quantity must be greatest that 0 and not exceed "+$prodStoreQty);
		    return false;
		} else {
                $.ajax({
                        type : 'POST',
                        dataType: "json",
                        data : {
                            productId : $prodId,
                            productQty : $prodQty
                        },
                        url : '/buy',
                        success: function(data) {
                                alert("Product added to cart");
                        }
                 });
            }
		});
		$('input[type="number"]').on('keyup focus', function(event) {
                let select = $(event.currentTarget);
                select.val(select.val().replace(/[^0-9]/gi,'').replace(/\s+/gi,', '));
        });
});