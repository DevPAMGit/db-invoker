CREATE TABLE IF NOT EXISTS dum_dummy(
    dum_id SERIAL,
    dum_data VARCHAR(10) NOT NULL,
    CONSTRAINT pk_dummy PRIMARY KEY(dum_id)
);