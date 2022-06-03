const getters = {
  preUrl: state => state.app.preUrl,

  // user info
  token: state => state.user.token,
  userId: state => state.user.userId,
  username: state => state.user.username,
  studentClass: state => state.user.studentClass,
  avatar: state => state.user.avatar,
  logged: state => state.user.logged,
  needLogin: state => state.user.needLogin
}

export default getters
