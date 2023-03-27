const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
  configFile: "../../backend/ecommerce-app/src/main/resources/application-test.properties"
});
