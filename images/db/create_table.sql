create table rob.namerequest(
  name varchar(256) not null primary key,
  updated timestamp not null,
  amount integer not null
);

insert into rob.namerequest values(
'Rob',
now(),
1
);

insert into rob.namerequest values(
'Terry',
now(),
1
);

commit;
