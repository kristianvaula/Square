import { shallowMount } from '@vue/test-utils';
import AdminCategoriesPage from '@/views/AdminCategoriesPage.vue';

describe('AdminCategoriesPage tests', () => {
  it('renders all the components', () => {
    const wrapper = shallowMount(AdminCategoriesPage);

    expect(wrapper.findComponent({ name: 'CategoryPool' }).exists()).toBe(true);
    expect(wrapper.findComponent({ name: 'CategoryForm' }).exists()).toBe(true);
    expect(wrapper.findComponent({ name: 'SubCategoryForm' }).exists()).toBe(true);
    expect(wrapper.findComponent({ name: 'SubCategories' }).exists()).toBe(true);
  });
});
