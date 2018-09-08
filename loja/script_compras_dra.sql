create database compras;

use compras;

create table compra(
compid int not null auto_increment,
primary key(compid));

create table produto(
proid int not null auto_increment,
prodescricao varchar(100) not null,
proqtde int unsigned,
provalor double,
primary key(proid));

create table itemcompra(
itemcompracompid int not null,
itemcompraproid int not null,
itemcompraqtde int unsigned not null,
itemcompravalor double not null,
primary key(itemcompracompid, itemcompraproid),
foreign key(itemcompracompid) references compra(compid),
foreign key(itemcompraproid) references produto(proid)
);

alter table compra add column compdata date;
