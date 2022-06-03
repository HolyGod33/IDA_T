import { setToken, getToken, removeToken, getUserKey, setUserKey } from '@/utils/auth'
import { login } from '@/api/user'

const state = {
  token: getToken(),
  userId: '',
  username: '',
  studentClass: '',
  avatar: '',
  logged: !!getToken(),
  needLogin: false
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_USER_ID: (state, userId) => {
    state.userId = userId
  },
  SET_USERNAME: (state, username) => {
    state.username = username
  },
  SET_STUDENT_CLASS: (state, studentClass) => {
    state.studentClass = studentClass
  },
  SET_AVATAR: (state, avatar) => {
    if (!avatar || avatar === 'default') {
      state.avatar = 'https://avatarcdn.aminer.cn/default/default.jpg!240'
    } else {
      state.avatar = avatar
    }
  },
  SET_Logged: (state, logged) => {
    state.logged = logged
  },
  SWITCH_STATUS: (state) => {
    state.needLogin = !state.needLogin
  }
}

const actions = {
  // user login
  login ({
    commit,
    dispatch
  }, loginForm) {
    return new Promise((resolve, reject) => {
      login(loginForm).then(response => {
        setToken(response.data.token)
        setUserKey(response.data.user.studentId)
        localStorage.setItem('studentName', response.data.user.studentName)
        localStorage.setItem('studentClass', response.data.user.studentClass)

        commit('SET_TOKEN', response.data.token)
        commit('SET_USER_ID', response.data.user.studentId)
        commit('SET_USERNAME', response.data.user.studentName)
        commit('SET_STUDENT_CLASS', response.data.user.studentClass)
        commit('SET_AVATAR', '')
        commit('SET_Logged', true)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  getLoginInfo ({ commit }) {
    const token = getToken()
    const userId = getUserKey()
    const username = localStorage.getItem('studentName')
    const studentClass = localStorage.getItem('studentClass')
    const avatar = localStorage.getItem('avatar')
    commit('SET_TOKEN', token)
    commit('SET_USER_ID', userId)
    commit('SET_USERNAME', username)
    commit('SET_STUDENT_CLASS', studentClass)
    commit('SET_AVATAR', avatar)
  },

  // user logout
  logout ({ dispatch }) {
    dispatch('resetToken')
  },

  // 清除登录信息
  resetToken ({
    commit,
    dispatch
  }) {
    commit('SET_TOKEN', '')
    commit('SET_Logged', false)
    removeToken()
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
