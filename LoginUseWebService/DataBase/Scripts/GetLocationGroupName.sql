alter function getLocationGroupName
(
@idUser int,
@lat1InDegrees real,
@long1InDegrees real
)
returns nvarchar(20)

begin
	declare @group nvarchar(20)
	declare @const real

	select @const = (60 * 1852)

	select top 1
		@group = case when [name] is null then '-' else [name] end
 	from locationGroup
	where idUser = @idUser and
		(Sqrt(
				Power((@lat1InDegrees - latitud) * @const, 2) + 
				Power(((@long1InDegrees - longitud) * Cos(@lat1InDegrees * PI() / 180)) * @const, 2)
			  )
		) <= 200	


--	SELECT top 1
--		@group = case when [name] is null then '-' else [name] end
--	from locationGroup
--	where idUser = @idUser and
--		3956 * 2 * ASIN(
--          SQRT( POWER(SIN((@lat1InDegrees - abs(latitud)) * pi()/180 / 2), 2) 
--              + COS(@long1InDegrees * pi()/180 ) * COS(abs(latitud) * pi()/180)  
--              * POWER(SIN((@long1InDegrees - longitud) * pi()/180 / 2), 2) )) 
--          <= 200


	return @group
end