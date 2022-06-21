CREATE SCHEMA sprinter;

CREATE TABLE sprinter.brands (
    id INTEGER NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE sprinter.items (
    id INTEGER NOT NULL AUTO_INCREMENT,
    reference_code VARCHAR(255),
    name VARCHAR(255),
    brand_id INTEGER,
    size VARCHAR(20),
    material VARCHAR(255),
    color VARCHAR(100),
    sport VARCHAR(100),
    creation_date TIMESTAMP without time zone,
    PRIMARY KEY(id),
    CONSTRAINT fk_item_brand FOREIGN KEY(brand_id) REFERENCES brands(id)
);

