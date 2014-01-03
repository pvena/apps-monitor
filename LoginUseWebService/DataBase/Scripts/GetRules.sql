if(exists (select * from sys.objects where type like 'p' and 
											name like 'getRules'))
	drop procedure getRules
go 
--getRules '666d4bbefa5393d5'
create procedure getRules
@phoneId nvarchar(20)

as
declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId

select 
	r.idRule,
	r.commandKey,
	t.name +'-'+p.name as [key],
	pv.value
from [rule] r
inner join condition c  on
	c.idRule = r.idRule
inner join propertyValue pv on
	pv.idPropertyValue = c.idPropertyvalue
inner join [property] p on 
	pv.idProperty = p.idProperty
inner join [type] t on
	p.idType = t.idType
where r.idUser = @idUser or forAll = 1
order by r.idRule