const getters = {
  sidebar: state => state.app.sidebar, //侧边栏是否打开
  device: state => state.app.device,  //设备名
  token: state => state.user.token,   //用户token（JWT）
  avatar: state => state.user.avatar, //用户头像
  name: state => state.user.name,     //用户姓名
  id: state => state.user.id,
  role: state => state.user.role
}
export default getters
