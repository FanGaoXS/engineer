import VueRouter from "vue-router";
import Vue from "vue";
Vue.use(VueRouter);

let routes=[
  {
    name: 'Upload',
    path: '/:deviceId',
    component: () => import('../views/upload/Upload2')
  }
];

let router = new VueRouter({
  routes,
  mode: 'history'
});

export default router;