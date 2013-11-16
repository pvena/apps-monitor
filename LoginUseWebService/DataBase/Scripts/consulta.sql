/*
delete [log]
delete [propertyvalue]
delete [property]
delete [type]
delete [file]
*/
select * from [log]
select * from [propertyvalue]
select * from [property]
select * from [type]
select * from [file]
select *  from [user]


select 
	convert(varchar(20),latitud)  + ',' + convert(varchar(20),longitud) ,
	[count]
from locationgroup
where [count] > 10