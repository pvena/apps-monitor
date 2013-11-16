if(exists (select * from sys.objects where type like 'p' and 
											name like 'GetLocation'))
	drop procedure GetLocation
go 
create procedure GetLocation
@phoneId nvarchar(20)

as 

declare @idUser int 

select @idUser = idUser from [user] where phoneId = @phoneId

select 
	date,
	pv.[LONG] as [Longitud],
	pv.[LAT] as [Latitud]
from (	select l.date, pv.value, p.[name]
		from [log] l 
		inner join [user] u on u.idUser = @idUser and (l.date > u.lastLocationProcess or u.lastLocationProcess is null)
		inner join [file] f on f.idFile = l.idFile and f.idUser = u.idUser		
		inner join [propertyvalue] pv on pv.idPropertyvalue = l.idpropertyvalue
		inner join [property] p on p.idProperty = pv.idProperty and	p.[name] in ('LAT','LONG')) as d
pivot 
( 
	max(d.[value]) 
	for d.[name] in ([LONG],[LAT])
)	pv
order by date 