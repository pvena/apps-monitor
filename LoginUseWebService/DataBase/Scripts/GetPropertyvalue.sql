if(exists (select * from sys.objects where type like 'p' and 
											name like 'getPropertyvalue'))
	drop procedure getPropertyvalue
go 
create procedure getPropertyvalue 
@property int = null
as

select 
	pv.*
from [property] p
inner join [type] t on 
	p.idType = t.idType
inner join propertyValue pv on
	pv.idproperty = p.idproperty and
	pv.idproperty = @property 
order by t.name + '-' + p.name