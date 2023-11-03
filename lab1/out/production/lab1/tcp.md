# Additional questions

In this document the following questions answered.
- Capture in and out traffic to chat server
- What do TCP headers in datagrams mean? 
- What do the flags ACK/SYN/SEQ/PSH/FIN mean?

The document structure is as follows, each question has a dedicated section that answers the question. If a question involved several parts, these parts are divided into subsections and answered.

## Capture Traffic

## What do TCP headers in datagrams mean?

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

### PSH
A control bit occupying no sequence space, indicating that
          this segment contains data that must be pushed through to the
          receiving user.
### FIN
A control bit (finis) occupying one sequence number, which
          indicates that the sender will send no more data or control
          occupying sequence space.