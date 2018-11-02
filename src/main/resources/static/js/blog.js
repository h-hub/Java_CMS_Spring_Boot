author = "";
title = "";
blogPostId = "";
approve = "";
bodyText = "";
pubToDate = "";

$(document).ready(function() {

	$('.datepicker').datepicker({
		startDate : new Date()
	});

	$('#content').summernote({
		height : 500, // set editor height
		minHeight : null, // set minimum height of editor
		maxHeight : null // set maximum height of editor
	});

	$('#preview-btn').click(function(event) {

		event.preventDefault();
		var blogPostForm = $('#blog-post-form');

		blogPostForm.append($('<input>', {
			'name' : 'preview',
			'value' : true,
			'type' : 'hidden'
		}));

		blogPostForm.submit();
	});

	$('#save-btn').click(function(event) {

		event.preventDefault();
		var blogPostForm = $('#blog-post-form');

		blogPostForm.append($('<input>', {
			'name' : 'save',
			'value' : true,
			'type' : 'hidden'
		}));

		blogPostForm.submit();
	});

	$('#blog-post-action-confirm').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget)
		var modal = $(this)
		modal.find('.body-text').text(bodyText)
		modal.find('.title').text("Title : " + title)
		modal.find('.author').text("Author : " + author)
	});
	
	$('#blog-post-action-confirm-btn').click(function(event){
		//$.post( "/change_user_status", { userId: "John", status: "2pm" } );
	    event.preventDefault();
	    var newForm = jQuery('<form>', {
	        'action': '/change_blog_post_status',
	        'method': 'POST'
	    }).append($('<input>', {
	        'name': 'blogPostId',
	        'value': blogPostId,
	        'type': 'hidden'
	    })).append($('<input>', {
	        'name': 'approve',
	        'value': approve,
	        'type': 'hidden'
	    })).append($('<input>', {
	        'name': 'pubToDate',
	        'value': pubToDate,
	        'type': 'hidden'
	    }));
	    $(document.body).append(newForm);
	    newForm.submit();
	});

});


function approvePost(blogPostId, title, author) {

	var date = $("input[name=pubToDateOf_" + blogPostId + "]").val();

	if (date === "") {
		$("input[name=pubToDateOf_" + blogPostId + "]").addClass( "border-30 border-danger" );
		return;
	}

	this.blogPostId = blogPostId;
	this.title = title;
	this.author = author;
	this.approve = true;
	this.pubToDate = date;
	this.bodyText = "Approve post with:";
	$('#blog-post-action-confirm').modal('show');
}

function rejectPost(blogPostId, title, author) {
	this.blogPostId = blogPostId;
	this.title = title;
	this.author = author;
	this.approve = false;
	this.bodyText = "Reject post with:";
	$('#blog-post-action-confirm').modal('show');
}