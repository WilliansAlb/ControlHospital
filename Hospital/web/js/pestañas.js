function mostrandoDiv(x){
    var z = document.getElementById('radio');
   	for (let i=0;i<document.fcolors.test.length;i++){ 
        if (document.fcolors.test[i].value == x){
            document.getElementById(x).style.display = 'block';
        } else {
            document.getElementById(document.fcolors.test[i].value).style.display = 'none';
        }
   	} 
}
window.onload = function() {
    document.getElementById($('input:radio[name=test]:checked').val()).style.display='block';
};