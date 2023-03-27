import { mount } from "@vue/test-utils";
import BaseSelect from "@/components/templates/BaseSelect.vue"

describe("BaseSelect.vue", () => {
  let wrapper

  const options = ['Option 1', 'Option 2', 'Option 3']

  beforeEach(() => {
    wrapper = mount(BaseSelect, {
      props: {
        options
      }
    })
  })

  afterEach(() => {
    wrapper.unmount()
  })

  it("loads the options correctly", () => {
    const select = wrapper.find("select")
    expect(select.findAll("option").length).toBe(options.length)
  })

  it("emits the correct value on change", async () => {
    const options = ["Option 1", "Option 2", "Option 3"];
    const wrapper = mount(BaseSelect, {
      props: {
        options: options,
        modelValue: options[0],
      },
    });
    const select = wrapper.find("select");
    await select.setValue(options[1]);
    await select.trigger("change");
    expect(wrapper.emitted()["update:modelValue"]).toBeTruthy();
    expect(wrapper.emitted()["update:modelValue"][0]).toEqual([options[1]]);
  });

  it('renders the label correctly', () => {
    const label = 'Select an option'
    const wrapper = mount(BaseSelect, {
      props: {
        label: label,
        modelValue: '',
        options: options
      }
    })
  
    const labelWrapper = wrapper.find('label')
    expect(labelWrapper.text()).toBe(label)
  })
  
  
  
  
  
  
})
