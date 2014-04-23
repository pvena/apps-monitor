if(exists (select * from sys.objects where type like 'p' and 
											name like 'getDistinctValues'))
	drop procedure getDistinctValues
go 
create procedure [dbo].[getDistinctValues]
@from datetime,
@to datetime,
@phoneId nvarchar(20)= null,
@propName nvarchar(50) = 'WIFI-IACCESS'

as

declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId

select
	p.idProperty,
	t.name + '-' + p.name as propName
into #propTemp
from [property] p
inner join [type] t on 
	t.idType = p.idType and
	@propName like t.name + '-' + p.name

select distinct	
	pv.value
into #values
from [log] l
inner join [propertyvalue] pv on 
	l.date between @from and @to and
	pv.idPropertyvalue = l.idpropertyvalue
inner join #propTemp pt on 
	pt.idProperty = pv.idProperty
inner join [file] f on 
	f.idFile = l.idFile and
	f.idUser = @idUser

declare @atribute nvarchar(max)

select @atribute = '@atribute ' + @propName + ' {'
select 
	@atribute = @atribute + [value] + ','
from #values

select (substring(@atribute,1,len(@atribute) - 1)) + '}'

drop table #propTemp
drop table #values