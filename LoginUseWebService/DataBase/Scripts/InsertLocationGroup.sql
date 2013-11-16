if(exists (select * from sys.objects where type like 'p' and 
											name like 'InsertLocationGroup'))
	drop procedure InsertLocationGroup
go 
create procedure InsertLocationGroup
@idLocationGroup int = null,
@phoneId nvarchar(20),
@name nvarchar(20),
@longitud float,
@latitud float,
@count int

as 

declare @idUser int 

select @idUser = idUser from [user] where phoneId = @phoneId

if(isnull(@idLocationGroup,'') = '')
begin	 
	insert into locationGroup(idUser,[name],longitud,latitud,[count])
	values (@idUser,@name,@longitud,@latitud,@count)
end
else
begin
	update locationGroup
		set longitud = @longitud,		
			latitud  = @latitud,
			[count] = @count
	where idLocationGroup = @idLocationGroup and @idUser = idUser
end

if(@@rowcount > 0)
begin
	update [user]
	set lastLocationProcess = getDate()
	where idUser = @idUser
end
