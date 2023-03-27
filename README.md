# SQUARE
SQUARE is a web application that allows users to buy and sell products. It also includes a chat functionality, allowing users to communicate with each other and negotiate prices.
 
## TECHNOLOGIES USED
The frontend of the application is built with JavaScript and Vue.js, while the backend is built with Java and Spring Boot. The application uses one H2 database for testing and one for production. 

## GETTING STARTED
To run the app locally, follow these steps:
Clone the repository from gitlab 
Install the required dependencies by running npm install in the root directory. 
This includes
    - npm i pinia-plugin-persistedstate
    - npm install vee-validate --save
    - npm install pinia
    - npm install axios
    - npm install --save vue-lodash lodash
    - npm install -D vitest
    - npm i @vitejs/plugin-vue
Start the frontend by running npm run serve in the client directory. The frontend will run on localhost:8080
Start the backend by running mvn spring-boot:run in the server directory or open the project in your favorite IDE. Then go to the “EcommerceappApplication” file, where the main method for the spring boot application is located. The backend will run on localhost:8081
Visit http://localhost:8080 in your web browser to access the app
 
To run the application you must go to the directory “ecommerce-app” which is located in the “frontend” folder. Thereafter run “npm install” in the terminal 
You need npm and Node.js installed on your machine to run this. The different commands can be found in the package.json file, which is located under scripts. 
Some of the commands included in the package.json file is:
npm run serve (To start the application)
npm run test:unit (To run different test on frontend)
npm run cypress:open (To run end to end tests) 
npm run test:coverage (To see the test coverage from the test)

## USAGE
To access all of SQUARE's features, users must create a profile and log in. Logged-in users can create product listings to sell items, or browse listings and negotiate prices with sellers via chat. Users who are not logged in can also browse products, but cannot create listings or chat with other users. Creating a listing requires filling out a form with details such as title, description, price, and images. Once submitted, the listing will be visible to other users. Users can browse listings by category or search by title or description.
To purchase a product, users can view listing details and initiate a chat with the seller. The chat feature enables communication and price negotiation between users. Logged-in users can also favorite products, which adds the product to their favorite-list under their user-profile, and view products they have listed under my “my-listings”. Admin users can create and delete new categories and subcategories. 

## IN CASE OF PROBLEMS
If you run into error messages when trying to run the application, you might have to change the first letter of the following files to uppercase letters:
    - baseInput.css → Baseinput.css
    - formPage.css → FormPage.css
The files are located under src/assets/style.
