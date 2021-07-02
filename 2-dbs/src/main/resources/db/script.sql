CREATE TABLE migration.product (
	id UUID PRIMARY KEY,
	name TEXT NOT NULL,
	price NUMERIC(2,4) NOT NULL
);

CREATE TABLE oficial.service(
	id UUID PRIMARY KEY,
	name TEXT NOT NULL,
	price NUMERIC(4,2) NOT NULL
);

insert into oficial.service values('67b6dcb6-c28c-4965-b9f7-5c830a04664d', 'test1', 10.1)
insert into migration.product values('3cbbcd26-c177-4277-b3df-bf4d9389f69d', 'test-product', 13.02)