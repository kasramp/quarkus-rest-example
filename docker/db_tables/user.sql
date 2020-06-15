CREATE TABLE IF NOT EXISTS users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(256) NOT NULL,
  password VARCHAR(256) NOT NULL,
  role VARCHAR(256) NOT NULL,
  first_name VARCHAR(256) NOT NULL,
  last_name VARCHAR(256) NOT NULL,
  age INTEGER NOT NULL
) engine=InnoDB;

-- password = admin
INSERT IGNORE INTO users(username, password, role, first_name, last_name, age) VALUES("admin", "$2a$10$0.sx37/fBVMAxmasa3M5.uvNUPPXj6HSvjdyOsPONvG2WCVjq1KVW", "ADMIN", "Kasra", "Mp", 30);
-- password = 1234
INSERT IGNORE INTO users(username, password, role, first_name, last_name, age) VALUES("leo", "$2a$10$3GigtQXq.9U9xSSQ8Vylee7P/QdIK40gknKjmgFeCF7zaOxi5/eZq", "USER", "Leonardo", "DiCaprio", 45);
-- password = password
INSERT IGNORE INTO users(username, password, role, first_name, last_name, age) VALUES("will", "$2a$10$QwO.0dmFJjC11ePYUEHleunxDytcLltSqCnU0DpZKPHGB3k4musUe", "USER", "Will", "Smith", 51);
-- password = password123
INSERT IGNORE INTO users(username, password, role, first_name, last_name, age) VALUES("den", "$2a$10$U3OlRLVcPpGAHUgJgSU4wuwE4TLli7a67JmozrZeNOyWNFyzczx6i", "USER", "Denzel", "Washington", 65);