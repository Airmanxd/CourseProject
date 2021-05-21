CREATE TABLE IF NOT EXISTS users (
id int NOT NULL PRIMARY KEY,
email varchar NOT NULL,
username varchar(30) NOT NULL,
password varchar NOT NULL,
enabled boolean NOT NULL DEFAULT FALSE,
role varchar
);

CREATE TABLE IF NOT EXISTS products (
productId int NOT NULL PRIMARY KEY,
name varchar(30) NOT NULL,
category varchar(20) NOT NULL,
price real NOT NULL,
image VARCHAR
);

CREATE TABLE IF NOT EXISTS user_cart
(
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id),
    UNIQUE (user_id, product_id)
);

