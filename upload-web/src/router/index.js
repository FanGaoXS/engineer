import VueRouter from "vue-router";
import Vue from "vue";
Vue.use(VueRouter);

let routes=[
  {
    path: '/:deviceId',
    redirect: '/vehicle/:deviceId'
  },
  {
    name: 'Vehicle',
    path: '/vehicle/:deviceId',
    component: () => import('../views/upload/vehicle')
  },
  {
    name: 'Machine',
    path: '/machine/:deviceId',
    component: () => import('../views/upload/machine')
  }
];

let router = new VueRouter({
  routes,
  mode: 'history'
});

export default router;