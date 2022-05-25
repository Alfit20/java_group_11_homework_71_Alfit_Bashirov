CREATE TABLE customers
(
    id           bigserial,
    name         varchar     NOT NULL,
    email        varchar     NOT NULL,
    password     varchar     NOT NULL,
    enabled      boolean     NOT NULL default true,
    role         varchar(16) NOT NULL default 'USER',
    phone_number varchar     NOT NULL,
    address      varchar     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE category_table
(
    id            bigserial,
    category_name varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_table
(
    id          bigserial,
    name        varchar NOT NULL,
    price       INT     NOT NULL,
    image       varchar NOT NULL,
    description varchar NOT NULL,
    category_id BIGINT  NOT NULL,
    FOREIGN KEY (category_id)
        REFERENCES category_table (id),
    PRIMARY KEY (id)
);

CREATE TABLE review_table
(
    id         bigserial,
    text       varchar   NOT NULL,
    date_added timestamp NOT NULL,
    author_id  BIGINT    NOT NULL,
    product_id BIGINT    NOT NULL,
    FOREIGN KEY (author_id)
        REFERENCES customers (id),
    FOREIGN KEY (product_id)
        REFERENCES product_table (id),
    PRIMARY KEY (id)
);

CREATE TABLE order_table
(
    id          bigserial,
    price       INT       NOT NULL,
    customer_id BIGINT    NOT NULL,
    product_id  BIGINT    NOT NULL,
--     quantity    INT       NOT NULL,
    quantity    INT,
    order_date  timestamp NOT NULL,
    FOREIGN KEY (customer_id)
        REFERENCES customers (id),
    FOREIGN KEY (product_id)
        REFERENCES product_table (id),
    PRIMARY KEY (id)
);

CREATE TABLE cart
(
    id          bigserial,
    price       INT    NOT NULL,
    product_id  BIGINT NOT NULL,
    quantity    INT DEFAULT 1,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (product_id)
        REFERENCES product_table (id),
    FOREIGN KEY (customer_id)
        REFERENCES customers (id),
    PRIMARY KEY (id)
)


