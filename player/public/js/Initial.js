var host="http://localhost:8083/";
var p = ["360P", "720P", "1080P"];
function initial()
{
    var pa = window.location.pathname.substr(1).split('&');
    var av = pa[0];
    var title = pa[1];
    var type = pa[2];
    var l= pa[3];
    i = l;
    cover = host+"picture/"+av+"."+type;
    document.getElementById("title").innerHTML = title;
    for(;l >= 0;l--)
    {
        level.push({"name":p[l],"url":host+"play?re="+p[l]+'&&name='+av+'.mp4'});
    }
    //level.push({"name":p[1],"url":host+"play?re="+p[1]+'&&name='+av+'.mp4'});
    //alert(level[0].url);
}