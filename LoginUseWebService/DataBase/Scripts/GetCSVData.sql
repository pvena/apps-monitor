if(exists (select * from sys.objects where type like 'p' and 
											name like 'getCSVData'))
	drop procedure getCSVData
go 
create procedure [dbo].[getCSVData]
@from datetime,
@to datetime,
@idUser int = null

as

select	* ,
	l.date as Date,  
	t.name as LogType,	
	p.name as PropName,
	pv.value as PropValue
from [log] l
inner join [propertyvalue] pv on 
	pv.idPropertyvalue = l.idpropertyvalue
inner join [property] p on 
	p.idProperty = pv.idProperty
inner join [type] t on 
	t.idType = p.idType
inner join [file] f on 
	f.idFile = l.idFile and
	(f.idUser = @idUser or @idUser is null)
where l.date between @from and @to
order by l.date,t.name
