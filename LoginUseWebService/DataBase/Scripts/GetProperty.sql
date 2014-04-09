if(exists (select * from sys.objects where type like 'p' and 
											name like 'getProperty'))
	drop procedure getProperty
go 
create procedure getProperty 
@names nvarchar(max) = null

as

if(isnull(@names,'') <> '')
	set @names = ',' + @names + ','

select 
	t.name + '-' + p.name as FullName,
	p.*,
	t.name [Type]
from [property] p
inner join [type] t on 
	p.idType = t.idType
where @names like '%,' + convert(nvarchar(4),p.name) + ',%'  or
		@names is null
order by t.name + '-' + p.name