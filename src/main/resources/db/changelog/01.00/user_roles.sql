CREATE TABLE user_roles (
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    user_id INT NOT NULL,
    role_id INT NOT NULL
);

ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES client_roles(role_id) ON DELETE CASCADE;
ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES clients(id) ON DELETE CASCADE;