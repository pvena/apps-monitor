if(exists (select * from sys.objects where type like 'p' and 
											name like 'InsertUser'))
	drop procedure InsertUser
go 
create procedure [dbo].[InsertUser]
@phoneId nvarchar(20),
@name nvarchar(50),
@version nvarchar(50),
@phoneModel nvarchar(50)

as


if(exists (select 1 from [user] where phoneId = @phoneId))
begin
	update [user]
	set phoneId = @phoneId,
		[name] = @name,
		[version] = @version,
		[phoneModel] = @phoneModel
	where phoneId = @phoneId

	select idUser as id from [user] where phoneId = @phoneId
end
else
begin
	insert into [user](phoneId,[name],[version],[phoneModel])
	values (@phoneId,@name,@version,@phoneModel)

	select @@IDENTITY as id
end