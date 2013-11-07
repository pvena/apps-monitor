if(exists (select * from sys.objects where type like 'p' and 
											name like 'InsertLog'))
	drop procedure InsertLog
go 
create procedure InsertLog 
@file nvarchar(50),
@date datetime,
@type nvarchar(20),
@property nvarchar(20),
@value nvarchar(50)

as

declare @idFile int 
declare @idUser int 
declare @idType int
declare @idProperty int 
declare @idPropertyValue int

--------------------------------------------------------------------------------------
select 
	@idType = idType 
from [type] where [name] = @type

if(isnull(@idType,'') = '')
begin
	insert into [type]([name])values(@type)
	set @idType = @@identity
end
--------------------------------------------------------------------------------------
select 
	@idProperty = idProperty 
from [property] where idType = @idType and [name] like @property

if(isnull(@idProperty,'') = '')
begin
	insert into [property](idType,[name])
	values(@idType,@property)

	set @idProperty = @@identity 
end
--------------------------------------------------------------------------------------
select 
	@idPropertyValue = idPropertyValue 
from propertyValue where  idProperty = @idProperty and [value] like @value

if(isnull(@idPropertyValue,'') = '')
begin
	insert into [propertyValue](idProperty,[value])
	values(@idProperty,@value)

	set @idPropertyValue = @@identity 
end
--------------------------------------------------------------------------------------
select 
	@idFile = idFile,
	@idUser = idUser
from [file] where [name] like @file and isZip = 0

if(isnull(@idFile,'') <> '' and isnull(@idUser,'') <> '')
begin
	insert into [log] (idFile,idPropertyValue,date)
	values(@idFile,@idPropertyValue,@date)

	select @@identity as id
end


