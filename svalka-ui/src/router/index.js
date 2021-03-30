import Vue from 'vue'
import Router from 'vue-router'
import RegisterTab from '@/views/Register'
import Home from '@/views/Home'
// import UserView from '@/views/UserView';

Vue.use(Router)

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/register',
      name: 'Register',
      component: RegisterTab
    },
    {
      path: '/profile',
      name: 'Profile',
      component: () => import('../views/Profile.vue')
    },
    {
      path: '/try',
      name: 'Try',
      component: () => import('../views/Try.vue')
    },
    {
      path: '/userView',
      name: 'UserView',
      component: () => import(/* webpackChunkName: "about" */ '../views/UserView.vue')
    },
    {
      path: '/about',
      name: 'About',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    }
  ]
})

// router.beforeEach((to, from, next) => {
//   const publicPages = ['/home', '/register', '/']
//   const authRequired = !publicPages.includes(to.path)
//   const loggedIn = localStorage.getItem('user')
//
//   // trying to access a restricted page + not logged in
//   // redirect to login page
//   if (authRequired && !loggedIn) {
//     next('/')
//   } else {
//     next()
//   }
// })
// export default router
