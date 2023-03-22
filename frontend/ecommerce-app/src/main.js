import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import upperFirst from 'lodash/upperFirst'
import camelCase from 'lodash/camelCase'
import { createPinia } from "pinia";
import piniaPluginPersistedState from "pinia-plugin-persistedstate"

import './assets/style/Style.css'

const requireComponent = require.context(
    './components/templates',
    false,
    /Base[A-Z]\w+\.(vue|js)$/
)

const app = createApp(App)

requireComponent.keys().forEach(fileName => {
    const componentConfig = requireComponent(fileName)

    const componentName = upperFirst(
        camelCase(fileName.replace(/^\.\/(.*)\.\w+$/, '$1'))
    )

    app.component(componentName, componentConfig.default || componentConfig)
})

const pinia = createPinia();
pinia.use(piniaPluginPersistedState)

app.use(store).use(router).use(pinia).mount('#app')
