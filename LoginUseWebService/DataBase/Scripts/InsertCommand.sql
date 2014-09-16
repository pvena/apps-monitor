if(exists (select * from sys.objects where type like 'p' and 
											name like 'InsertCommand'))
	drop procedure InsertCommand
go 
create procedure InsertCommand
@phoneId nvarchar(20), 
@idPropertyValue int,
@idRule int = null,
@commandKey varchar(30)
as

declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId

if (@idRule is null)
begin
	insert into [rule] (idUser,[description],commandKey,forAll)
	values(@idUser,'From App',@commandKey,0)

	set @idRule = @@identity
end

insert into condition(idRule,idPropertyValue)
values (@idRule,@idPropertyValue)

select @@identity AS Id

