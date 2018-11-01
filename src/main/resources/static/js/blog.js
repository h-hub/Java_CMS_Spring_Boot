$(document).ready(function() {

	$('#content').summernote({
		height : 300, // set editor height
		minHeight : null, // set minimum height of editor
		maxHeight : null
	// set maximum height of editor
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

});

$('.datepicker').datepicker();