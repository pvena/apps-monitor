if(exists (select * from sys.objects where type like 'p' and 
											name like 'getFile'))
	drop procedure getFile
go 
create procedure getFile
@phoneId nvarchar(20),
@name nvarchar(50) = null,
@isZip bit = null

as

declare @idUser int 

select top 1 @idUser = idUser from [user] u where u.phoneId like @phoneId or u.name like @phoneId

select
	*  
from  [file] f
where   (f.name like @name or @name is null) and
		(f.isZip = @isZip or @isZip is null) and
		idUser = @idUser
order by [name]