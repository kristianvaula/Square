import { shallowMount } from "@vue/test-utils";
import CategoryCard from "@/components/CategoryCard.vue";

describe("CategoryCard tests", () => {
  let wrapper;
  const categoryInfo = {
    categoryId: 1,
    description: "Category 1",
    size: 2,
  };

  beforeEach(() => {
    wrapper = shallowMount(CategoryCard, {
      propsData: {
        CategoryInfo: categoryInfo,
      },
    });
  });

  afterEach(() => {
    wrapper.unmount();
  });

  it("renders the correct category title", () => {
    const title = wrapper.find(".category-title h3");
    expect(title.text()).toEqual(categoryInfo.description);
  });

  it("renders the correct category size", () => {
    const size = wrapper.find(".category-size span");
    expect(size.exists()).toBe(true);
    expect(size.text()).toEqual(`(${categoryInfo.size})`);
    wrapper.vm.$forceUpdate();
  });

  it("emits 'selectedCardEvent' event when clicked and category is not selected", () => {
    wrapper.trigger("click");
    expect(wrapper.emitted("selectedCardEvent")).toBeTruthy();
    expect(wrapper.emitted("selectedCardEvent")[0][0]).toEqual(categoryInfo);
  });

  it("emits 'deselectedCardEvent' event when clicked and category is already selected", () => {
    wrapper.vm.store.CurrentCategoryID = categoryInfo.categoryId;
    wrapper.trigger("click");
    expect(wrapper.emitted("deselectedCardEvent")).toBeTruthy();
    expect(wrapper.emitted("deselectedCardEvent")[0][0]).toEqual(categoryInfo);
  });
});


