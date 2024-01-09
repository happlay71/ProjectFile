import './assets/base.css'
import ElementPlus from 'element-plus'
// 这行代码导入 element-plus 库的 CSS 文件。
// 它是引入库的默认样式，通常用于确保组件正常渲染所需的样式被正确加载。
import 'element-plus/dist/index.css'
// 使一些组件或功能能够以中文（简体）的语言显示或按照中文的习惯进行渲染和处理
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(ElementPlus, {
    locale: zhCn,
})

app.use(router)

app.mount('#app')
