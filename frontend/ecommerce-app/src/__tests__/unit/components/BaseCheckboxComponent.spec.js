import { mount } from '@vue/test-utils'
import BaseCheckbox from '@/components/templates/BaseCheckbox.vue'

describe('BaseCheckbox tests', () => {
  const label = 'Test Label'
  let wrapper

  beforeEach(() => {
    wrapper = mount(BaseCheckbox, {
      props: {
        label: label
      }
    })
  })

  afterEach(() => {
    wrapper.unmount()
  })

  it('renders the label correctly', () => {
    const labelWrapper = wrapper.find('label')
    expect(labelWrapper.text()).toBe(label)
  })

  it('emits the correct value on change', async () => {
    const checkbox = wrapper.find('input[type="checkbox"]')

    await checkbox.setChecked(true)
    expect(wrapper.emitted()['update:modelValue'][0]).toEqual([true])

    await checkbox.setChecked(false)
    expect(wrapper.emitted()['update:modelValue'][1]).toEqual([false])
  })

  it('sets the checkbox as checked when modelValue is true', async () => {
    const wrapper = mount(BaseCheckbox, {
      props: {
        label: 'Test Checkbox',
        modelValue: true
      }
    })
  
    const checkbox = wrapper.find('input[type="checkbox"]')
    expect(checkbox.element.checked).toBe(true)
  })

  it('sets the checkbox as unchecked when modelValue is false', async () => {
    wrapper.setProps({ modelValue: false })

    const checkbox = wrapper.find('input[type="checkbox"]')
    expect(checkbox.element.checked).toBe(false)
  })
})
