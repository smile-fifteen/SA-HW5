// 修改按钮
function update(obj) {
    // 打开修改框架
    document.getElementById('updateBlock').style.display = 'block';
    document.getElementById('totalBackground').style.display = 'block';

    // 获取当前行
    var iTr = obj.parentNode.parentNode;

    // 获取当前行中的所有单元格
    iTds = iTr.getElementsByTagName('td');

    // 将新增框架中的输入框中内容设为当前行对应的内容
    document.getElementById('id_2').value = iTds[1].innerText;
    document.getElementById('name_2').value = iTds[2].innerText;
    if(iTds[3].innerText == '女')
    {
        document.getElementById('girl_2').checked = 'checked';
    }
    else
    {
        document.getElementById('boy_2').checked = 'checked';
    }
    document.getElementById('birth_2').value = iTds[4].innerText;
    document.getElementById('native_place_2').value = iTds[5].innerText;
    document.getElementById('department_2').value = iTds[6].innerText;
}

// 保存按钮
function preservation() {
    // 将新内容写入
    var id = document.getElementById('id_2').value;
    var name = document.getElementById('name_2').value;

    var c = document.getElementsByName('sex_2');
    var sex;
    if(c.item(0).checked)sex = "女";
    else sex = "男";

    var birth = document.getElementById('birth_2').value;
    var np = document.getElementById('native_place_2').value;
    var department = document.getElementById('department_2').value;
    iTds[2].innerText = document.getElementById('name_2').value;

    iTds[3].innerText = sex;

    iTds[4].innerText = document.getElementById('birth_2').value;
    iTds[5].innerText = document.getElementById('native_place_2').value;
    iTds[6].innerText = document.getElementById('department_2').value;
    var xmlhttp = new XMLHttpRequest();
    // xmlhttp.open("GET","/update?id="+id+"&name="+name+"&sex="+sex+"&birth="+birth+"&nativePlace="+np+"&department="+department
    //     ,false);
    // xmlhttp.send();
    var json = {"id":id,"name":name,"sex":sex,"birth":birth,"nativePlace":np,"department":department };
    // $.post("student",json,function (data) {
    //    alert("Rest create data: " + data);
    // });
    xmlhttp.open("PUT", "/student/"+id,false);
    xmlhttp.setRequestHeader("Content-type","application/json");
    xmlhttp.send(JSON.stringify(json));
    alert(xmlhttp.status + "\n" + xmlhttp.responseText);


    // 关闭修改框架
    document.getElementById('updateBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}

// 修改中的取消按钮
function updateCancel() {
    // 关闭修改框架
    document.getElementById('updateBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}
