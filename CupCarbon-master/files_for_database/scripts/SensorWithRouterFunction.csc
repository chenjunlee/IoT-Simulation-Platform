getpos p
atget id id
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
if($type==A)
    if($tarid==$id)
        mark 1
	areadsensor v
	print $v
	rdata $v s s1 v1 s2 v2 s3 v3 s4 v4 s5 v5 s6 v6 end
	data d $id B $id $froid $p $info0
	if($s==X)
	    data var 0
	end
	if($s==0)
	    data var 0
	end
	if($s==1)
	    data var 1 $s1 $v1
	end
	if($s==2)
	    data var 2 $s1 $v1 $s2 $v2
	end
	if($s==3)
	    data var 3 $s1 $v1 $s2 $v2 $s3 $v3
	end
	if($s==4)
	    data var 4 $s1 $v1 $s2 $v2 $s3 $v3 $s4 $v4
	end
	if($s==5)
	    data var 5 $s1 $v1 $s2 $v2 $s3 $v3 $s4 $v4 $s5 $v5
	end
	if($s==6)
	    data var 6 $s1 $v1 $s2 $v2 $s3 $v3 $s4 $v4 $s5 $v5 $s6 $v6
	end
	data d $d $var
	data d $d E
	send $d $rid
        goto 11
    end
    if($recA==1)
        rdata $prevA pid target from rd
        if(($tarid==$from) && ($froid==$target))
            if($rd==$info0)
		data prevA $pid $target $from $rd
		goto 11
	    end
	    if($rd<$info0)
                set recA 0
                goto 11
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
		goto 11
	    end
	    if($rd<$info0)
                set recB 0
                goto 11
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
		goto 11
	    end
	    if($rd<$info0)
                set recC 0
                goto 11
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
		goto 11
	    end
	    if($rd<$info0)
                set recD 0
                goto 11
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
		goto 11
	    end
	    if($rd<$info0)
                set recE 0
                goto 11
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
		goto 11
	    end
	    if($rd<$info0)
                set recF 0
                goto 11
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
		goto 11
	    end
	    if($rd<$info0)
                set recG 0
                goto 11
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
		goto 11
	    end
	    if($rd<$info0)
                set recH 0
                goto 11
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
	goto 11
    end
    if($recB==0)
        set recB 1
	data prevB $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 11
    end
    if($recC==0)
        set recC 1
	data prevC $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 11
    end
    if($recD==0)
        set recD 1
	data prevD $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 11
    end
    if($recE==0)
        set recE 1
	data prevE $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 11
    end
    if($recF==0)
        set recF 1
	data prevF $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 11
    end
    if($recG==0)
        set recG 1
	data prevG $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 11
    end
    if($recH==0)
        set recA 1
	data prevA $rid $froid $tarid $info0
	data d $id A $froid $tarid $info0
	send $d * $rid
	goto 11
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
	    goto 11
	end
    end
    if($recB==1)
        rdata $prevB pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recB 0
	    goto 11
	end
    end
    if($recC==1)
        rdata $prevC pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recC 0
	    goto 11
	end
    end
    if($recD==1)
        rdata $prevD pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recD 0
	    goto 11
	end
    end
    if($recE==1)
        rdata $prevE pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recE 0
	    goto 11
	end
    end
    if($recF==1)
        rdata $prevF pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recF 0
	    goto 11
	end
    end
    if($recG==1)
        rdata $prevG pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recG 0
	    goto 11
	end
    end
    if($recH==1)
        rdata $prevH pid target from rd
	if($tarid==$target)
	    mark 1
	    data d $id B $froid $target $info0 $info1 $info2 $info3 $info4 $info5 $info6 $info7 $info8 $info9 $info10 $info11 $info12 $info13 $info14 $info15 $end
	    send $d $pid
	    set recH 0
	    goto 11
	end
    end
end
goto 11