import { mount } from "@vue/test-utils";
import RegisterProfilePage from "@/views/RegisterProfilePage.vue"
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import BaseSelect from "@/components/BaseSelect.vue";


describe("Input tests", () => {
  it('Possible to register an valid profile when the components is stub out', async () => {
    //arrange
    const mockAxios = new MockAdapter(axios);

    const countiesResponse = {
      data: [
        { countyName: 'Rogaland'},
        { countyName: 'Agder'},
        { countyName: 'Viken'}
      ],
    };

    mockAxios.onGet('/unauthorized/counties').reply(200, countiesResponse);

    const wrapper = mount(RegisterProfilePage, {
      global: {
        stubs: {
          BaseInput: true,
          BaseSelect: true,
        }
      }
    })

    //act
    //wait to finsih mounting and fetching data
    await wrapper.vm.$nextTrik();

    wrapper.find("[data-test='firstName']").setValue("Ola");
    wrapper.find("[data-test='lastName']").setValue("Norman");
    wrapper.find("[data-test='eMail']").setValue("Ola.Nordman@mail.com");
    wrapper.find("[data-test='city']").setValue("Oslo");
    wrapper.find("[data-test='address']").setValue("Olav tryggvasons gate 24");
    wrapper.find("[data-test='password']").setValue("Password1!");
    
    wrapper.findComponent({ name: BaseSelect }).vm.$emit('update:modelValue', 'Rogaland')
    
    const registerBtn = wrapper.find('registerBtn')
    registerBtn.trigger('click')

    await wrapper.vm.$nextTrik();//registering the user

    expect(wrapper.vm.tokenStore.jwtToken).toBeTruthy();
    expect(wrapper.vm.errorMessage).toBeFalsy();
    expect(wrapper.vm.$router.currentRoute.value.path).toBe('/');
  });
});