// 新增按钮
function add() {
    // 打开新增框架
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","/generateId",false);
    xmlhttp.send();
    var n = xmlhttp.responseText;
    document.getElementById('id_1').value = n;
    document.getElementById('addBlock').style.display = 'block';
    document.getElementById('totalBackground').style.display = 'block';
}

// 提交按钮
function sumbit() {
    // 关闭新增框架
    document.getElementById('addBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';

    // 写入表单
    // 获取表
    var iTable = document.getElementById('myTable');
    // 获取输入值
    var id = document.getElementById('id_1').value;
    var name = document.getElementById('name_1').value;

    var c = document.getElementsByName('sex_1');
    var sex;
    if(c.item(0).checked)sex = "女";
    else sex = "男";

    var birth = document.getElementById('birth_1').value;
    var np = document.getElementById('native_place_1').value;
    var department = document.getElementById('department_1').value;
	var nums = iTable.rows.length;
    // 创建一行tr
    var iTr = document.createElement('tr');
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","/update?id="+id+"&name="+name+"&sex="+sex+"&birth="+birth+"&nativePlace="+np+"&department="+department
        ,false);
    xmlhttp.send();

    // 隔行换色
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
    iTd2.appendChild(document.createTextNode(id));
    var iTd3 = document.createElement('td');
    iTd3.className = "col3";
    iTd3.appendChild(document.createTextNode(name));
    var iTd4 = document.createElement('td');
    iTd4.className = "col4";
    iTd4.appendChild(document.createTextNode(sex));
    var iTd5 = document.createElement('td');
    iTd5.className = "col5";
    iTd5.appendChild(document.createTextNode(birth));
    var iTd6 = document.createElement('td');
    iTd6.className = "col6";
    iTd6.appendChild(document.createTextNode(np));
    var iTd7 = document.createElement('td');
    iTd7.className = "col7";
    iTd7.appendChild(document.createTextNode(department));
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


    // 将新增框架中的输入框值初始化
    document.getElementById('id_1').value = null;
    document.getElementById('name_1').value = null;
    if(sex == '男')
    {
        document.getElementById('girl').checked = 'checked';
    }
    document.getElementById('birth_1').value = null;
    document.getElementById('native_place_1').value = null;
    document.getElementById('department_1').value = null;


    var pageNum = document.getElementById('pageNum').innerText;
    pageNum = parseInt(pageNum);
    for (var i=10*pageNum+1; i<=nums; i++) {
        iTable.rows[i].style.display = 'none';
    }
    if(nums < 10)
    {
        document.getElementById('nums').innerText = nums;
    }
    else
    {
        document.getElementById('nums').innerText = "10";
    }
}

// 新增中的取消按钮
function addCancel() {
    // 关闭新增框架
    var xmlhttp = new XMLHttpRequest();
    var id = document.getElementById('id_1').value;
    xmlhttp.open("GET","/delete?id="+ id,false);
    xmlhttp.send();
    document.getElementById('addBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}

