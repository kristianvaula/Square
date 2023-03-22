import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomePage.vue'
import ProfilePage from '../views/ProfilePage.vue'
import EditProfilePage from '@/views/EditProfilePage.vue'
import MyMessages from '@/views/MyMessages.vue';

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
    component: MyMessages
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
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
