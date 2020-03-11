### Data transmit logic on CupCarbon simulator
---
#### Send:
* Send data with command "send" with following rules:
    * `send hello 2` 
    (Sends hello to the sensor having an id=2)
    * `send $p 2`
    (Sends the value of p to the sensor having an id=2)
    * `send $p`
    (Sends the value of p in a broadcast mode)
    * `send $p *`
    (Sends the value of p in a broadcast mode(the same as send $p))
    * `send $p * 3`
    (Sends the value of p in broadcast except the sensor having an id=3)
    * `send $p 0 4`
    (Sends the value of p to sensors having a MY address equal to 4)
    * `send $p 0 0 5`
    (Direct sending of the value p to sensors having an id equal to 5 (like in a GPRS/3G/4G mode))
    * `send !color 1`
    (Change the color of the send link on the IHM. It is practical to differentiate the type of the communication
    links(of the send instruction) between sensors.)
* The send command can hold at most 4 args accoring to source file "Command_SEND.java" under package "senscript"
* There is the logic how this command will execu:
    ```
    // execute send
        // First we write in a UART (if writtenInUART=false)
        // Then the message will be sent by radio (if writtenInUART=true) 
        // Than we will wait for an ACK if this mode is activated (ie. SimulationInputs.ack=true)
        // No ACK for the broadcast sending : arg2.equals("*") && arg3.equals("")
    ```
    * The sending process is basicly wirte the message into a buffer which it calls UART, then wait for ACK from the receiver, if both UART and ACK is ready then create a message event with args sender, receiver, message, type, time.
    `public MessageEvent(int type, SensorNode sSensor, SensorNode rSensor, String message, double time`
    the messageEvent class is under device package.

    * After calling all previous methods, the simulation system will call method `receivedMessages` for each MessageEvent in MessageEventList which push message into a buffer. Check `WisenSimulation.java` under simulation package. `MessageEventList.java` under device package.
---
#### Receive manually:
* Receive data from buffer with command `read`
    ```
    read x
    Assign the value of the buffer to x
    ```
* Check `readMessage` under package device package and in class `SensorNode`.
---
#### Radio Modules:
* Lora
    * Checking the Class `RadioModule_Lora.java`, it defines:
        * `spreadingFactor` 7
        * `codeRate` 0
        * `frequency`
                which are 		//868 MHz for Europe
            //915 MHz for North America
            //433 MHz band for Asia
        * radioRangeColor which I think is not important in out project.
        * RadioRangeRadius 5000
    * Checking the class `LORaTransceiver.java`
        * under this class, it checks `errorBitsOk` which is used in send message(check Send part) and `MessageEventList.java`, there is `errorBitsOk` function which uses to check if the message is ok to transmit using LOra tech. I am not true yet, will check into it later.
---
#### TO BE CONTINUE.