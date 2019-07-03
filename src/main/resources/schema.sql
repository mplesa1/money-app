create table if not exists users (
   id identity,
   username varchar(20) not null,
   password varchar(100) not null,
   enabled bit not null
);

create table if not exists authorities (
 username varchar(20) not null,
 authority varchar(20) not null
);

create table if not exists wallet (
  id identity,
  type varchar(150) not null,
  username varchar(20),
  user_id bigint
);

 create table if not exists expense (
    id identity ,
    name VARCHAR(50) not null,
    amount DECIMAL not null,
    expense_type VARCHAR(150) not null,
    place_of_expense VARCHAR(150) not null,

    wallet_id bigint,
    FOREIGN KEY (wallet_id) REFERENCES wallet
 );

