CREATE TABLE IF NOT EXISTS users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(256) NOT NULL,
  last_name VARCHAR(256) NOT NULL,
  age INTEGER NOT NULL
) engine=InnoDB;


INSERT IGNORE INTO users(first_name, last_name, age) VALUES("Leonardo", "DiCaprio", 45);
INSERT IGNORE INTO users(first_name, last_name, age) VALUES("Will", "Smith", 51);
INSERT IGNORE INTO users(first_name, last_name, age) VALUES("Denzel", "Washington", 65);