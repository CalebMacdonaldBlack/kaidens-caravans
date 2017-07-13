CREATE EXTENSION pgcrypto;

CREATE TABLE caravans (
  id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid()
)