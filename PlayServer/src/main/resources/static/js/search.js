function search() {
    var iTable = document.getElementById('myTable');
    for(var i = 1; i < iTable.rows.length;i++)
    {
        iTable.rows[i].style.display = 'none';
    }
    var items = document.getElementsByName('item');
    for(var j=0;j<items.length;j++)
    {
        items[j].parentNode.parentNode.remove();
        j--;
    }
    document.getElementById('nums').innerText = 0;
    var condition = "";
    var key = document.getElementById('key').value;
    var con = document.getElementsByName('condition');
    for(var i = 0; i < 6; i++)
    {
        if(con[i].checked) condition+="1";
        else condition +="0";
    }

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "/search?condition="+condition+"&key="+key,false);
    xmlhttp.send();
    // alert(xmlhttp.responseText);
    var Table = xmlhttp.responseText.split(" ");
    for(var i = 0; i < Table.length; i++)
    {
        if(Table[i] != "") showone(Table[i],i);
    }
}