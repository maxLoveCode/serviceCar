function formatDate(value,row){
	if(value== null)
		return "";
	var date = new Date(value);

	//return $.fn.datebox.defaults.formatter(d);
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    var HH = date.getHours();
    var mm = date.getMinutes();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+'  '+HH+':'+mm;
}