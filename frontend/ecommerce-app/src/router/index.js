import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomePage.vue'
import ProfilePage from '../views/ProfilePage.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginPage.vue')
  },
  {
    path: '/register-profile',
    name: 'registerProfile',
    component: () => import('../views/RegisterProfilePage.vue')
  },
  {
    path: '/create-listing',
    name: 'createListing',
    component: () => import('../views/CreateListingPage.vue')
  },
  {
    path: '/messages',
    name: 'messages',
    component: HomeView
  },

  {
    path: '/profile-page',
    name: 'profilePage',
    component: ProfilePage
  },
  {
    path: '/favorites',
    name: 'favorites',
    component: HomeView
  },
  {
    path: '/my-listings',
    name: 'myListings',
    component: HomeView
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
