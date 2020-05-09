function initial()
{
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
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "/search?condition=111111&key=",false);
    xmlhttp.send();

    var Table = xmlhttp.responseText.split(" ");
    for(var i = 0; i < Table.length; i++)
    {
        if(Table[i] != "") showone(Table[i],i);
    }
}
function showone(str,i) {
    var cell = str.split(",");
    // 获取表
    var iTable = document.getElementById('myTable');
    // 创建一行tr
    var iTr = document.createElement('tr');
    // 隔行换色
    var nums = iTable.rows.length;
    if (nums % 2 != 0)
    {
        iTr.className = 'mainTbodyTr1';
    }
    else {
        iTr.className = 'mainTbodyTr2';
    }

    // 将tr添加到table中
    iTable.appendChild(iTr);

    // 创建选择按钮
    var sel = document.createElement('input');
    sel.setAttribute('type','checkbox');
    sel.setAttribute('name','item');

    // 创建单元格td，并添加属性、内容
    var iTd1 = document.createElement('td');
    iTd1.className = "col1";
    iTd1.appendChild(sel);
    var iTd2 = document.createElement('td');
    iTd2.className = "col2";
    iTd2.appendChild(document.createTextNode(cell[0]));
    var iTd3 = document.createElement('td');
    iTd3.className = "col3";
    iTd3.appendChild(document.createTextNode(cell[1]));
    var iTd4 = document.createElement('td');
    iTd4.className = "col4";
    iTd4.appendChild(document.createTextNode(cell[2]));
    var iTd5 = document.createElement('td');
    iTd5.className = "col5";
    iTd5.appendChild(document.createTextNode(cell[3]));
    var iTd6 = document.createElement('td');
    iTd6.className = "col6";
    iTd6.appendChild(document.createTextNode(cell[4]));
    var iTd7 = document.createElement('td');
    iTd7.className = "col7";
    iTd7.appendChild(document.createTextNode(cell[5]));
    var iTd8 = document.createElement('td');
    iTd8.className = "col8";

    var examine = document.createElement('input');
    examine.id = 'examine';
    examine.setAttribute('type','button');
    examine.setAttribute('value','查看');
    examine.setAttribute('onclick','examine(this)');
    var update = document.createElement('input');
    update.id = 'update';
    update.setAttribute('type','button');
    update.setAttribute('value','修改');
    update.setAttribute('onclick','update(this)');
    iTd8.appendChild(examine);
    iTd8.appendChild(update);

    // 将单元格添加到行
    iTr.appendChild(iTd1);
    iTr.appendChild(iTd2);
    iTr.appendChild(iTd3);
    iTr.appendChild(iTd4);
    iTr.appendChild(iTd5);
    iTr.appendChild(iTd6);
    iTr.appendChild(iTd7);
    iTr.appendChild(iTd8);

    if(i < 10)
    {
        iTr.style.display = 'table-row';
        document.getElementById('nums').innerText = i+1;
    }
    else
    {
        iTr.style.display = 'none';
    }
}