import RegisterProfilePage from '@/views/RegisterProfilePage.vue'
import { mount } from '@vue/test-utils'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import router from '@/router';

describe('Test for inputs in login page', () => {
    beforeEach(() => {
        const app = createApp({})
        const pinia = createPinia()
        app.use(router).use(pinia)
    })

    it("Is firstname set to expected value", () => {
        const wrapper = mount(RegisterProfilePage, {
            global: {
                stubs: {
                    BaseInput: {
                        template: '<input data-test="firstName" type="text">'
                    }
                }
            }
        });
        const inputTxt = "ola";
        const firstNameInput = wrapper.find("[data-test='firstName']");

        firstNameInput.setValue(inputTxt);

        expect(firstNameInput.element.value).toEqual(inputTxt)
    });

    it("Is lastname set to expected value", () => {
        const wrapper = mount(RegisterProfilePage, {
            global: {
                stubs: {
                    BaseInput: {
                        template: '<input data-test="lastName" type="text">'
                    }
                }
            }
        });
        const inputTxt = "ola";
        const lastNameInput = wrapper.find("[data-test='lastName']");

        lastNameInput.setValue(inputTxt);

        expect(lastNameInput.element.value).toEqual(inputTxt)
    });

    

    it("Is e-mail set to expected value", () => {
        const wrapper = mount(RegisterProfilePage, {
            global: {
                stubs: {
                    BaseInput: {
                        template: '<input data-test="eMail" type="text">'
                    }
                }
            }
        });
        const inputTxt = "ola@mail.com";
        const eMailInput = wrapper.find("[data-test='eMail']");

        eMailInput.setValue(inputTxt);

        expect(eMailInput.element.value).toEqual(inputTxt)
    });

    it('Has county correspondent value as given input', () => {
        const wrapper = mount(RegisterProfilePage, {
            global: {
                stubs: {
                    BaseSelect: {
                        template: '<select data-test="county"><option :selected="true">Rogaland</option></select>'
                    }
                }
            }
        });
        const countyInput = wrapper.find("[data-test='county']");

        expect(countyInput.find("option:checked").element.value).toBe("Rogaland");
    });

    it("Is city set to expected value", () => {
        const wrapper = mount(RegisterProfilePage, {
            global: {
                stubs: {
                    BaseInput: {
                        template: '<input data-test="city" type="text">'
                    }
                }
            }
        });
        const inputTxt = "Oslo";
        const cityInput = wrapper.find("[data-test='city']");

        cityInput.setValue(inputTxt);

        expect(cityInput.element.value).toEqual(inputTxt)
    });

    it("Is address set to expected value", () => {
        const wrapper = mount(RegisterProfilePage, {
            global: {
                stubs: {
                    BaseInput: {
                        template: '<input data-test="address" type="text">'
                    }
                }
            }
        });
        const inputTxt = "Olav tryggvasons gate 24";
        const addressInput = wrapper.find("[data-test='address']");

        addressInput.setValue(inputTxt);

        expect(addressInput.element.value).toEqual(inputTxt)
    });

    it("Is password set to expected value", () => {
        const wrapper = mount(RegisterProfilePage, {
            global: {
                stubs: {
                    BaseInput: {
                        template: '<input data-test="password" type="password">'
                    }
                }
            }
        });
        const inputTxt = "Passord1!";
        const passwordInput = wrapper.find("[data-test='address']");

        passwordInput.setValue(inputTxt);

        expect(passwordInput.element.value).toEqual(inputTxt)
    });
})