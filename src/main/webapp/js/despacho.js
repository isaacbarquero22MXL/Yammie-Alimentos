
function showTime(){
    myDate = new Date();
    hours = myDate.getHours();
    minutes = myDate.getMinutes();
    seconds = myDate.getSeconds();
    if (hours < 10) hours = 0 + hours;
    if (minutes < 10) minutes = "0" + minutes;
    if (seconds < 10) seconds = "0" + seconds;
    var fecha = "<p>Fecha: " + myDate.getDate() + "/" + (myDate.getMonth() + 1) + "/"+myDate.getFullYear() + "</p>";
    fecha += "<p>Hora: " + hours + ":" + minutes + ":" + seconds + "</p>"
    time.innerHTML = fecha;
    setTimeout("showTime()", 1000); 
}