if(exists (select * from sys.objects where type like 'p' and 
											name like 'getType'))
	drop procedure getType
go 
create procedure getType
@names nvarchar(max) = null

as

if(isnull(@names,'') <> '')
	set @names = ',' + @names + ','

select 
	t.* 
from [type] t
where @names like '%,' + convert(nvarchar(4),t.name) + ',%'  or
		@names is null
order by t.name