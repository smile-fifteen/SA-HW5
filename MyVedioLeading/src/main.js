import Vue from 'vue'
import VideoUploader from './components/VideoUploader'


Vue.config.productionTip = false

new Vue(
  {
    render: h => h(VideoUploader),
  }
).$mount('#VideoUploader')

