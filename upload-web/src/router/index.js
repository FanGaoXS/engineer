import VueRouter from "vue-router";
import Vue from "vue";
// 懒加载upload组件
const Upload = () => import('../views/upload/Upload');
Vue.use(VueRouter);

let routes=[
  {
    path: '/:devId',
    component: Upload
  }
];

let router = new VueRouter({
  routes,
  mode: 'history'
});

export default router;