<template>
  <div id='VideoUploader' class="example-simple" style="margin-top:75px">
    <h1 id="example-title" style="margin-left:120px;" class="example-title">Current Upload</h1>
    <div class="upload" style="margin-left:110px;" >
      <ul>
        <li style="height:92px;line-height:92px;clear:left;" v-for="(file, index) in files" :key="file.id">
          <span class="spanStyle" >{{InitialName(file,index)}}{{index}} - </span>
          <span class="spanStyle2" ><ImageUploader v-bind:AVnum=file.id></ImageUploader></span>
          <!-- <span>{{file.name}}</span> - -->
          <!-- <span ondblclick= "change(this)">{{file.name}}</span> -  -->
          <span class="spanStyle"> - </span>
          <span class="spanStyle" v-on:dblclick="change($event.currentTarget, file)" >{{names[index]}}</span>
          <span class="spanStyle"> - </span>
          <span class="spanStyle">{{Toformat(file.size)}} -</span>
          <span class="spanStyle" v-if="file.error">{{file.error}}</span>
          <span class="spanStyle" v-else-if="file.success">success</span>
          <span class="spanStyle" v-else-if="file.active">active</span>
          <span class="spanStyle" v-else-if="file.active">active</span>
          <span class="spanStyle" v-else></span>
        </li>
      </ul>
      <div class="Clear"></div>
      <div class="example-btn" style="margin-left:10px;">
        <file-upload
          class="btn btn-primary"
          post-action="http://localhost:8081/video"
          extensions="mp4"
          accept="video/mp4"
          input-id="father"
          :multiple="true"
          :size="0"
          v-model="files"
          @input-filter="inputFilter"
          @input-file="inputFile"
          ref="upload">
          <i class="fa fa-plus"></i>
          Select files
        </file-upload>
        <button type="button" style="margin-left:10px;" class="btn btn-success" v-if="!$refs.upload || !$refs.upload.active" @click.prevent="$refs.upload.active = true">
          <i class="fa fa-arrow-up" aria-hidden="true"></i>
          Start Upload
        </button>
        <button type="button" style="margin-left:10px;" class="btn btn-danger"  v-else @click.prevent="$refs.upload.active = false">
          <i class="fa fa-stop" aria-hidden="true"></i>
          Stop Upload
        </button>
      </div>
    </div>
  </div>
</template>


<style>
.example-simple label.btn {
  margin-bottom: 0;
  margin-right: 1rem;
}

.spanStyle
{
  /* white-space: nowrap; */
  float: left;
  height: 92px;
  line-height: 92px;
}
.spanStyle2
{
  /* white-space: nowrap; */
  float: left;
}
.Clear
{
  clear: both;
}
</style>

<script>
import FileUpload from 'vue-upload-component'
import ImageUploader from './ImageUploader'
import axios from 'axios'
import GetAV from '../../public/js/fileAV'
var re='';
export default {
  name:"VideoUploader",
  components: {
    FileUpload,
    ImageUploader,
  },
  data() {
    return {
      files: [],
      names: [],
      ok: [],
    }
  },
  methods: {
    change(element,file)
    {
      if(file.error || file.success || file.active) return;
      var oldhtml = element.innerHTML;
        //创建新的input元素
        var newobj = document.createElement('input');
        //为新增元素添加类型
        newobj.type = 'text';
        //为新增元素添加value值
        newobj.value = oldhtml;
        //为新增元素添加光标离开事件
        newobj.onblur = function() {
            //当触发时判断新增元素值是否为空，为空则不修改，并返回原有值 
            element.innerHTML = this.value == oldhtml ? oldhtml : this.value;
            if(element.innerHTML =='')
            {
                element.innerHTML = oldhtml;
            }
            file.name = element.innerHTML;
            var str = file.name.split('.');
            if(str[str.length-1] != 'mp4')
            {
              file.name+='.mp4';
            }
            file.name = file.id+'.'+file.name;
            //当触发时设置父节点的双击事件为ShowElement、也可在这里进行保存动作
            element.setAttribute("ondblclick", "change(this);");
        }
        //设置该标签的子节点为空
        element.innerHTML = '';
        //添加该标签的子节点，input对象
        element.appendChild(newobj);
        //设置选择文本的内容或设置光标位置（两个参数：start,end；start为开始位置，end为结束位置；如果开始位置和结束位置相同则就是光标位置）
        newobj.setSelectionRange(0, oldhtml.length);
        //设置获得光标
        newobj.focus();
        //设置父节点的双击事件为空
        newobj.parentNode.setAttribute("ondblclick", "");
    },
    // getAV(file)
    // {
    //     var xmlhttp = new XMLHttpRequest();
    //     xmlhttp.open("GET", "http://localhost:8081/getAV?id="+file.id,false);
    //     xmlhttp.send();
    //     var re = xmlhttp.responseText;
    //     return re;
    // },
    InitialName(file,index)
    {
        if(this.ok[index] != null) return;
        this.ok[index] = 'ready';
        this.names[index] = file.name;
        file.name = file.id + '.' + file.name;
    },
    Toformat(size)
    {
      if(size < 1024) return size + " Bytes";
      else if(size < 1024*1024) return (size/1024).toFixed(2) + " KB";
      else if(size < 1024*1024*1024) return (size/(1024*1024)).toFixed(2)+" MB";
    },
    inputFilter(newFile, oldFile, prevent) {
      if (newFile && !oldFile) {
        // Before adding a file
        // 添加文件前
        // Filter system files or hide files
        // 过滤系统文件 和隐藏文件
        if (/(\/|^)(Thumbs\.db|desktop\.ini|\..+)$/.test(newFile.name)) {
          return prevent()
        }
        // Filter php html js file
        // 过滤 php html js 文件
        if (/\.(php5?|html?|jsx?)$/i.test(newFile.name)) {
          return prevent()
        }
      }
    },
    inputFile(newFile, oldFile) {
      if (newFile && !oldFile) {
        // add
        console.log('add', newFile)
      }
      if (newFile && oldFile) {
        // update
        console.log('update', newFile)
      }
      if (!newFile && oldFile) {
        // remove
        console.log('remove', oldFile)
      }
    }
  }
}
</script>
