#!/bin/bash

docker exec -i mysql-user mysql -u root --password=secret user_db < db_tables/user.sql