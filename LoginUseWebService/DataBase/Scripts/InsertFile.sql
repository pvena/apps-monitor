if(exists (select * from sys.objects where type like 'p' and 
											name like 'insertFile'))
	drop procedure insertFile
go 
create procedure insertFile
@phoneId nvarchar(20),
@name nvarchar(50),
@process bit,
@size int,
@isZip bit

as 

declare @idUser int 

select @idUser = idUser from [user] where phoneId = @phoneId

if(isnull(@idUser,'') <> '' )
begin 
	if(	not exists(select 1 from [file] 
					where	idUser = @iduser and
							[name] = @name and
							isZip = @isZip))
	begin
		insert into [file](idUser,[name],[process],isZip,[size])
		values(@idUser,@name,@process,@isZip,@size)

		select @@identity as id
	end
	else
	begin
		update [file]
		set	[process] = @process,
			[size] = @size,
			isZip = @isZip
		where idUser= @idUser and [name] = @name and isZip = @isZip 
			
		select idFile as id	from [file] where idUser= @idUser and [name] = @name and isZip = @isZip 
	end
end
else
	select -1 as id
	