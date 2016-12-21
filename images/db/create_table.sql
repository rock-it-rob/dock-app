create table rob.namerequest(
  id integer not null auto_increment primary key,
  name varchar(256) not null unique,
  updated timestamp not null default now()
);

insert into rob.namerequest(name) values('Rob');
insert into rob.namerequest(name) values('Terry');
insert into rob.namerequest(name) values('Vik');
insert into rob.namerequest(name) values('April');

commit;