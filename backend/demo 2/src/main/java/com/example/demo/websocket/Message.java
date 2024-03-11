package com.example.demo.websocket;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Message")
@EntityListeners(AuditingEntityListener.class)
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long msgId;
	@NotBlank
	String senderName;
	@NotBlank
	//@Column(unique=true)
	String receiverName;
	@NotBlank
	String message;
    @NotBlank
	String date;
    @NotBlank
	String status;
    public Message() {
        super(); // TODO Auto-generated constructor stub
}
 public Message(String senderName, String receiverName, String message,String date,String status ) {
    super();
    this.senderName = senderName;
    this.receiverName = receiverName;
    this.message = message;
    this.date = date;
    this.status = status;
//	this.userType = userType;
}
public Long getMsgId() {
    return msgId;
}
public void setMsgId(Long msgId) {
    this.msgId = msgId;
}
public String getSenderName() {
    return senderName;
}
public void setSenderName(String senderName) {
    this.senderName = senderName;
}
public String getReceiverName() {
    return receiverName;
}
public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
}
public String getMessage() {
    return message;
}
public void setMessage(String message) {
    this.message = message;
}
public String getDate() {
    return date;
}
public void setDate(String date) {
    this.date = date;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}
@Override
public String toString() {
    return "User [id=" + msgId + ", sender name=" + senderName + ", receiver name=" + receiverName + ", message=" + message + ", date=" + date + ", status=" + status +"]";
}
}

// package com.example.demo.websocket;

// import java.io.Serializable;

// import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;
// import javax.validation.constraints.NotBlank;

// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// @Entity
// @Table(name = "Message")
// @EntityListeners(AuditingEntityListener.class)
// public class Message implements Serializable {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
// 	Long msgId;
// 	@NotBlank
// 	String senderName;
// 	@NotBlank
// 	//@Column(unique=true)
// 	String receiverName;
// 	@NotBlank
// 	String message;
//     @NotBlank
// 	String date;
//     @NotBlank
// 	String status;
//     public Message() {
//         super(); // TODO Auto-generated constructor stub
// }
//  public Message(String senderName, String receiverName, String message,String date,String status ) {
//     super();
//     this.senderName = senderName;
//     this.receiverName = receiverName;
//     this.message = message;
//     this.date = date;
//     this.status = status;
// //	this.userType = userType;
// }
// public Long getMsgId() {
//     return msgId;
// }
// public void setMsgId(Long msgId) {
//     this.msgId = msgId;
// }
// public String getSenderName() {
//     return senderName;
// }
// public void setSenderName(String senderName) {
//     this.senderName = senderName;
// }
// public String getReceiverName() {
//     return receiverName;
// }
// public void setReceiverName(String receiverName) {
//     this.receiverName = receiverName;
// }
// public String getMessage() {
//     return message;
// }
// public void setMessage(String message) {
//     this.message = message;
// }
// public String getDate() {
//     return date;
// }
// public void setDate(String date) {
//     this.date = date;
// }
// public String getStatus() {
//     return status;
// }
// public void setStatus(String status) {
//     this.status = status;
// }
// @Override
// public String toString() {
//     return "User [id=" + msgId + ", sender name=" + senderName + ", receiver name=" + receiverName + ", message=" + message + ", date=" + date + ", status=" + status +"]";
// }


// }
