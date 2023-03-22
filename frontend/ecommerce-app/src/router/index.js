import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomePage.vue'
import ProfilePage from '../views/ProfilePage.vue'
import EditProfilePage from '@/views/EditProfilePage.vue'

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
    path: '/create-profile',
    name: 'registerProfile',
    component: () => import('../views/RegisterProfilePage.vue')
  },
  {
    path: '/create-listing',
    name: 'createListing',
    component: () => import('../views/CreateListingPage.vue')
  },
  {
    path: '/my-messages',
    name: 'myMessages',
    component: HomeView
  },

  {
    path: '/my-profile',
    name: 'myProfile',
    component: ProfilePage
  },
  {
    path: '/edit-profile',
    name: 'editProfile',
    component: EditProfilePage
  },
  {
    path: '/my-favorites',
    name: 'myFavorites',
    component: HomeView
  },
  {
    path: '/my-listings',
    name: 'myListings',
    component: HomeView
  },
  {
    path: '/admin',
    name: 'AdminCategoriesPage',
    component: () => import('../views/AdminCategoriesPage.vue')
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
