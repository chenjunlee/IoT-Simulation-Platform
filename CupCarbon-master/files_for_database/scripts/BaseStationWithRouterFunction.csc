getpos p
atget id id
led v1 v2
vget x v1 0
if($x==1)
    goto 128
end
set round 0
for i 1 $x
    if($round==10000)
        set round 0
    else
        set round $round+1
    end
    vget y v1 $i
    data d $id A $id $y $round
    send $d
    time st
    wait
    read m
    time ct
    set lantency $ct-$st
    set lantency $lantency/1000
    rdata $m rid type froid tarid pos1 pos2 rd nums s1 v1 s2 v2 s3 v3 s4 v4 s5 v5 s6 v6 end
    if(($type==A) && ($tarid==$id))
	data d $id B $id $froid $p $pos1
	send $d $rid
	goto 19
    end
    if(($type==B) && ($round==$rd))
    	mark 1
	if($nums==0)
		printdb Simulation time: $ct s
		printdb Sensor ID: $froid
		printdb Sensor location: $pos1 $pos2
		printdb Lantency: $lantency ms
		printdb \
	end
	if($nums==1)
		printdb Simulation time: $ct s
		printdb Sensor ID: $froid
		printdb Sensor location: $pos1 $pos2
		printdb Lantency: $lantency ms
		printdb Event: $s1
		printdb Event value: $v1
		printdb \
	end
	if($nums==2)
		printdb Simulation time: $ct s
		printdb Sensor ID: $froid
		printdb Sensor location: $pos1 $pos2
		printdb Lantency: $lantency ms
		printdb Event: $s1
		printdb Event value: $v1
		printdb Event: $s2
		printdb Event value: $v2
		printdb \
	end
	if($nums==3)
		printdb Simulation time: $ct s
		printdb Sensor ID: $froid
		printdb Sensor location: $pos1 $pos2
		printdb Lantency: $lantency ms
		printdb Event: $s1
		printdb Event value: $v1
		printdb Event: $s2
		printdb Event value: $v2
		printdb Event: $s3
		printdb Event value: $v3
		printdb \
	end
	if($nums==4)
		printdb Simulation time: $ct s
		printdb Sensor ID: $froid
		printdb Sensor location: $pos1 $pos2
		printdb Lantency: $lantency ms
		printdb Event: $s1
		printdb Event value: $v1
		printdb Event: $s2
		printdb Event value: $v2
		printdb Event: $s3
		printdb Event value: $v3
		printdb Event: $s4
		printdb Event value: $v4
		printdb \
	end
	if($nums==5)
		printdb Simulation time: $ct s
		printdb Sensor ID: $froid
		printdb Sensor location: $pos1 $pos2
		printdb Lantency: $lantency ms
		printdb Event: $s1
		printdb Event value: $v1
		printdb Event: $s2
		printdb Event value: $v2
		printdb Event: $s3
		printdb Event value: $v3
		printdb Event: $s4
		printdb Event value: $v4
		printdb Event: $s5
		printdb Event value: $v5
		printdb \
	end
	if($nums==6)
		printdb Simulation time: $ct s
		printdb Sensor ID: $froid
		printdb Sensor location: $pos1 $pos2
		printdb Lantency: $lantency ms
		printdb Event: $s1
		printdb le Event value: $v1
		printdb Event: $s2
		printdb Event value: $v2
		printdb Event: $s3
		printdb Event value: $v3
		printdb Event: $s4
		printdb Event value: $v4
		printdb Event: $s5
		printdb Event value: $v5
		printdb Event: $s6
		printdb Event value: $v6
		printdb \
	end
    else
	goto 19
    end
end
goto 9
set recA 0
set recB 0
set recC 0
set recD 0
set recE 0
set recF 0
set recG 0
set recH 0
wait
read m
rdata $m rid type froid tarid info0 info1 info2 info3 info4 info5 info6 info7 info8 info9 info10 info11 info12 info13 info14 info15 end
if($tarid==$id)
    data d $id B $id $froid $p $info0 0 E
    send $d $rid
    goto 136
end
if($type==A)
    if($recA==1)
        rdata $prevA pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevA $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recA 0
                goto 136
	    end
        else
            data prevA $pid $target $from $rd
        end
    end
    if($recB==1)
        rdata $prevB pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevB $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recB 0
                goto 136
	    end
        else
            data prevB $pid $target $from $rd
        end
    end
    if($recC==1)
        rdata $prevC pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevC $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recC 0
                goto 136
	    end
        else
            data prevC $pid $target $from $rd
        end
    end
    if($recD==1)
        rdata $prevD pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevD $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recD 0
                goto 136
	    end
        else
            data prevD $pid $target $from $rd
        end
    end
    if($recE==1)
        rdata $prevE pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevE $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recE 0
                goto 136
	    end
        else
            data prevE $pid $target $from $rd
        end
    end
    if($recF==1)
        rdata $prevF pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevF $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recF 0
                goto 136
	    end
        else
            data prevF $pid $target $from $rd
        end
    end
    if($recG==1)
        rdata $prevG pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevG $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recG 0
                goto 136
	    end
        else
            data prevG $pid $target $from $rd
        end
    end
    if($recH==1)
        rdata $prevH pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevH $pid $target $from $rd
		goto 136
	    end
	    if($rd<$info0)
                set recH 0
                goto 136
	    end
        else
            data prevH $pid $target $from $rd
        end
    end
    if($recA==0)
        set recA 1
	data prevA $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
    if($recB==0)
        set recB 1
	data prevB $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
    if($recC==0)
        set recC 1
	data prevC $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
    if($recD==0)
        set recD 1
	data prevD $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
    if($recE==0)
        set recE 1
	data prevE $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
    if($recF==0)
        set recF 1
	data prevF $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
    if($recG==0)
        set recG 1
	data prevG $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
    if($recH==0)
        set recA 1
	data prevA $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 136
    end
end
if($type==B)
    if($recA==1)
        rdata $prevA pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recA 0
	    goto 136
	end
    end
    if($recB==1)
        rdata $prevB pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recB 0
	    goto 136
	end
    end
    if($recC==1)
        rdata $prevC pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recC 0
	    goto 136
	end
    end
    if($recD==1)
        rdata $prevD pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recD 0
	    goto 136
	end
    end
    if($recE==1)
        rdata $prevE pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recE 0
	    goto 136
	end
    end
    if($recF==1)
        rdata $prevF pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recF 0
	    goto 136
	end
    end
    if($recG==1)
        rdata $prevG pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recG 0
	    goto 136
	end
    end
    if($recH==1)
        rdata $prevH pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recH 0
	    goto 136
	end
    end
end
goto 136