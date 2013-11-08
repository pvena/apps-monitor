if(exists (select * from sys.objects where type like 'p' and 
											name like 'getFile'))
	drop procedure getFile
go 
create procedure getFile
@name nvarchar(50),
@isZip bit

as

select
	*  
from  [file] f
where f.name like @name and
		f.isZip = @isZip