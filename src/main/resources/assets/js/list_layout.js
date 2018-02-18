$(document).ready(function(){
    var options = {
        valueNames: [ 'filterd-field']
    };
    var userList = new List('table-list-filter', options);

    $('.deletefrm').on('submit',function(e){
        if(!confirm("Would you like to remove the record ?")){
            e.preventDefault();
            return false;
        }
        return true;
    });
});