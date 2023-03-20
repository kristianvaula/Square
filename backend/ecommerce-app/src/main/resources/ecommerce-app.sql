DROP TABLE Product_SubCategory;
DROP TABLE Product;
DROP TABLE SubCategory;
DROP TABLE Category;
DROP TABLE ProdImage;
DROP TABLE Profile;
DROP TABLE ProfileType;
DROP TABLE Address;
DROP TABLE City;
DROP TABLE County;


CREATE TABLE County(
    countyId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    stateName VARCHAR(50),
    PRIMARY KEY (countyId)
);

CREATE TABLE City(
    cityId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    cityName VARCHAR(50),
    countyId INT NOT NULL,
    PRIMARY KEY (cityId),
    FOREIGN KEY (countyId) REFERENCES County(countyId)
);

CREATE TABLE Address(
    addressId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    address VARCHAR(50),
    cityId INT NOT NULL,
    PRIMARY KEY (addressId),
    FOREIGN KEY (cityId) REFERENCES City(cityId)
);

CREATE TABLE ProfileType(
    profileTypeId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    roleName VARCHAR(50),
    PRIMARY KEY (profileTypeId)
);

CREATE TABLE Profile(
     profileId INT UNSIGNED NOT NULL AUTO_INCREMENT,
     firstName VARCHAR(50),
     lastName VARCHAR(50),
     ePost VARCHAR(50),
     password VARCHAR(50),
     addressId INT NOT NULL,
     profileTypeId INT NOT NULL,
     PRIMARY KEY (profileId),
     FOREIGN KEY (addressId) REFERENCES Address(addressId),
     FOREIGN KEY (profileTypeId) REFERENCES ProfileType(profileTypeId)
);

CREATE TABLE ProdImage(
    prodImageId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    image BLOB,
    description VARCHAR(50),
    productId INT NOT NULL,
    PRIMARY KEY (prodImageId),
    FOREIGN KEY (productId) REFERENCES Product(productId)
);

CREATE TABLE Category(
    categoryId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(50),
    PRIMARY KEY (categoryId)
);

CREATE TABLE SubCategory(
    subCategoryId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(50),
    categoryId INT NOT NULL,
    PRIMARY KEY (subCategoryId),
    FOREIGN KEY (categoryId) REFERENCES Category(categoryId)
);

CREATE TABLE Product(
    productId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    breif_description VARCHAR(100),
    full_description VARCHAR (500),
    price INT,
    PRIMARY KEY (productId)
);

CREATE TABLE Product_SubCategory(
    productId INT UNSIGNED NOT NULL,
    subCategoryId INT UNSIGNED NOT NULL,
    PRIMARY KEY (productId, subCategoryId),
    FOREIGN KEY (productId) REFERENCES Product(productId),
    FOREIGN KEY (subCategoryId) REFERENCES SubCategory(subCategoryId)
);
