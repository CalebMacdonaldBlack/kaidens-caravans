#!/bin/bash
set -e
createuser -s kc

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE kc_dev ENCODING 'UTF-8';
    GRANT ALL PRIVILEGES ON DATABASE kc_dev to kc;

    CREATE DATABASE kc_test ENCODING 'UTF-8';
    GRANT ALL PRIVILEGES ON DATABASE kc_test kc;
EOSQL
