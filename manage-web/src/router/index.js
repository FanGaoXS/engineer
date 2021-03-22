import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: {title: '主面板', icon: 'dashboard'}
    }]
  },

  { //工程监理
    path: '/supervisor',
    component: Layout,
    redirect: '/supervisor/vehicle',
    name: 'Supervisor',
    meta: {title: '工程监理', icon: 'el-icon-s-order'},
    alwaysShow: true,
    children: [

      { //车辆监理
        path: 'vehicle',
        name: 'Vehicle',
        // alwaysShow: true,
        meta: {title: '车辆监理',},
        component: () => import('@/views/supervisor/vehicle/index'),
      },

      /*{ //机械监理
        path: 'machine',
        name: 'Machine',
        meta: {title: '机械监理（待开发）',},
        component: () => import('@/views/supervisor/machine/index')
      },*/

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

  { //工程用具
    path: '/engineer',
    component: Layout,
    redirect: '/car/vehicleList',
    name: 'Engineer',
    meta: {title: '工程用具', icon: 'el-icon-truck'},
    alwaysShow: true,
    children: [

      { //车辆列表
        path: 'vehicleList',
        name: 'VehicleList',
        // alwaysShow: true,
        meta: { title: '车辆列表',},
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

  { //类型管理
    path: '/model',
    component: Layout,
    redirect: '/model/vehicle',
    name: 'Model',
    meta: { title: '类型管理', icon: 'el-icon-s-data'},
    children: [

      { //车辆类型
        path: 'vehicle',
        name: 'VehicleModel',
        component: () => import('@/views/model/vehicle/index'),
        meta: { title: '车辆类型',}
      },

      { //机械类型
        path: 'machine',
        name: 'MachineModel',
        component: () => import('@/views/model/machine/index'),
        meta: { title: '机械类型',}
      }
    ]
  },

  {
    path: '/logger',
    component: Layout,
    redirect: '/logger/operation',
    // name: 'Logger',
    // meta: { title: '日志管理', icon: 'el-icon-tickets'},
    children: [
      {
        name: 'Operation',
        path: 'operation',
        component: () => import('@/views/logger/operation/index'),
        meta: { title: '操作日志', icon: 'el-icon-tickets'}
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

  /*{//测试
    path: '/test',
    component: Layout,
    name: 'Test',
    redirect: '/test/test1',
    meta: { title: '功能测试', icon: 'el-icon-setting' },
    alwaysShow: true,
    children: [
      {//测试1
        path: 'test1',
        name: 'Test1',
        component: () => import('@/views/test/test1/index'),
        meta: { title: '测试1'}
      },
      {
        path: 'page2',
        name: 'Page2',
        component: () => import('@/views/test/page2/index'),
        meta: { title: '页面2'}
      },
      {
        path: 'page3/:name/:age/:object',
        name: 'Page3',
        hidden: true,
        component: () => import('@/views/test/page3/index'),
        meta: {
          title: '页面3',
          activeMenu: '/test/page2'
        }
      }
    ]
  },*/



  /*{
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        name: 'Menu2',
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },*/

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
