import { mount } from '@vue/test-utils'
import CategoryPool from '@/components/CategoryPool.vue'

describe('CategoryPool tests', () => {
  let wrapper

  beforeEach(() => {
    wrapper = mount(CategoryPool)
  })

  afterEach(() => {
    wrapper.unmount()
  })

  it('Check that the categoryPool includes all categories provided', async () => {
    const categories = [
      { id: 1, name: 'Category 1' },
      { id: 2, name: 'Category 2' },
      { id: 3, name: 'Category 3' },
    ]

    wrapper.setData({ categories })
    await wrapper.vm.$nextTick()
    expect(wrapper.findAllComponents({ name: 'CategoryCard' }).length)
      .toEqual(categories.length)
  })

  it('should emit a selectedCategoryEvent when you select a category', async () => {
    const category = { id: 1, name: 'Category 1' }
    const eventData = { categoryId: category.id }
    wrapper.setData({ categories: [category] })
    await wrapper.vm.$nextTick()
    wrapper.findComponent({ name: 'CategoryCard' }).vm.$emit('selected-card-event', eventData)

    expect(wrapper.emitted('selectedCategoryEvent')).toBeTruthy()
    expect(wrapper.emitted('selectedCategoryEvent')[0]).toEqual([category.id])
  })

  it('should emit a deselectCategoryEvent when you deselect a category', async () => {
    const category = { id: 1, name: 'Category 1' }
    const eventData = { categoryId: category.id }

    wrapper.setData({ categories: [category] })
    await wrapper.vm.$nextTick()
    wrapper.findComponent({ name: 'CategoryCard' }).vm.$emit('deselected-card-event', eventData)

    expect(wrapper.emitted('deselectCategoryEvent')).toBeTruthy()
    expect(wrapper.emitted('deselectCategoryEvent')[0]).toEqual([category.id])
  })
})
