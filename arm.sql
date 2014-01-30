create or replace 
PROCEDURE CANDI_SETS(
    F_size IN NUMBER)
IS
  I   NUMBER;
  x1 number;
  sel_stmt varchar2(8000);
  frm_st   varchar2(8000);
  where_st  varchar2(8000);
  ord_st  varchar2(8000);
  SQL_SEL     varchar2(8000);
  SQL_ins     VARCHAR2(8000);
begin
  I   := 1;
x1:=1;
if(x1<2) then
x1:=2;
end if;
  sel_stmt := 'SELECT ';
  frm_st   := ' from fiset3_'||to_char(F_size-1)||' c, '||'fiset3_'||to_char(F_size-1)||' d';
  where_st := ' WHERE ';
  ord_st := ' ORDER BY ';
  
  WHILE(I<F_size)
  LOOP
    DBMS_OUTPUT.PUT_LINE(I);
    sel_stmt  := sel_stmt || 'c.itemid'||to_char(I)||', ';
    IF(I          <(F_size-1)) THEN
      where_st :=where_st||'c.itemid'||to_char(I)||' = '||'d.itemid'||to_char(I)||' AND ';
      ord_st :=ord_st||'c.itemid'||to_char(I)||' , ';
    ELSE
      where_st :=where_st||'c.itemid'||to_char(I)||' < '||'d.itemid'||to_char(I)||' ';
      ord_st :=ord_st||'c.itemid'||to_char(I)||' ';
    END IF;
    I :=I+1;
  END LOOP;
  SQL_SEL :=sel_stmt||'d.itemid'||to_char(F_size-1)||' '||frm_st || ' '||where_st||' '|| ord_st ;
  SQL_INS := 'INSERT INTO ciset3_'||to_char(F_size)||' '||SQL_SEL||' ';
    execute immediate SQL_INS;
  commit;
END Candi_sets;
/



create or replace 
PROCEDURE F_ITEM_SETS(
    F_Size IN NUMBER,
    Support IN NUMBER)
IS
  I           number;
  x1          number;
  tcount      number:=1731;
  SELECT_STMT VARCHAR2(5000);
  FROM_STMT   varchar2(5000);
  WHERE_STMT  varchar2(5000);
  ORDER_STMT  varchar2(5000);
  GRPBY_STMT  varchar2(5000);
  HAVING_STMT varchar2(5000);
  SQL_SEL     varchar2(5000);
  SQL_INS     VARCHAR2(5000);
BEGIN
  I := 1;
  if(x1=1)then
  x1:=1;
  end if;
  
  
SELECT_STMT := ' SELECT ';
  FROM_STMT   := ' FROM ';
  WHERE_STMT  := ' WHERE ';
  ORDER_STMT  := ' ORDER BY ';
  GRPBY_STMT  := ' GROUP BY ';
 
  WHILE(I     <=F_Size)
  LOOP
    
    SELECT_STMT:=SELECT_STMT||'itemid'||to_char(I)||', ';
    FROM_STMT  := FROM_STMT||'TRANS'||' t'||to_char(I)||', ';
    IF(I       <=(F_Size-1)) THEN
     
      WHERE_STMT := WHERE_STMT||'t'||to_char(I)||'.itemid = '||'ciset3_'||to_char(F_Size)||'.itemid'||to_char(I)||' AND '||'t'||to_char(I)||'.transid = '||'t'||to_char(I+1)||'.transid AND ';
      GRPBY_STMT :=GRPBY_STMT ||'itemid'||to_char(I)||' , ';
    ELSE
      WHERE_STMT := WHERE_STMT||'t'||i||'.itemid = '||'ciset3_'||to_char(F_Size)||'.itemid'||i||' ';
      GRPBY_STMT :=GRPBY_STMT||'itemid'||to_char(I);
    END IF;
    I :=I+1;
  END LOOP;
 
  SELECT_STMT := SELECT_STMT ||'round(count(*)*100/ '||tcount||',2)';
  FROM_STMT   :=FROM_STMT||' '||'ciset3_'||to_char(F_Size);
  HAVING_STMT := ' HAVING round(count (*)*100/1731,2) >='||SUPPORT;
  SQL_SEL := SELECT_STMT||FROM_STMT||WHERE_STMT||GRPBY_STMT||HAVING_STMT;
 
  SQL_INS := 'INSERT INTO '||'fiset3_'||F_Size||SQL_SEL;
  EXECUTE immediate SQL_INS;
  commit;
END;
/

create or replace 
PROCEDURE task__one(support NUMBER)
AS
tcount NUMBER:=1731;
x1     number;
tableCount1 number;
tableCount2 number;
BEGIN
DELETE FROM ftask1;
INSERT INTO ftask1(itemid, support) SELECT itemid, round((count(*)*100)/tcount,2) AS Support FROM trans GROUP BY itemid HAVING round((count(*)*100)/tcount,2)>=support;

commit;
END ;
/

create or replace 
PROCEDURE task__two(support NUMBER)
AS
tcount NUMBER;
BEGIN
SELECT COUNT(DISTINCT transid) INTO tcount FROM trans;
task__one(support);
DELETE FROM F1Task2;
INSERT INTO F1Task2(transID, itemID) SELECT * from trans WHERE itemid IN (SELECT itemID from trans GROUP BY itemID HAVING round((count(*)*100)/tcount,2)>=support);
DELETE FROM F2Task2;
INSERT INTO F2Task2(itemID1, itemID2, support) SELECT t1.itemID, t2.itemID, round((count(*)*100)/tcount,2) FROM F1Task2 t1, F1Task2 t2 WHERE t1.transID = t2.transID AND t1.itemID < t2.itemID GROUP BY t1.itemID, t2.itemID HAVING round((count(*)*100)/tcount,2)>=support;
commit;
END ;
/

create or replace 
procedure drp_table(del_size in number)
AUTHID CURRENT_USER
is 
del_1 varchar2(5000);
del_2 varchar2(5000);
I number;
begin 
I:=1;

while (I<=del_size )
loop

del_1:='drop table ciset3_' || to_char(I);
del_2:='drop table fiset3_' || to_char(I);

I:=I+1;


execute immediate del_1;
execute immediate del_2;
end loop;

commit;
end;
/

create or replace 
procedure bef_drop
as
tableCount1 number;
tableCount2 number;
tableCount3 number;
tableCount4 number;
tableCount5 number;
begin
 SELECT COUNT(*) INTO tableCount1 FROM user_tables WHERE table_name = upper('items');
       IF tableCount1 = 1 THEN
              EXECUTE IMMEDIATE 'DROP TABLE items CASCADE CONSTRAINTS';
    END IF;
    SELECT COUNT(*) INTO tableCount2 FROM user_tables WHERE table_name = upper('trans');
       IF tableCount2 = 1 THEN
              EXECUTE IMMEDIATE 'DROP TABLE trans CASCADE CONSTRAINTS';
    END IF;
    SELECT COUNT(*) INTO tableCount3 FROM user_tables WHERE table_name = upper('ftask1');
       IF tableCount3 = 1 THEN
              EXECUTE IMMEDIATE 'DROP TABLE ftask1 CASCADE CONSTRAINTS';
    END IF;
    SELECT COUNT(*) INTO tableCount4 FROM user_tables WHERE table_name = upper('f1task2');
       IF tableCount4 = 1 THEN
              EXECUTE IMMEDIATE 'DROP TABLE f1task2 CASCADE CONSTRAINTS';
    END IF;
    SELECT COUNT(*) INTO tableCount5 FROM user_tables WHERE table_name = upper('f2task2');
       IF tableCount5 = 1 THEN
              EXECUTE IMMEDIATE 'DROP TABLE f2task2 CASCADE CONSTRAINTS';
    END IF;
    commit;
    end bef_drop;
/
exit;