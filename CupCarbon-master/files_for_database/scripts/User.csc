atget id id
led v 1
vget x v 0
loop
for i 1 $x
    vget y v $i
    data d $id A $id $y
    send $d
    time st
    wait
    read m
    time ct
    set lantency $ct-$st
    set lantency $lantency/1000
    rdata $m rid type froid tarid pos1 pos2 nums s1 v1 s2 v2 s3 v3 s4 v4 s5 v5 s6 v6 end
    if($type==B)
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
		printdb Event value: $v1
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
	goto 10
    end
end