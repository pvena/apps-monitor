if(exists (select * from sys.objects where type like 'p' and 
											name like 'getUser'))
	drop procedure getUser
go 
create procedure getUser
@names nvarchar(max) = null

as

if(isnull(@names,'') <> '')
	set @names = ',' + @names + ','

select 
	u.* 
from [user] u
where @names like '%,' + convert(nvarchar(4),u.name) + ',%'  or
		@names is null
order by u.name