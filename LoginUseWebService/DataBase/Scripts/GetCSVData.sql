if(exists (select * from sys.objects where type like 'p' and 
											name like 'getCSVData'))
	drop procedure getCSVData
go 
--[getCSVData] '20131001','20140430','666d4bbefa5393d5'
create procedure [dbo].[getCSVData]
@from datetime,
@to datetime,
@phoneId nvarchar(20)= null,
@propNames nvarchar(max) = null

as

update [propertyvalue] 
 set [value] = replace([value],',','.' )
where idproperty = 210 and [value] like '%,%'

declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId

if (isnull(@propNames,'') <> '')
	set @propNames = ';' + @propNames + ';'

----------------------------Obtengo solo pas propiedades que me interesan--------------------------
select
	p.idProperty,
	t.name + '-' + p.name as propName
into #propTemp
from [property] p
inner join [type] t on 
	t.idType = p.idType and
	(@propNames like '%;' + t.name + '-' + p.name + ';%' or @propNames is null)
-------------------------------Creo tabla temporal de resultados------------------------------------
create table #resultTemp (date datetime, FullName varchar(20), PropValue varchar(50))
----------------------------Obtengo solo los logs que me interesan----------------------------------
insert into #resultTemp(date, FullName, PropValue)
select 
	l.date,
	pt.propName,
	pv.value
from [log] l
inner join [propertyvalue] pv on 
	l.date between @from and @to and
	pv.idPropertyvalue = l.idpropertyvalue
inner join #propTemp pt on 
	pt.idProperty = pv.idProperty
inner join [file] f on 
	l.date between @from and @to and
	f.idFile = l.idFile and
	(f.idUser = @idUser or @idUser is null)
----------------------------------------------------------------------------------------------------
--------------------------------Inserto los datos precalculados------------------------------------
----------------------------------------------------------------------------------------------------
insert into #resultTemp(date, FullName, PropValue)
select distinct
	date,
	'TIME-ISWEEKDAY',
	case when datepart(weekday, date) > 1 and datepart(weekday, date) < 7 then 1 else 0 end
from #resultTemp
---------------------------------------------------------------------------------------------------- 
insert into #resultTemp(date, FullName, PropValue)
select distinct
	date,
	'TIME-QUARTER',
	datepart(hour, date) % 4
from #resultTemp 
---------------------------------------------------------------------------------------------------- 
insert into #resultTemp(date, FullName, PropValue)
select distinct
	date,
	'TIME-HOUR',
	datepart(hour, date)
from #resultTemp 
----------------------------------------------------------------------------------------------------

insert into #resultTemp(date, FullName, PropValue)
select distinct
	rt1.date,
	'LOCATION-GROUP',
	dbo.getLocationGroupName(@idUser,rt1.propValue,rt2.propValue)
from #resultTemp rt1
inner join #resultTemp rt2 on 
	rt1.date = rt2.date
where rt1.FullName like 'LOCATION-LAT' and rt2.FullName like 'LOCATION-LONG'
-------------------------------------------Elimino location-------------------------------------------
delete #resultTemp
where FullName in ('LOCATION-LAT','LOCATION-LONG','LOCATION-ALT','BATTERY-PCT') or
		PropValue is null
---------------------------------------------consulta final-------------------------------------------
select 
	date,
	FullName,
	replace(PropValue,' ','_') as PropValue
from #resultTemp
order by date, fullName, propValue

drop table #resultTemp
drop table #propTemp