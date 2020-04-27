getpos p
atget id id
set recA 0
set exp 1
loop
wait
read m
rdata $m rid type froid tarid info0 info1 info2 info3 info4 info5 info6 info7 info8 info9 info10 info11 info12 info13 info14 end
if(($type==A) && ($recA==1))
	if($tarid==$id)
		data d $id B $id $froid $p 0 E
		send $d $rid
	end
end
if(($type==A) && ($recA==0))
	set recA 1
	if($tarid==$id)
		data d $id B $id $froid $p 0 E
		send $d $rid 
		set recA 0
	else
		data prevA $rid $froid $tarid
		data d $id A $froid $tarid
		send $d
	end
end
if($type==B)
	rdata $prevA pid target from
	if($tarid==$target)
		mark 1
		data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $end
		send $d $pid
		set recA 0
	else
		data prevA $pid $target $from
	end
end