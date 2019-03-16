$(document).ready(function(){

    $("button[element='delete-student']").click(function(event){
        event.preventDefault();
        btn = $(this);
        bootbox.confirm("Are you sure to delete "+$(this).attr("student")+"?", function(result){
            if( result ){
                $("#frm"+btn.attr("id")).submit();
            }
        })
    });

    $("button[element='delete-result']").click(function(event){
        event.preventDefault();
        btn = $(this);
        bootbox.confirm("Are you sure to delete "+$(this).attr("module")+"?", function(result){
            if( result ){
                $("#frm-result"+btn.attr("id")).submit();
            }
        })
    });


});