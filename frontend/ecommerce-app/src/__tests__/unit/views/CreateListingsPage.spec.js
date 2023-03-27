import { shallowMount } from '@vue/test-utils';
import CreateListingPage from '@/views/CreateListingPage.vue';
import { mount } from '@vue/test-utils'

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

  it("is tittel set to expected value", () => {
        const wrapper = mount(CreateListingPage, {
            global: {
                stubs: {
                    BaseText: {
                        template: '<input data-test="textInput" type="text">'
                    }
                }
            }
        });
        const inputTxt = "test input";
        const nameInput = wrapper.find("[data-test='textInput']");

        nameInput.setValue(inputTxt);

        expect(nameInput.element.value).toEqual(inputTxt)

  })
  
});
