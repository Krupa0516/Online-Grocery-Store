package com.example.online_grocery_store.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.online_grocery_store.Entity.UserDtls;
import com.example.online_grocery_store.Repository.UserRepository;


@Controller
@RequestMapping("/index")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		UserDtls user = userRepo.findByEmail(email);

		m.addAttribute("user", user);

	}
}
	// @GetMapping("/")
	// public String index() {
	// 	return "index";
	// }

	// @GetMapping("changePass")
	// public String loadChangePassword() {
	// 	return "change_password";
	// }

// 	@PostMapping("/updatePassword")
// 	public String changePassword(Principal p,@RequestParam("oldPass") String oldPass,
// 	@RequestParam("newPass") String newPass,HttpSession session)
// 	{

// 		String email=p.getName();
// 		UserDtls loginUser=userRepo.findByEmail(email);

// 		boolean f=passwordEncoder.matches(oldPass,loginUser.getPassword());

// 		if(f){
// 			//System.out.println("old password is correct");
// 			loginUser.setPassword(passwordEncoder.encode(newPass));
// 			UserDtls updatePasswordUser=userRepo.save(loginUser);
// 			if(updatePasswordUser!=null)
// 			{
//                 session.setAttribute("msg","Password Change successfully");
			
// 			}
// 			else{
// 				session.setAttribute("msg","Something went wrong");
			
// 			}
// 		}
// 		else
// 		{
// 			session.setAttribute("msg","OLD password incorrect");
			
// 			//System.out.println("please enter correct password");
// 		}

// 		return "redirect:changePass";
// 	}
// } 