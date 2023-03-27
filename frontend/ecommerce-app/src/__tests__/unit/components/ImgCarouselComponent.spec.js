import { mount } from '@vue/test-utils'
import ImgCarouselComponent from '@/components/ImgCarouselComponent.vue'

describe('ImgCarouselComponent', () => {
  const images = [
    { src: 'https://example.com/image1.jpg' },
    { src: 'https://example.com/image2.jpg' },
    { src: 'https://example.com/image3.jpg' },
  ];


  it('displays the first image when the component is mounted', () => {
    const wrapper = mount(ImgCarouselComponent, {
      props: {
        images,
        loaded: true,
      },
    });

    const img = wrapper.find('img');
    expect(img.exists()).toBe(true);
    expect(img.attributes('src')).toBe(images[0].src);
    wrapper.unmount()
  });

  it('displays the next image when "Next" button is clicked', async () => {
    const wrapper = mount(ImgCarouselComponent, {
      props: {
        images,
        loaded: true,
      },
    });

    const nextButton = wrapper.find('button:nth-child(2)');
    nextButton.trigger('click');
    await wrapper.vm.$nextTick();

    const img = wrapper.find('img');
    expect(img.exists()).toBe(true);
    expect(img.attributes('src')).toBe(images[1].src);
    wrapper.unmount()
  });

  it('displays the previous image when "Previous" button is clicked', async () => {
    const wrapper = mount(ImgCarouselComponent, {
      props: {
        images,
        loaded: true,
      },
    });

    const nextButton = wrapper.find('button:nth-child(2)');
    nextButton.trigger('click');
    await wrapper.vm.$nextTick();

    const previousButton = wrapper.find('button:nth-child(1)');
    previousButton.trigger('click');
    await wrapper.vm.$nextTick();

    const img = wrapper.find('img');
    expect(img.exists()).toBe(true);
    expect(img.attributes('src')).toBe(images[0].src);
  });

});
