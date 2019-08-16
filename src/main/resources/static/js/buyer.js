function follow(sellerId, buyerId) {
    event.preventDefault();

    $.ajax({
        type: 'PUT',
        url: '/buyer/follow-seller/' + sellerId + '/' + buyerId,
        success: function(category){
            console.log("Successfully followed");
            $("#follow_" + sellerId).empty();
            $("#follow_" + sellerId).append('<button class=\"btn btn-info btn-width\" th:data-sellerId=\"' + sellerId + '" th:data-buyerId=\"' + buyerId + '\" onclick=\"unfollow(this.getAttribute(\'data-sellerId\'))\">Following</button>');
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
	console.log(sellerId);
}

function unfollow(sellerId, buyerId) {
    event.preventDefault();

    $.ajax({
        type: 'PUT',
        url: '/buyer/unfollow-seller/' + sellerId + '/' + buyerId,
        success: function(category){
            console.log("Successfully unfollowed");
            $("#follow_" + sellerId).empty();
            $("#follow_" + sellerId).append('<button class=\"btn btn-outline-primary btn-width\" th:data-sellerId=\"' + sellerId + '" th:data-buyerId=\"' + buyerId + '\" onclick=\"follow(this.getAttribute(\'data-sellerId\'))\">Follow</button>');
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
	console.log(sellerId);
}

function deleteAddress(addressId) {
    event.preventDefault();

    $.ajax({
        type: 'DELETE',
        url: '/buyer/delete-address/' + addressId,
        success: function(category){
            console.log("Successfully deleted");
            $("#add_" + addressId).remove();
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}

function editAddress(addressId) {
	window.location.href = '/buyer/address/edit/' + addressId;
}

function openAddressForm() {
	window.location.href = '/buyer/address/add';
}

function openCardForm() {
	window.location.href = '/buyer/payment/add';
}

function deleteCard(cardId) {
    event.preventDefault();

    $.ajax({
        type: 'DELETE',
        url: '/buyer/delete-card/' + cardId,
        success: function(category){
            console.log("Successfully deleted");
            $("#card_" + cardId).remove();
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}

function shopping(sellerId) {
	window.location.href = '/buyer/happy-shopping/' + sellerId;
}

function addToCart(productId) {
    event.preventDefault();
    
	var quantity = $("#pq_" + productId).val();

    $.ajax({
        type: 'POST',
        url: '/buyer/shopping-cart/' + productId + '/' + quantity,
        success: function(category){
            console.log("Successfully added to cart");
            $("#oper_" + productId).empty();
            $("#oper_" + productId).append('<span class=\"text-success\">Added</span>');
            $("#pq_" + productId).empty();
            $("#pq_" + productId).append("<span class=\"text-dark\">" + quantity + "</span>");
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}

function removeItem(cartItemId) {

    event.preventDefault();
    
    $.ajax({
        type: 'DELETE',
        url: '/buyer/shopping-cart/remove-item/' + cartItemId,
        success: function(category){
            console.log("Successfully removed from cart");
            $("#ci_" + cartItemId).remove();
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}

function placeOrder() {

	var shippingAddressId = $("#selectedShippingAddress").val();
	var creditCardId = $("#selectedCreditCard").val();
	if(shippingAddressId == 0 || creditCardId == 0) {
		alert("Choose shipping address and credit card");
		return;
	}
	
	event.preventDefault();
    
    $.ajax({
        type: 'POST',
        url: '/buyer/order/' + shippingAddressId + '/' + creditCardId,
        success: function(category){
            alert("Successfully ordered");
            window.location.href = '/buyer/shopping-cart';
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}









