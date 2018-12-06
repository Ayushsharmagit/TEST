# TEST

You must complete the following requirements:-
1)Java 8 or more
2)Eclipse
3)window builder for eclispse
4)any Database in pc
5)required database driver
6)log4j.jar
7)poi libs etc.

8)you must have these table in your database,you can rename it according to your need.
examdb=# select * from user_mst;
 uid | usereid | password | status
-----+---------+----------+--------
   1 | amit    | amit123  | y
   2 | ram     | ram123   | y
(2 rows)


examdb=# select * from role_mst;
 roleid |  name   | status
--------+---------+--------
      1 | Admin   | Y
      2 | Teacher | Y
      3 | Student | Y
(3 rows)


examdb=# select * from right_mst;
 rightid |      name      |    screen     | status
---------+----------------+---------------+--------
       1 | Upload Test    | UploadTset    | Y
       3 | Take Test      | TakeTest      | Y
       2 | Student Result | StudentResult | Y
(3 rows)


examdb=# select * from role_right_mapping;
 rolerightid | role | rightid
-------------+------+---------
           1 |    2 |       1
           2 |    2 |       2
           3 |    3 |       3
(3 rows)


examdb=# select * from user_role_mapping;
 userroleid | uid | roleid
------------+-----+--------
          1 |   1 |      2
          2 |   2 |      3
(2 rows)
