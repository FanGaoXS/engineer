import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters' //抽离出来的getters
import app from './modules/app' //全局变量、全局同步函数、全局异步函数
import settings from './modules/settings' //用户偏好设置（如headr是否保持固定、是否展示侧边栏logo）
import user from './modules/user' //用户的个人信息

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user,
  },
  getters
})

export default store
