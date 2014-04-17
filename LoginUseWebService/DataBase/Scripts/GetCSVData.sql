if(exists (select * from sys.objects where type like 'p' and 
											name like 'getCSVData'))
	drop procedure getCSVData
go 
create procedure [dbo].[getCSVData]
@from datetime,
@to datetime,
@phoneId nvarchar(20) = null,
@propNames nvarchar(max) = null

as

declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId

if (isnull(@propNames,'') <> '')
	set @propNames = ';' + @propNames + ';'

select	
	l.date as Date,  
	t.name + '-' + p.name as FullName,
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
where l.date between @from and @to and
		(@propNames like '%;' + t.name + '-' + p.name + ';%' or @propNames is null)
order by l.date,t.name
