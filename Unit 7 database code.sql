CREATE DATABASE GussFineEating;

CREATE TABLE users (
    user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(50),
    phone_number INT NOT NULL,
    credit_card INT NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash BINARY(64) NOT NULL
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    server_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    status ENUM('pending', 'completed', 'cancelled') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (server_id) REFERENCES servers(server_id)
);

CREATE TABLE menu_items (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(255) NOT NULL,
    item_description TEXT,
    item_price DECIMAL(10, 2) NOT NULL
);

INSERT INTO menu_item (item_description, item_name, item_price)
values ('Tail of cattle seasoned with herbs and spices, fried, and then slow cooked', 'Oxtail', 12.99);

INSERT INTO menu_item (item_description, item_name, item_price)
values ('Curry that can be made with goat,mutton, or chicken', 'Curry', 10.99);

CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (item_id) REFERENCES menu_items(item_id)
);

CREATE TABLE reservations (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    date_reserved DATE NOT NULL,
    status ENUM('booked', 'cancelled') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE inventory (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(255) NOT NULL,
    item_quantity INT NOT NULL,
    reorder_level INT NOT NULL
);