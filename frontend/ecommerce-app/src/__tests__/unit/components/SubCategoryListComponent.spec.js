import { mount } from "@vue/test-utils";
import SubCategoryList from "@/components/SubCategoryList.vue"
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

describe("SubcategoryList tests", () => {
    let wrapper
    const baseurl = "http://localhost:8081";
    const defaultConfig = {
        headers: {
            "Content-type" : "application/json"
        }
    }

    it('loads no subcategories when the category that the categoryId prop represents has no categories', async () => {
        const mockAxios = new MockAdapter(axios);
        const subCategoriesResponse = null
        mockAxios.onGet(`${baseurl}/unauthorized/sub-category`, defaultConfig).reply(200, subCategoriesResponse);
        wrapper = mount(SubCategoryList, {
            props: {
              categoryId: 1
            }
        })
        await wrapper.vm.$nextTick();
    
        expect(wrapper.vm.items).toEqual(subCategoriesResponse)
      })

      /**it('loads subcategories when the category that the categoryId prop has no categories', async () => {
        const mockAxios = new MockAdapter(axios);
        const subCategoriesResponse = [{ id: 1, description: 'subcategory 1' }]
        mockAxios.onGet(`${baseurl}/unauthorized/sub-category`, defaultConfig).reply(200, subCategoriesResponse);
        wrapper = mount(SubCategoryList, {
            props: {
              categoryId: 1
            }
        })
        
        await wrapper.vm.$nextTick();
    
        expect(wrapper.vm.items).toEqual(subCategoriesResponse)
      }) */
    })  