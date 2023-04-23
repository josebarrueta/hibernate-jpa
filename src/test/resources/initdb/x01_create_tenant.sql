create table public.tenant
(
    id         VARCHAR(40) NOT NULL PRIMARY KEY,
    name       VARCHAR(40) NOT NULL,
    status     smallint    NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (name)
);