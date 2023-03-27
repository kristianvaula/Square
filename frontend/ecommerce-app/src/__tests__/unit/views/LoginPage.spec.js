import LoginPage from '@/views/LoginPage.vue';
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

    it("Is e-mail set to expected value", () => {
        const wrapper = mount(LoginPage, {
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

    it("Is password set to expected value", () => {
        const wrapper = mount(LoginPage, {
            global: {
                stubs: {
                    BaseInput: {
                        template: '<input data-test="password" type="password">'
                    }
                }
            }
        });
        const inputTxt = "Password1!";
        const passwordInput = wrapper.find("[data-test='password']");

        passwordInput.setValue(inputTxt);

        expect(passwordInput.element.value).toEqual(inputTxt)
    })
})