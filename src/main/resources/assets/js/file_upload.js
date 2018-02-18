$(document).ready(function(){
    $('#selusers').select2();
    $('#btnAddFields').on('click',function(e){
        $('#file-group-container').clone().find("input:file").val("").end().appendTo("#files-container");
    });
});