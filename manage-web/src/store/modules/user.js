import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(), //token（存在cookies中）
    name: '',          //名字
    avatar: ''         //头像
  }
}

const state = getDefaultState()

const mutations = {
  // 重置STATE
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  // 设置token
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  // 设置name
  SET_NAME: (state, name) => {
    state.name = name
  },
  // 设置头像
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // 用户登录
  login({ commit }, loginInfo) {
    const { username, password, validityDay } = loginInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password, validityDay: validityDay}).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token) //将token放入store的state中
        setToken(data.token) //将token存入cookies
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获得用户信息（用户每一次上次的时候都需要提交token并且验证）
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) { //没有data
          return reject('认证失败，请重新登录！')
        }
        const { name, avatar } = data
        commit('SET_NAME', name) //将name放入store的state中
        commit('SET_AVATAR', avatar) //将avatar放入store的state中
        console.log(state.name,'登录')
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 用户退出
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken()   // 移除cookies中的token（第一步必须这么做！！！）
        resetRouter()   // 重置路由
        console.log(state.name,'退出')
        commit('RESET_STATE') //重置store的state
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 移除token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

