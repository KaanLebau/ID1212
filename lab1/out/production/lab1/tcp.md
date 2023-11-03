# Additional questions

In this document the following questions answered.
- Capture in and out traffic to chat server
- What do TCP headers in datagrams mean? 
- What do the flags ACK/SYN/SEQ/PSH/FIN mean?

The document structure is as follows, each question has a dedicated section that answers the question. If a question involved several parts, these parts are divided into subsections and answered.

## Capture Traffic
the traffic is captured using wireshark and saved in the same directory as the java files, the file format is pcapng and the file name is traffic.pcapng
## What do TCP headers in datagrams mean?


TCP headers contain necessary information about the connection between two machines.
headers contain the following
<ul>

<li><b>Source Port and Destination Port:</b> These fields identify the sending and receiving applications or services on the devices.

<li><b>Sequence Number:</b> Helps in arranging and reassembling segments on the receiving end.

<li><b>Acknowledgment Number:</b> Confirms the receipt of data, indicating the next expected sequence number.

<li><b>Header Length:</b> Specifies the length of the TCP header.

<li><b>Flags:</b> Contain control information, such as SYN (synchronization), ACK (acknowledgment), PSH (push), FIN (finish), RST (reset), and more, used to manage the connection state and segment transmission.

<li><b>Window Size:</b> Indicates the size of the receiving window, controlling the amount of data that can be sent before receiving an acknowledgment.

<li><b>Checksum:</b> Used for error-checking the TCP header and data.

<li><b>Urgent Pointer:</b> Points to the end of urgent data in the segment if the URG flag is set.
</ul>
## What do the flags ACK/SYN/SEQ/PSH/FIN mean?

### ACK

A control bit (acknowledge) occupying no sequence space, which
          indicates that the acknowledgment field of this segment
          specifies the next sequence number the sender of this segment
          is expecting to receive, hence acknowledging receipt of all
          previous sequence numbers.

### SYN
A control bit in the incoming segment, occupying one sequence
          number, used at the initiation of a connection, to indicate
          where the sequence numbering will start.
### SEQ
Specifies the sequence number of the data being sent in a TCP segment.
### PSH
A control bit occupying no sequence space, indicating that
          this segment contains data that must be pushed through to the
          receiving user.
### FIN
A control bit (finis) occupying one sequence number, which
          indicates that the sender will send no more data or control
          occupying sequence space.