create table public.test_entity
(
    id         VARCHAR(40) NOT NULL PRIMARY KEY,
    properties JSON        NOT NULL,
    tenant_id  VARCHAR(40) NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);