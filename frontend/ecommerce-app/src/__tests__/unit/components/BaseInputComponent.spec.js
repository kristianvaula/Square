import { mount } from '@vue/test-utils'
import BaseInputContainer from '@/components/templates/BaseInput.vue'

describe('BaseInputContainer', () => {
  it('emits the correct value when input is entered', async () => {
    const wrapper = mount(BaseInputContainer, {
      props: {
        label: 'Name',
        modelValue: ''
      }
    })

    const input = wrapper.find('input')
    await input.setValue('testValue')

    expect(wrapper.emitted('update:modelValue')[0]).toEqual(['testValue'])
  })
})
