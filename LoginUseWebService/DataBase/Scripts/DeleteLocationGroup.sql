if(exists (select * from sys.objects where type like 'p' and 
											name like 'deleteLocationGroups'))
	drop procedure deleteLocationGroups
go 
create procedure deleteLocationGroups
@phoneId nvarchar(20)

as 

declare @idUser int 

select @idUser = idUser from [user] where phoneId = @phoneId

delete locationGroup
where idUser = @idUser

update [user]
set lastLocationProcess = null
where idUser = @iduser