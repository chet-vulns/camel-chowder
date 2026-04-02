CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(50)
);

INSERT INTO users (name, email, role) VALUES
('admin', 'admin@example.com', 'ADMIN'),
('alice', 'alice@example.com', 'USER'),
('bob', 'bob@example.com', 'USER');
