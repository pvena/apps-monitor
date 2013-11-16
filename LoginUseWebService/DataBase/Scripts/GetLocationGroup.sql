if(exists (select * from sys.objects where type like 'p' and 
											name like 'GetLocationGroup'))
	drop procedure GetLocationGroup
go 
create procedure GetLocationGroup
@phoneId nvarchar(20)

as 

declare @idUser int 

select @idUser = idUser from [user] where phoneId = @phoneId

select top 10
	* 
from locationGroup 
where idUser = @idUser
order by [count] desc