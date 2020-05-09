// 查看按钮
function examine(obj) {
    // 打开查看框架
    document.getElementById('examineBlock').style.display = 'block';
    document.getElementById('totalBackground').style.display = 'block';

    // 获取当前行
    var iTr = obj.parentNode.parentNode;

    // 获取当前行中的所有单元格
    var iTds = iTr.getElementsByTagName('td');

    // 将新增框架中的输入框中内容设为当前行对应的内容
    document.getElementById('id_3').value = iTds[1].innerText;
    document.getElementById('name_3').value = iTds[2].innerText;
    if(iTds[3].innerText == '女')
    {
        document.getElementById('girl_3').checked = 'checked';
    }
    else
    {
        document.getElementById('boy_3').checked = 'checked';
    }
    document.getElementById('birth_3').value = iTds[4].innerText;
    document.getElementById('native_place_3').value = iTds[5].innerText;
    document.getElementById('department_3').value = iTds[6].innerText;

}

// 查看中的取消按钮
function examineCancel() {
    // 关闭修改框架
    document.getElementById('examineBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}
