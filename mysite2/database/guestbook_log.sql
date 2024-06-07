select * from emaillist;

desc emaillist;

insert into author values(null, '...');

insert into author(name) values(?);

desc book;
 -- guestbook_log
update guestbook_log set count = count + 1 where date = current_date();
insert into guestbook_log values(current_date(), 1);

select date(reg_date) from guestbook where no = 1;
update guestbook_log set count = count + 1 where date = (select date(reg_date) from guestbook where no = 1);

select * from guestbook;
select * from guestbook_log;
delete from guestbook_log;






