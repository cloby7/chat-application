package com.cloby.chatapplication.chat.Chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    //2)Method to send message
    //Payload(object)

    @MessageMapping("/chat.sendMessage")    //URL to invoke the message
    @SendTo("/topic/public")     //Whereto send the message
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage //Hold body of our request
    ){
        return chatMessage;
    }

    //1)Method to user
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ){
        //Add username in websocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
