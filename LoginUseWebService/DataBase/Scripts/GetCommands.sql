if(exists (select * from sys.objects where type like 'p' and 
											name like 'getCommands'))
	drop procedure getCommands
go 
create procedure getCommands
@phoneId nvarchar(20) 

as

declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId

select 
r.idRule,
c.idCondition,
CommandKey,
t.[name] + '-' + p.[Name] + ' = ' + pv.[value] AS condition
 
from [rule] r
inner join condition c on
	r.idRule = c.idRule
inner join propertyValue pv on
	 pv.idPropertyValue = c.idPropertyValue
inner join [property] p on 
	p.idProperty = pv.idProperty
inner join [type] t on
	t.idType = p.idtype
where r.idUser = @idUser
order by r.idRule,c.idCondition