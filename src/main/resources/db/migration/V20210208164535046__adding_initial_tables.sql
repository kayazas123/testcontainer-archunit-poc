CREATE TABLE companies
(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    name CHARACTER VARYING(255) NOT NULL UNIQUE,
    founded_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT companies_pky PRIMARY KEY (id)
);

CREATE trigger company_creation_timestamp_trigger
   BEFORE INSERT ON companies
   for each row EXECUTE procedure creation_timestamp_handler();
   
CREATE trigger company_updation_timestamp_trigger
   BEFORE UPDATE ON companies
   for each row EXECUTE procedure updation_timestamp_handler();
   
CREATE TABLE promoters
(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    first_name CHARACTER VARYING(255) NOT NULL,
    last_name CHARACTER VARYING(255) NOT NULL,
    date_of_birth TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    gender CHARACTER VARYING(255) NOT NULL,
    status CHARACTER VARYING(255) NOT NULL,
    company_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT promoters_pkey PRIMARY KEY (id),
    CONSTRAINT fk_key_promoters_company FOREIGN KEY (company_id)
        REFERENCES companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE trigger promoters_creation_timestamp_trigger
   BEFORE INSERT ON promoters
   for each row EXECUTE procedure creation_timestamp_handler();
   
CREATE trigger promoters_updation_timestamp_trigger
   BEFORE UPDATE ON promoters
   for each row EXECUTE procedure updation_timestamp_handler();
   
   
CREATE TABLE wrestlers
(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    first_name CHARACTER VARYING(255) NOT NULL,
    last_name CHARACTER VARYING(255) NOT NULL,
    stage_name CHARACTER VARYING(255) NOT NULL,
    date_of_birth TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    gender CHARACTER VARYING(255) NOT NULL,
    status CHARACTER VARYING(255) NOT NULL,
    company_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT wrestlers_pkey PRIMARY KEY (id),
    CONSTRAINT fk_key_wrestlers_company FOREIGN KEY (company_id)
        REFERENCES companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE trigger wrestlers_creation_timestamp_trigger
   BEFORE INSERT ON wrestlers
   for each row EXECUTE procedure creation_timestamp_handler();
   
CREATE trigger wrestlers_updation_timestamp_trigger
   BEFORE UPDATE ON wrestlers
   for each row EXECUTE procedure updation_timestamp_handler();
   
   
CREATE TABLE matches
(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    company_id UUID NOT NULL,
    outcome CHARACTER VARYING(255) NOT NULL,
    wrestler_one UUID NOT NULL,
    wrestler_two UUID NOT NULL,
    occured_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT matches_pkey PRIMARY KEY (id),
    CONSTRAINT fk_key_matches_company FOREIGN KEY (company_id)
        REFERENCES companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_key_matches_wrestler_one FOREIGN KEY (wrestler_one)
        REFERENCES wrestlers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_key_matches_wrestler_two FOREIGN KEY (wrestler_two)
        REFERENCES wrestlers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION    
);
