import { createRouter, createWebHashHistory } from 'vue-router'
import { mount } from '@vue/test-utils'
import router from '@/router'
import HomeView from '@/views/HomePage.vue'
import LoginPage from '@/views/LoginPage.vue'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import CategoryPool from '@/components/CategoryPool.vue'
import RegisterProfilePage from '@/views/RegisterProfilePage.vue'
import CreateListingPage from '@/views/CreateListingPage.vue'
import MyMessages from '@/views/MyMessages.vue'
import ProfilePage from '@/views/ProfilePage.vue'
import EditProfilePage from '@/views/EditProfilePage.vue'
import MyListings from '@/views/MyListings.vue'
import NotLoggedInPage from '@/views/NotLoggedInPage.vue'

describe('Router tests', () => {
let wrapper
beforeEach(() => {
    const app = createApp({})
    const pinia = createPinia()
    app.use(router).use(pinia)
    wrapper = mount(HomeView, {
        global: {
        plugins: [pinia],
        },
    })
    })

  it('should navigate to home page', async () => {
    

    await router.push('/')
    await wrapper.vm.$nextTick()
    
    expect(wrapper.findComponent(CategoryPool).exists()).toBe(true);
    wrapper.unmount()
  })

  it(' Navigate to login page', async () => {
    await router.push('/login')
    await wrapper.vm.$nextTick()
    
    expect(wrapper.findComponent(LoginPage))
    wrapper.unmount()
  })

  it(' Navigate to RegisterProfilePage page', async () => {
    await router.push('/create-profile')
    await wrapper.vm.$nextTick()
    
    expect(wrapper.findComponent(RegisterProfilePage))
    wrapper.unmount()
  })

  it(' Navigate to RegisterProfilePage page', async () => {
    await router.push('/create-listing')
    await wrapper.vm.$nextTick()
    expect(wrapper.findComponent(CreateListingPage))
    wrapper.unmount()
  })

  it(' Navigate to MyMessages page', async () => {
    await router.push('/my-messages')
    await wrapper.vm.$nextTick()
    expect(wrapper.findComponent(MyMessages))
    wrapper.unmount()
  })

  it(' Navigate to profile page', async () => {
    await router.push('/my-profile')
    await wrapper.vm.$nextTick()
    expect(wrapper.findComponent(ProfilePage))
    wrapper.unmount()
  })

  it(' Navigate to not logged in page', async () => {
    await router.push('/not-logged-in')
    await wrapper.vm.$nextTick()
    expect(wrapper.findComponent(NotLoggedInPage))
    wrapper.unmount()
  })

  it('Navigate to my-listings page', async () => {
    await router.push('/my-listings')
    await wrapper.vm.$nextTick()
    expect(wrapper.findComponent(MyListings))
    wrapper.unmount()
  })
})
