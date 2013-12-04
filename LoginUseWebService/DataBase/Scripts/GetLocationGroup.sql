if(exists (select * from sys.objects where type like 'p' and 
											name like 'GetLocationGroup'))
	drop procedure GetLocationGroup
go 
--GetLocationGroup '666d4bbefa5393d5'
create procedure GetLocationGroup
@phoneId nvarchar(20)

as 

declare @idUser int 
declare @maxLocations int
select 
	@idUser = idUser,
	@maxLocations = maxLocations
from [user] where phoneId = @phoneId

select --top @maxLocations	
	Row_Number() OVER (ORDER BY ([count]) desc) [order],
	* 
into #temp
from locationGroup 
where idUser = @idUser
order by [count] desc

delete #temp 
where [order] > @maxLocations

select * from #temp 

drop table #temp


