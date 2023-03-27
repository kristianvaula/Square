import { useTokenStore } from '@/store/token';
import { mount } from '@vue/test-utils'
import ProductCard from '@/components/ProductCard.vue'
import { nextTick } from 'vue'

describe('ProductCard tests', () => {
  let wrapper

  const productInfo = {
    product: {
      title: 'Product Title',
      price: 100,
      productId: '123',
      
    },
    imageList: [{ src: 'image-source' }],
  }


  it('renders the product title', async () => {
    wrapper = mount(ProductCard, {
        props: {
            product: productInfo,
          }
    })
    expect(wrapper.text()).toContain(productInfo.product.title)
    wrapper.unmount()
  })

  it('renders the product price', async () => {
    wrapper = mount(ProductCard, {
        props: {
            product: productInfo,
          }
    })
    expect(wrapper.text()).toContain(`${productInfo.product.price} NOK`)
    wrapper.unmount()
  })

  it('renders the product image', async () => {
    wrapper = mount(ProductCard, {
        props: {
            product: productInfo,
          }
    })
    expect(wrapper.find('.image-wrapper img').attributes('src')).toBe(productInfo.imageList[0].src)
    wrapper.unmount()
  })
})