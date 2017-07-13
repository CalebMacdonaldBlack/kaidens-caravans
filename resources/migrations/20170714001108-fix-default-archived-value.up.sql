UPDATE caravans SET archived = FALSE;
ALTER TABLE caravans ALTER COLUMN archived SET DEFAULT FALSE;
