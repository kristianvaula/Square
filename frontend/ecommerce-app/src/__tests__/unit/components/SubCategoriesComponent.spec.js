import { mount } from '@vue/test-utils';
import SubCategories from '@/components/SubCategories.vue';

describe('SubCategories tests', () => {
  const subCategoriesList = [
    { id: 1, subCategoryId: 1, description: 'Subcategory 1' },
    { id: 2, subCategoryId: 2, description: 'Subcategory 2' },
    { id: 3, subCategoryId: 3, description: 'Subcategory 3' }
  ];

  it('renders subcategory list correctly', () => {
    const wrapper = mount(SubCategories, {
      props: {
        SubCategories: subCategoriesList
      }
    })

    const subCategoryList = wrapper.find('#subcategory-list')
    const subCategoryItems = subCategoryList.findAll('li')
    expect(subCategoryItems).toHaveLength(subCategoriesList.length)

    subCategoryItems.forEach((item, index) => {
      expect(item.text()).toBe(subCategoriesList[index].description)
      expect(item.classes()).not.toContain('pressed')
    })
    wrapper.unmount()
  })

  it('marks selected subcategory as pressed', async () => {
    const wrapper = mount(SubCategories, {
      props: {
        SubCategories: subCategoriesList
      }
    })

    const subCategoryItems = wrapper.findAll('li')
    const selectedSubCategory = subCategoriesList[1]

    await subCategoryItems[selectedSubCategory.id - 1].trigger('click')

    //check if the component emits a subCategorySelected event with the correct subcategory ID
    expect(wrapper.emitted('subCategorySelected')).toBeTruthy()
    expect(wrapper.emitted('subCategorySelected')[0]).toEqual([selectedSubCategory.subCategoryId])
    //check if the clicked item is marked as pressed.
    expect(subCategoryItems[selectedSubCategory.id - 1].classes()).toContain('pressed')

    wrapper.unmount()
  })

})