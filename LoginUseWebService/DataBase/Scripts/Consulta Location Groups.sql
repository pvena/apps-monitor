
create table #temp (date datetime,prop nvarchar(50),val nvarchar(50))

insert into #temp
EXEC dbo.getCSVData '20131001','20140423','666d4bbefa5393d5'

select * from #temp 
drop table #temp