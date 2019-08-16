function approveSeller(sellerId) {
    event.preventDefault();

    $.ajax({
        type: 'PUT',
        url: '/admin/approve-seller/' + sellerId,
        success: function(category){
            console.log("Successfully approved");
            $("#" + sellerId).empty();
            $("#" + sellerId).append('<div class="text-success">Approved</div>');
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}

function approveReview(reviewId) {
    event.preventDefault();

    $.ajax({
        type: 'PUT',
        url: '/admin/approve-review/' + reviewId,
        success: function(category){
            console.log("Successfully approved");
            $("#" + reviewId).empty();
            $("#" + reviewId).append('<div class="text-success">Approved</div>');
        },
        error: function (xmlResponse) {
            console.log("Failed");
        }

    });
}