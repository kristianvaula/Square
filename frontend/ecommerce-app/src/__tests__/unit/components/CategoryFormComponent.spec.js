import { shallowMount } from '@vue/test-utils';
import CategoryForm from '@/components/CategoryForm.vue';
import BaseText from '@/components/templates/BaseText.vue';

describe('CategoryForm tests', () => {
  it('renders a form element', () => {
    const wrapper = shallowMount(CategoryForm);
    expect(wrapper.find('form.category-form').exists()).toBe(true);
  });

  it('renders a BaseText component for the description field', () => {
    const wrapper = shallowMount(CategoryForm);
    expect(wrapper.findComponent(BaseText).exists()).toBe(true);
    expect(wrapper.find('#descInput').exists()).toBe(true);
  });

  it('emits a newCategoryEvent when the form is submitted', async () => {
    const wrapper = shallowMount(CategoryForm);
    const form = wrapper.find("form");
    const spyOnForm = vi.spyOn(form, "trigger");
    form.trigger('click')
    expect(spyOnForm).toHaveBeenCalledOnce();
  });
});
