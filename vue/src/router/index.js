import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', redirect: '/manager/home'},
    {
      path: '/manager',
      component: import('../views/Manager.vue'),
      children: [
        {path: 'home', component: () => import('../views/Home.vue')},
        {path: 'about', component: import('../views/about.vue')}
      ]
    },
    {
      path: '/about',
      component: import('../views/about.vue')
    },
    {
      path: '/notfound',
      component: import('../views/404.vue')
    },
    {
      path: '/:pathMatch(.*)',
      redirect: '/notfound'
    }
  ],
})

export default router
