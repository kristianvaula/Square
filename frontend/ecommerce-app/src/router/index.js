import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomePage.vue'
import CreateListingPage from '@/views/CreateListingPage.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/create-listing',
    name: 'createListing',
    component: CreateListingPage
  },
  {
    path: '/messages',
    name: 'messages',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: HomeView
  },
  {
    path: '/profile-page',
    name: 'profilePage',
    component: HomeView
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
