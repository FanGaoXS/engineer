import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

let routes=[
  { //进入该系统的默认页
    path: '/',
    redirect: '/main'
  },
  {
    path: '/login',
    component: () => import('../views/login/Login')
  },
  {
    path: '/main',
    component: () => import('../views/main/Main'),
    children: [
      { //主页面的默认页
        path: '',
        redirect: 'developList'
      },
      { //开发人员列表
        path: 'developList',
        component: () => import('../views/user/develop/DevelopList')
      },
      { //新增开发人员
        path: 'developInsert',
        component: () => import('../views/user/develop/DevelopInsert')
      },
      { //车辆列表
        path: 'vehicleList',
        component: () => import('../views/car/vehicle/VehicleList')
      },
      { //机械列表
        path: 'machineList',
        component: () => import('../views/car/machine/MachineList')
      },
    ]
  }
]

const router=new VueRouter({
  routes,
  mode: 'history'
});

export default router;