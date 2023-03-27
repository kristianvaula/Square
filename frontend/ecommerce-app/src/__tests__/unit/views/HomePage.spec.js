import { mount } from '@vue/test-utils';
import HomePage from '@/views/HomePage.vue';
import ProductPool from '@/components/ProductPool.vue';
import CategoryPool from '@/components/CategoryPool.vue';
import SubCategories from '@/components/SubCategories.vue';
import CategoryUtils from '@/utils/CategoryUtils';
import ProductUtils from '@/utils/ProductUtils';
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from '@/router';


describe('HomePage tests', () => {
    let wrapper;
  
    beforeEach(() => {
      const app = createApp({})
      const pinia = createPinia()
      app.use(router).use(pinia)
      wrapper = mount(HomePage, {
        global: {
          plugins: [pinia],
        },
      })
    });
  
    it('should render the CategoryPool component', () => {
      expect(wrapper.findComponent(CategoryPool).exists()).toBe(true);
      wrapper.unmount();
    })

    it('should render the SubCategories component', () => {
        expect(wrapper.findComponent(SubCategories).exists()).toBe(true);
      });
    
    it('should render the ProductPool component', () => {
        expect(wrapper.findComponent(ProductPool).exists()).toBe(true);
    });
    
  })
  