
firstName = "";
email = "";
userId = "";

function suspendUser(userId,firstName,email) {
	this.firstName = firstName;
	this.email = email;
	this.userId = userId;
	$('#suspendConfirm').modal('show');
	

}

$(document).ready(function() {
	
	$('#suspendConfirm').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget)
		var modal = $(this)
		modal.find('.modal-title').text('Action Confirmation !')
		modal.find('.firstName').text("Name : "+firstName)
		modal.find('.email').text("Email : "+email)
	})
	
});
