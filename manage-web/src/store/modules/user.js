import { login, logout } from '@/api/user/auth'
import { getInfo } from "@/api/user/profile";
import { getToken, setToken, removeToken } from '@/utils/auth'
import router , { resetRouter } from '@/router/index'

const getDefaultState = () => {
  return {
    token: getToken(), //token（存在cookies中）
    id: undefined,
    username: '',
    roles: [],
    name: '',          //名字
    avatar: ''         //头像
  }
}

//每次用户进入该项目都会执行该方法（从cookies中获得token）
const state = getDefaultState()

//同步方法
const mutations = {
  // 重置STATE
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  // 设置id
  SET_ID: (state, id) => {
    state.id = id
  },
  // 设置角色
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_USERNAME: (state, username) =>{
    state.username = username
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

//异步方法
const actions = {
  // 用户登录
  login({ commit }, loginInfo) {
    const { username, password, validityDay } = loginInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password, validityDay: validityDay}).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token) //将token放入store的state中
        setToken(data.token,validityDay) //将token存入cookies
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获得用户信息（用户每一次上线的时候都需要提交token并且验证）
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) { //没有data
          return reject('认证失败，请重新登录！')
        }
        const { name, avatar,id, role, username } = data
        commit('SET_ID', id) //将id放入store的state中
        commit('SET_NAME', name) //将name放入store的state中
        commit('SET_AVATAR', avatar) //将avatar放入store的state中
        commit('SET_ROLES', [ role.name ]) //将role放入store的state中
        commit('SET_USERNAME', username)
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
        resolve(state.name)
        console.log(state.name,'退出')
        removeToken()   // 移除cookies中的token（第一步必须这么做！！！）
        resetRouter()   // 重置路由
        commit('RESET_STATE')
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 移除token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      resolve()
    })
  },

  async changeRoles({ commit, dispatch }, role) {
    const token = role + '-token'

    commit('SET_TOKEN', token)
    setToken(token)

    const { data } = await dispatch('getInfo')
    const roles = [ data.role.name ]
    resetRouter()
    // generate accessible routes map based on roles
    const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })
    // dynamically add accessible routes
    router.addRoutes(accessRoutes)

    // reset visited views and cached views
    // dispatch('tagsView/delAllViews', null, { root: true })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

