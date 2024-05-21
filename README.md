In this random flooding program, we've depicted the distributed algorithm of broadcasting with N processes where its structures and working will be explained as follows,

Structure:

1. We have a class 'Process' which is the blueprint of a process, where it has 3 properties 'pid' is the process id, 'msgsInChannel' is the variable that stores the message in the channel, 'token' is initialized as 1 because we'll be sending single message token over channel and we have methods for sending a message which will send 1 token/ message to the channel and receive a message which will take the token from the channel.

2. Class 'RandomFlooding' is the main class which has methods like 'main', and 'processMessage' which will send the message to a random process, and a helper method 'displayProcess' which will display the processes and which process is sending how many messages.

Algorithm:

1. Process 0 will initiate the computation by sending the messages to its neighbors.
2. Process 1 to N-2 will follow the algorithm upon receiving tokens it'll send the token to its neighbors.

Working:

1. We've instantiated the 'numberOfProcesses' as 5 and 'numberOfComputation' as 50
2. The initiator is enabled and runs the computation for the first time
3. Then for the rest of the time (ie 49) times
4. After that, we'll only receive the message/ token, and won't produce it
5. While executing the computation it basically will send a random token to its neighbors randomly and this function makes the executing process not send to itself and won't send a message to the same process again in a single execution

Output:

1. In the output file, we can see that the processes will be executed for 50 times
2. After the 50th execution, we'll not produce a new message, instead, we'll receive all the remaining messages in the channel, so only there'll be more 0's last of the computation.

Here we can change the 'numberOfProcesses', and 'numberOfComputation' in the program and check for different computations since everything happening is randomized.
