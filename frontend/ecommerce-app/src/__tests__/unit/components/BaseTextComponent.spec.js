import { mount } from '@vue/test-utils'
import BaseText from '@/components/templates/BaseText.vue'


describe('BaseText tests', () => {
    it('updates the modelValue when the user inputs text into the field', async () => {
        const wrapper = mount(BaseText, {
          props: {
            modelValue: 'initial value',
          },
        });
        const input = wrapper.find('input');
        await input.setValue('new value');
        expect(wrapper.emitted('update:modelValue')).toBeTruthy();
        expect(wrapper.emitted('update:modelValue')[0][0]).toBe('new value');
      });

      it('displays the label when provided as a prop', () => {
        const label = 'Test Label';
        const wrapper = mount(BaseText, {
          props: {
            label,
          },
        });
        const labelElement = wrapper.find('label');
        expect(labelElement.text()).toBe(label);
      });

      it('displays an error message when an error prop is provided', () => {
        const error = 'This field is required';
        const wrapper = mount(BaseText, {
          props: {
            error,
          },
        });
        const errorMessageElement = wrapper.find('.errorMessage');
        expect(errorMessageElement.exists()).toBe(true);
        expect(errorMessageElement.text()).toBe(error);
      });

      test('switches to a textarea when the `textarea` prop is true', () => {
        const wrapper = mount(BaseText, {
          props: {
            textarea: true,
          },
        });
        const textarea = wrapper.find('textarea');
        expect(textarea.exists()).toBe(true);
      });
})