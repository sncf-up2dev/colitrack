CREATE TABLE IF NOT EXISTS app_user (
    username VARCHAR(255),
    password VARCHAR(255)
);

ALTER TABLE app_user ADD COLUMN IF NOT EXISTS role VARCHAR(255) NOT NULL DEFAULT 'VISITOR';