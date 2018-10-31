
firstName = "";
email = "";
userId = "";
status = "";
bodyText = "";

function suspendUser(userId,firstName,email) {
	this.firstName = firstName;
	this.email = email;
	this.userId = userId;
	this.status = false;
	this.bodyText = "Suspend user with:";
	$('#suspendConfirm').modal('show');
	

}

function activateUser(userId,firstName,email) {
	this.firstName = firstName;
	this.email = email;
	this.userId = userId;
	this.status = true;
	this.bodyText = "Activate user with:";
	$('#suspendConfirm').modal('show');
	

}

$(document).ready(function() {
	
	$('#suspendConfirm').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget)
		var modal = $(this)
		modal.find('.modal-title').text('Action Confirmation !')
		modal.find('.body-text').text(bodyText)
		modal.find('.firstName').text("Name : "+firstName)
		modal.find('.email').text("Email : "+email)
	});
	
	
	
	$('#modal-confirm-btn').click(function(event){
		//$.post( "/change_user_status", { userId: "John", status: "2pm" } );
	    event.preventDefault();
	    var newForm = jQuery('<form>', {
	        'action': '/change_user_status',
	        'method': 'POST'
	    }).append($('<input>', {
	        'name': 'userId',
	        'value': userId,
	        'type': 'hidden'
	    })).append($('<input>', {
	        'name': 'status',
	        'value': status,
	        'type': 'hidden'
	    }));
	    $(document.body).append(newForm);
	    newForm.submit();
	});
	
});
