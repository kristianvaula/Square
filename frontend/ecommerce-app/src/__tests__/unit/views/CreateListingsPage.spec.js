import { shallowMount } from '@vue/test-utils';
import CreateListingPage from '@/views/CreateListingPage.vue';

describe('CreateListingPage', () => {
  it('renders all the components', () => {
    const wrapper = shallowMount(CreateListingPage);

   

    
    expect(wrapper.findComponent({ name: 'ImgCarousel' }).exists()).toBe(false);

    // Check if the form and its elements are rendered
    expect(wrapper.find('form').exists()).toBe(true);
    expect(wrapper.find('input[type="file"]').exists()).toBe(true);
    expect(wrapper.find('#titleInput').exists()).toBe(true);
    expect(wrapper.find('#priceInput').exists()).toBe(true);
    expect(wrapper.find('#descInput').exists()).toBe(true);
    expect(wrapper.find('#select').exists()).toBe(true);
    expect(wrapper.findComponent({ name: 'SubcategoryList' }).exists()).toBe(true);
    expect(wrapper.findComponent({ name: 'BaseCheckbox' }).exists()).toBe(true);

    // Check if the create listing button is rendered
    expect(wrapper.find('button[type="submit"]').exists()).toBe(true);
    expect(wrapper.find('button[type="submit"]').text()).toMatch('Create Listing');
  });

  

  it('submits the form with the correct data when the create listing button is clicked', async () => {
    const wrapper = shallowMount(CreateListingPage);
  
    // Wait for the next tick of the event loop
    await wrapper.vm.$nextTick();
  
    // Fill in form data
    const titleInput = wrapper.findComponent({ name: 'BaseText' }).find('input');
    await titleInput.setValue('Test Title');
  
    const priceInput = wrapper.find('#priceInput');
    await priceInput.setValue(10.99);
  
    const descInput = wrapper.find('#descInput');
    await descInput.setValue('Test description');
  
    const categorySelect = wrapper.find('#select');
    await categorySelect.setValue('Test Category');
  
    const subcategoryList = wrapper.findComponent({ name: 'SubcategoryList' });
    await subcategoryList.vm.$emit('update:selectedSubcategories', ['Subcategory 1', 'Subcategory 2']);
  
    const checkbox = wrapper.findComponent({ name: 'BaseCheckbox' });
    await checkbox.setChecked();
  
    // Submit the form
    const form = wrapper.find('form');
    await form.trigger('submit.prevent');
  
    // Check that the form data was submitted correctly
    expect(wrapper.emitted().createListing[0][0]).toMatchObject({
      title: 'Test Title',
      price: 10.99,
      description: 'Test description',
      category: 'Test Category',
      subcategories: ['Subcategory 1', 'Subcategory 2'],
      isNegotiable: true,
    });
  });
  
});
