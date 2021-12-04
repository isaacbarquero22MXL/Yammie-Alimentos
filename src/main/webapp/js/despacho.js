
function showTime(){
    var date = document.querySelectorAll('.date')[0];
    var dateTime = document.querySelectorAll('.dateTime')[0];
    var inputDate = document.querySelectorAll('.inputDate')[0];
    var inputDateTime = document.querySelectorAll('.inputDateTime')[0];
    myDate = new Date();
    hours = myDate.getHours();
    minutes = myDate.getMinutes();
    seconds = myDate.getSeconds();
    if (hours < 10) hours = 0 + hours;
    if (minutes < 10) minutes = "0" + minutes;
    if (seconds < 10) seconds = "0" + seconds;
    var fecha = "Fecha: " + myDate.getDate() + "/" + (myDate.getMonth() + 1) + "/"+myDate.getFullYear();
    var hora  = "Hora: " + hours + ":" + minutes + ":" + seconds;
    inputDate.value = fecha;
    inputDateTime.value = hora;
    date.innerHTML = fecha;
    dateTime.innerHTML = hora;
    setTimeout("showTime()", 1000); 
}