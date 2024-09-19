package com.zerock.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerock.test.dto.ChatRoom;
import com.zerock.test.dto.ChatRoom4;
import com.zerock.test.dto.CustomUserDetails;
import com.zerock.test.service.ChatRoomService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatRoomController {
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@GetMapping("/chat/rooms")
	public String getAllRooms(Model model) {
		List<ChatRoom> rooms = chatRoomService.getAllRooms();
		model.addAttribute("rooms",rooms);
		return "chat";
	}
	
	@GetMapping("/chat/room2")
	public String getAllRooms2(@RequestParam("userid") String userid, Model model) {
		
		List<ChatRoom4> myrooms = chatRoomService.getRoom(userid) ;
		model.addAttribute("myrooms",myrooms);
		return "chat";
	}
	
	@ModelAttribute("userid")
    public String getUserID() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            return userDetails.getUserDTO().getId();
        } else {
            return null;
        }
    }

	@GetMapping("/myRoomsPage")
	public String getChatRoomsPage(@ModelAttribute("userid") String userid, Model model, HttpSession session) {
		
		if(userid != null) {
			System.out.println("userid sypl : " + userid);
			log.info("userid 로그 :" + userid);
			List<ChatRoom4> chatRooms = chatRoomService.getRoom(userid);
			model.addAttribute("chatRooms",chatRooms);
		} else {
			return "redirect:/login";
		}

	    return "chat";
	}
	    
	@PostMapping("/chat/room1")
	public String createRoom(@ModelAttribute("room_name") String room_name,
	                         @ModelAttribute("product_idx") int product_idx,
	                         @ModelAttribute("sender") String sender,
	                         @ModelAttribute("product_seller") String seller_id,
	                         Model model) {
		
		ChatRoom4 existingChatRoom = chatRoomService.findChatRoomByParticipants(sender, seller_id, product_idx);
		
		if(existingChatRoom != null) {
			return "redirect:/myRoomsPage";
		}
		
		else { 
	    ChatRoom4 chatRoom = new ChatRoom4();
	    
	    chatRoom.setRoom_name(room_name);
	    chatRoom.setProduct_idx(product_idx);
	    chatRoom.setSender(sender);
	    chatRoom.setSeller_id(seller_id);
	    
	    System.out.println("chatroom4 : " + room_name);
	    System.out.println("chatroom4 : " + product_idx);
	    System.out.println("chatroom4 : " + sender);
	    System.out.println("chatroom4 : " + seller_id);
	    
	    chatRoomService.insertchatRoom(chatRoom);
	    return "redirect:/myRoomsPage";
	    
		}
	}
	    

	 @GetMapping("/chat/room/{room_id}")
	 @ResponseBody
	    public ChatRoom getRoomById(@PathVariable("room_id") int room_id) {
	        return chatRoomService.getRoomById(room_id);
	    } 
	 
	/*	@PostMapping("/chatroom/delete")
		public String deleteChatRoom(int room_id) {
			System.out.println(" delete room_id " + room_id);
			log.info("delete room_id" + room_id);
			chatRoomService.deleteChatRoom(room_id);
			return "redirect:/myRoomsPage";
		}*/
	 
	 @PostMapping("/chatroom/delete")
	 @ResponseBody
	    public void deleteChatRoom(@RequestParam("room_id") int room_id) {
	        // 채팅방 삭제
		 System.out.println("deletechatroom : " + room_id);
		 log.info("deletechatroom " + room_id);
	        chatRoomService.deleteChatRoom(room_id);
	        	
	        // WebSocket으로 삭제 알림 전송
	        messagingTemplate.convertAndSend("/topic/chatroom/delete", room_id);

	    }
	 
	 @GetMapping("/chatroom/details")
	 @ResponseBody
	 public ChatRoom4 getChatRoomDetails(@RequestParam("room_id") int room_id) {
		 System.out.println("getChatRoomDetails : " + room_id);
		 log.info("getChatRoomDetails " + room_id);
	     ChatRoom4 chatRoomDetails = chatRoomService.getroomdetail(room_id);
	     
	     return chatRoomDetails;
	 }
	 

}
