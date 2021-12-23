let editor;

window.onload= function(){
	editor = ace.edit("editor");
	editor.setTheme("ace/theme/monokai");
	editor.session.setMode("ace/mode/c_cpp");
}


function changeLanguage() {

	let language = $("#language").val();

	if(language == 'c' || language == 'cpp') editor.session.setMode("ace/mode/c_cpp");
	else if(language == 'java') editor.session.setMode("ace/mode/java");
	else if(language == 'php') editor.session.setMode("ace/mode/php");
	else if(language == 'python') editor.session.setMode("ace/mode/python");
	else if(language == 'javascript') editor.session.setMode("ace/mode/javascrip");
}

function executeCode() {

	$.ajax({

		url: "IDE/src/main/java/com/far/ExecutionServlet.java",
		method: "POST",

		data: {
			language: $("#languages").val(),
			code: editor.getSession().getValue()
		},
		success: function(response) {
			$(".output").text(response)
		}
	})
}

