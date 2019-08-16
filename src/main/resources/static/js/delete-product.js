function deleteProduct(productId) {
    event.preventDefault();

    $.ajax({
        type: 'DELETE',
        url: '/seller/product/delete/' + productId,
        success: function(category){
            console.log("Successfully deleted");
            $("#prod_" + productId).remove();
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}

function editProduct(productId) {
	window.location.href = '/seller/product/edit/' + productId;
}

function openProductForm() {
	window.location.href = '/seller/product/add';
}

