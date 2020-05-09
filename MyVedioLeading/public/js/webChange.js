function webChange1()
{
    document.getElementById("second").className = "divnormal";
    document.getElementById("first").className = "divnormalactive";
    document.getElementById("2").style.color="grey";
    document.getElementById("1").style.color ="white";
    document.getElementById("VideoUploader").style.display="block";
    document.getElementById("List").style.display="none";
    
}
function webChange2()
{
    document.getElementById("first").className = "divnormal";
    document.getElementById("second").className = "divnormalactive";
    document.getElementById("1").style.color ="grey";
    document.getElementById("2").style.color="white";
    document.getElementById("VideoUploader").style.display="none";
    document.getElementById("List").style.display="block";
    Initial();
}

function Over1()
{
    if(document.getElementById("first").className=="divnormalactive")return;
    document.getElementById("1").style.color="white";
}
function Out1()
{
    if(document.getElementById("first").className=="divnormalactive")return;
    document.getElementById("1").style.color="grey";
}

function Over2()
{
    if(document.getElementById("second").className=="divnormalactive")return;
    document.getElementById("2").style.color="white";
}
function Out2()
{
    if(document.getElementById("second").className=="divnormalactive")return;
    document.getElementById("2").style.color="grey";
}