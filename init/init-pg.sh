#!/bin/bash

set -e
set -u

function create_user_and_schemas() {
  local schema=$1
  echo " Creating schema '$schema' for user '$POSTGRES_USER'"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
        CREATE USER $schema;
        CREATE SCHEMA $schema AUTHORIZATION $schema;
        GRANT ALL PRIVILEGES ON SCHEMA $schema TO $schema;
EOSQL
}

if [ -n "${POSTGRES_MULTIPLE_SCHEMAS:-}" ]; then
  echo "Multiple schema creation requested: $POSTGRES_MULTIPLE_SCHEMAS"
  for schema in $(echo $POSTGRES_MULTIPLE_SCHEMAS | tr ',' ' '); do
    create_user_and_schemas $schema
  done
  echo "Schemas are created successfully."
fi