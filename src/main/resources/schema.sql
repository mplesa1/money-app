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
  createDate timestamp not null,
  type varchar(150) not null,

  username varchar(20)
  --     users_id bigint,
  --     FOREIGN KEY (users_id) REFERENCES users
);

create table if not exists expense (
   id identity ,
   createDate timestamp not null,
   name VARCHAR(50) not null,
   amount DECIMAL not null,
   expenseType VARCHAR(150) not null,

   wallet_id bigint,
   FOREIGN KEY (wallet_id) REFERENCES wallet
);