import Vue from 'vue'
import Router from "vue-router";
Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * 静态路由表（所有用户都可以访问）
 * @type {{}}
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },

  { //主面板
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: {
        title: '主面板', icon: 'dashboard'
      }
    }]
  },

  { //个人中心
    path: '/profile',
    component: Layout,
    redirect: '/profile/info',
    children: [
      {
        path: 'info',
        name: 'Info',
        component: () => import('@/views/profile/info/index'),
        meta: { title: '个人中心', icon: 'el-icon-user'}
      },
      {
        path: 'password',
        name: 'Password',
        component: () => import('@/views/profile/password/index'),
        meta: { title: '修改密码'},
        hidden: true
      }
    ]
  },

  { //相关下载
    path: '/download',
    component: Layout,
    redirect: '/download/app',
    name: 'Download',
    meta: { title: '相关下载', icon: 'el-icon-download'},
    alwaysShow: true,
    children: [
      {
        path: 'app',
        name: 'App',
        component: () => import('@/views/download/app/index'),
        meta: { title: '配置程序' }
      }
    ]
  },

  { //拓展功能
    path: '/expand',
    component: Layout,
    redirect: '/expand/speed',
    name: 'Expand',
    meta: { title: '拓展功能', icon: 'el-icon-odometer' },
    alwaysShow: true,
    children: [
      {
        path: 'speed',
        name: 'Speed',
        component: () => import('@/views/expand/speed/index'),
        meta: { title: '网络测速'}
      }
    ]
  },
]

/**
 * 异步路由表（根据用户权限判断用户是否能够访问）
 * @type {{}}
 */
export const asyncRoutes = [

  { //操作日志（管理员、开发者可见）
    path: '/logger',
    component: Layout,
    redirect: '/logger/operation',
    name: 'Logger',
    meta: {
      title: '日志管理',
      icon: 'el-icon-tickets',
      roles: [ 'admin', 'developer', 'ghost' ]
    },
    alwaysShow: true,
    children: [
      {
        name: 'Operation',
        path: 'operation',
        component: () => import('@/views/logger/operation/index'),
        meta: {
          title: '操作日志',
          icon: 'el-icon-tickets'
        }
      }
    ]
  },

  { //系统权限（管理员、开发者可见）
    path: '/system',
    component: Layout,
    redirect: '/system/user',
    name: 'System',
    meta: {
      title: '系统权限',
      icon: 'el-icon-s-check',
      roles: [ 'admin', 'developer', 'ghost' ]
    },
    alwaysShow: true,
    children: [
      {
        name: 'Role',
        path: 'role',
        component: () => import('@/views/system/role/index'),
        meta: {
          title: '角色管理'
        }
      },
      {
        name: 'User',
        path: 'user',
        cache: false,
        component: () => import('@/views/system/user/index'),
        meta: {
          title: '用户管理'
        }
      }
    ]
  },


  { //工程用具（管理员、开发者、监理可见）
    path: '/engineer',
    component: Layout,
    redirect: '/car/vehicleList',
    name: 'Engineer',
    meta: {
      title: '工程用具',
      icon: 'el-icon-truck',
      roles: [ 'admin', 'developer', 'ghost', 'supervisor' ]
    },
    alwaysShow: true,
    children: [

      { //车辆列表
        path: 'vehicleList',
        name: 'VehicleList',
        // alwaysShow: true,
        meta: {
          title: '车辆列表',
        },
        component: () => import('@/views/engineer/vehicle/index'),
      },

      /*{ //机械列表
        path: 'machineList',
        name: 'MachineList',
        meta: { title: '机械列表（待开发）',},
        component: () => import('@/views/engineer/machine/index')
      },*/

    ]
  },

  { //类型管理（管理员、开发者、监理可见）
    path: '/model',
    component: Layout,
    redirect: '/model/vehicle',
    name: 'Model',
    meta: {
      title: '类型管理',
      icon: 'el-icon-s-data',
      roles: [ 'admin', 'developer', 'ghost', 'supervisor' ]
    },
    alwaysShow: true,
    children: [

      { //车辆类型
        path: 'vehicle',
        name: 'VehicleModel',
        component: () => import('@/views/model/vehicle/index'),
        meta: { title: '车辆类型'}
      },

      /*{ //机械类型
        path: 'machine',
        name: 'MachineModel',
        component: () => import('@/views/model/machine/index'),
        meta: { title: '机械类型',}
      }*/
    ]
  },

  { //工程监理（管理员、开发者、监理可见）
    path: '/supervisor',
    component: Layout,
    redirect: '/supervisor/vehicle',
    name: 'Supervisor',
    meta: {
      title: '工程监理',
      icon: 'el-icon-s-order',
      roles: [ 'admin', 'developer', 'ghost', 'supervisor' ]
    },
    alwaysShow: true,
    children: [

      { //车辆监理
        path: 'vehicle',
        name: 'Vehicle',
        // alwaysShow: true,
        meta: {title: '车辆监理',},
        component: () => import('@/views/supervisor/vehicle/index'),
      },

      { //工时列表（根据车牌号）
        hidden: true,
        path: 'work/:vehicleNumber',
        name: 'Work',
        meta: {
          title: '工时列表',
          activeMenu: '/supervisor/vehicle'
        },
        component: () => import('@/views/supervisor/work/index')
      },

      { //轨迹详情
        hidden: true,
        path: 'map/:vehicleNumber/:date/:mileage',
        name: 'Map',
        meta: {
          title: '轨迹详情',
          activeMenu: '/supervisor/vehicle'
        },
        component: () => import('@/views/supervisor/map/index')
      }

    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes,
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
