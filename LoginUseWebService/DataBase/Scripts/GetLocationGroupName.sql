create function getLocationGroupName
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

	return @group
end