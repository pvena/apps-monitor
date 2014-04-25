update [propertyvalue] 
 set [value] = replace([value],',','.' )
where idproperty = 210 and [value] like '%,%'