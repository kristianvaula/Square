DROP TABLE IF EXISTS product_subCategory;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS subCategory;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS prodImage;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS profileType;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS county;


CREATE TABLE county(
    countyId INT NOT NULL AUTO_INCREMENT,
    countyName VARCHAR(50),
    PRIMARY KEY (countyId)
);

CREATE TABLE city(
    cityId INT NOT NULL AUTO_INCREMENT,
    cityName VARCHAR(50),
    countyId INT NOT NULL,
    PRIMARY KEY (cityId),
    FOREIGN KEY (countyId) REFERENCES county(countyId)
);

CREATE TABLE address(
    addressId INT NOT NULL AUTO_INCREMENT,
    address VARCHAR(50),
    cityId INT NOT NULL,
    PRIMARY KEY (addressId),
    FOREIGN KEY (cityId) REFERENCES city(cityId)
);

CREATE TABLE profileType(
    profileTypeId INT NOT NULL AUTO_INCREMENT,
    roleName VARCHAR(50),
    PRIMARY KEY (profileTypeId)
);

CREATE TABLE profile(
     profileId INT NOT NULL AUTO_INCREMENT,
     firstName VARCHAR(50),
     lastName VARCHAR(50),
     eMail VARCHAR(50),
     password VARCHAR(50),
     addressId INT NOT NULL,
     profileTypeId INT NOT NULL,
     PRIMARY KEY (profileId),
     FOREIGN KEY (addressId) REFERENCES address(addressId),
     FOREIGN KEY (profileTypeId) REFERENCES profileType(profileTypeId)
);

CREATE TABLE category(
    categoryId INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(50),
    icon BLOB,
    PRIMARY KEY (categoryId)
);

CREATE TABLE subCategory(
    subCategoryId INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(50),
    categoryId INT NOT NULL,
    PRIMARY KEY (subCategoryId),
    FOREIGN KEY (categoryId) REFERENCES category(categoryId)
);

CREATE TABLE product(
    productId INT NOT NULL AUTO_INCREMENT,
    description VARCHAR (500),
    price INT,
    sellerId INT,
    buyerId INT,
    PRIMARY KEY (productId),
    FOREIGN KEY (sellerId) REFERENCES profile(profileId),
    FOREIGN KEY (buyerId) REFERENCES profile(profileId)
);

CREATE TABLE product_subCategory(
    productId INT NOT NULL,
    subCategoryId INT NOT NULL,
    PRIMARY KEY (productId, subCategoryId),
    FOREIGN KEY (productId) REFERENCES product(productId),
    FOREIGN KEY (subCategoryId) REFERENCES subCategory(subCategoryId)
);

CREATE TABLE prodImage(
    prodImageId INT NOT NULL AUTO_INCREMENT,
    image BLOB,
    productId INT NOT NULL,
    PRIMARY KEY (prodImageId),
    FOREIGN KEY (productId) REFERENCES product(productId)
);
