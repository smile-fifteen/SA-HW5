var host = "http://localhost:8083/";

function getSize()
{
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", host+"getSize",false);
    xmlhttp.send();
    return xmlhttp.responseText;
}
function Initial()
{
    //alert(window.location.href);
    var n = getSize();
    for(var i = 0; i < n; i++)
    {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", host+"video/"+i,false);
        xmlhttp.send();
        var obj = JSON.parse(xmlhttp.responseText);
        if(!document.getElementById(obj.av))
        {
            generateVideoBlock(obj.av, obj.title,obj.image, obj.time,obj.level);
        }
    }
}

function generateVideoBlock(av,title, type,time,level)
{
    var x = document.getElementById("List");
    var picture = document.createElement("img");
        picture.style.width = "250px";
        picture.style.height = "165px";
        picture.style.marginLeft = "-12px";
        picture.style.marginTop = "-38px";
        picture.style.borderRadius="10px 10px 0px 0px";
        picture.style.border="0px";
    picture.src = host+"picture/"+av+"."+type;

    var play = document.createElement("img");
    play.src = "image/shipin2.png";
        play.style.marginLeft = "-160px";
        play.style.marginTop = "-40px";
        play.style.visibility = "hidden";
    play.id = av+"play";

    var t = document.createElement("a");
        t.style.height = "20px";
        t.style.backgroundColor = "rgba(50,50,50,0.5)";
        t.style.color="white";
        t.style.fontSize = "15px";
        t.style.position = "relative";
        t.style.left = "-158px";
        t.style.bottom = "-56px";
    t.innerText=time;

    var text = document.createElement("a");
        text.style.width = "250px";
        text.style.height = "60px";
        text.style.fontSize = "15px";
        text.style.fontFamily = "font-family: Arial, Helvetica, sans-serif;";
        text.style.fontWeight="500";
        text.style.whiteSpace="normal";
        text.style.wordBreak="break-all";
    text.innerHTML = title;

    var del = document.createElement("button");
        del.className = "btn btn-danger";
        del.style.textAlign="left";
        del.style.fontWeight="600";
        del.style.fontSize = "20px";
        del.style.height = "25px";
        del.style.width = "25px";
        del.style.marginLeft ="20px";
        del.style.marginTop = "-160px";
        del.style.visibility = "hidden";
    
    del.innerHTML = "<a style='position:relative;left:-6px;top:-8px;'>×</a>";
    del.id = av+"del";

    var button = document.createElement("button");
        button.className = "btn btn-default";
        button.style.width = "250px";
        button.style.height = "225px";
        button.style.borderRadius ="10px";
        button.style.marginLeft = "20px";
        button.style.marginTop = "30px";
        button.style.border = "0px";
        button.style.textAlign="left";
        
    button.id = av;

    button.appendChild(picture);
    button.appendChild(play);
    button.appendChild(t);
    button.appendChild(del);
    button.appendChild(document.createElement("br"));
    button.appendChild(text);
    x.appendChild(button);

    del.addEventListener("click", function(){deleteVideo(av)});
    button.addEventListener("mouseover", function() {Show(play.id,del.id);});
    button.addEventListener("mouseout", function() {UnShow(play.id,del.id);});
    button.addEventListener("click", function() {Open(av,title,type,level)});
}

function Show(str1,str2)
{
    document.getElementById(str1).style.visibility="visible";
    document.getElementById(str2).style.visibility="visible";
    //alert(str);
}
function UnShow(str1,str2)
{
    document.getElementById(str1).style.visibility="hidden";
    document.getElementById(str2).style.visibility="hidden";
        //alert(str);
}
var isdel = false;
function deleteVideo(av)
{
    isdel = true;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("DELETE", host+"video/"+av,false);
    xmlhttp.send();
    var v = document.getElementById(av);
    document.getElementById("List").removeChild(v);
}
function Open(av,title,type,level)
{
    if(isdel){isdel = false; return;}
    window.open("http://localhost:8084/"+av+"&"+title+"&"+type+"&"+level);
}