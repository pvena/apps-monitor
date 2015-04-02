if(exists (select * from sys.objects where type like 'p' and 
											name like 'DeleteCommand'))
	drop procedure DeleteCommand
go 
create procedure DeleteCommand
@phoneId nvarchar(20), 
@idCondition int,
@idRule int 
as

declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId


delete condition
where idCondition = @idCondition

if(not exists (select 1 from condition where idRule = @idRule))
begin
	delete [rule] where idRule = @idRule
end