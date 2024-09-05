package com.example.online_grocery_store.Controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.online_grocery_store.Entity.UserDtls;
import com.example.online_grocery_store.Repository.UserRepository;
import com.example.online_grocery_store.Service.UserService;


@Controller
public class HomeController {
    
    @Autowired
	 private UserService userService;

	 @Autowired
	 private UserRepository userRepo;
 
	 @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	 
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		if(p!=null)
		{
			String email = p.getName();
		    UserDtls user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
		}

	}

	@RequestMapping("/home.html")
    public String home1() {
        return "home"; // The name of your adminhome.html page
    }

	@RequestMapping("/bakery.html")
    public String adminHome() {
        return "bakery"; // The name of your adminhome.html page
    }

	@RequestMapping("/oil.html")
    public String oil() {
        return "oil"; // The name of your adminhome.html page
    }

	@GetMapping("changePass")
	public String loadChangePassword() {
		return "change_password";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/aboutus")
	public String Aboutus(){
		return "aboutus";
	}

	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDtls user,HttpSession session) {
    
   
		boolean f = userService.checkEmail(user.getEmail());

		if (f) {
			session.setAttribute("msg1", "Email Id alreday exists");
			return "redirect:/register?error";
		}

		else {
			UserDtls userDtls = userService.createUser(user);
			if (userDtls != null) {
				session.setAttribute("msg1", "Register Sucessfully");
				return "redirect:/signin";
			} else {
				session.setAttribute("msg1", "Something wrong on server");
				return "redirect:/register?error";
			}
		}
		
	}

	@GetMapping("/loadForgotPassword")
	public String loadForgetPassword()
	{
		return "forgot_password";
	}
	@GetMapping("/loadResetPassword/{id}")
	public String loadRestPassword(@PathVariable int id,Model m)
	{

		m.addAttribute("id", id);
		return "reset_password";
	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email,@RequestParam String phonenum,HttpSession session)
	{

       UserDtls user=userRepo.findByEmailAndPhonenum(email, phonenum);
         if(user!=null)
		 {
            return "redirect:/loadResetPassword/"+user.getId();
		 }
		 else{

			session.setAttribute("msg", "invalid email and phone number");
			return "forgot_password";
		 }
		
	}

	@PostMapping("/changePassword")
	public String resetPassword(@RequestParam String psw,@RequestParam Integer id,HttpSession session)
	{
		UserDtls user=userRepo.findById(id).get();
		String encryptPsw=passwordEncoder.encode(psw);
		user.setPassword(encryptPsw);

		UserDtls updateUser=userRepo.save(user);

		if(updateUser!=null)
		{
			session.setAttribute("msg", "password change successfully");
		}
		return "redirect:/loadForgotPassword";
	}

	@PostMapping("/updatePassword")
	public String changePassword(Principal p,@RequestParam("oldPass") String oldPass,
	@RequestParam("newPass") String newPass,HttpSession session)
	{

		String email=p.getName();
		UserDtls loginUser=userRepo.findByEmail(email);

		boolean f=passwordEncoder.matches(oldPass,loginUser.getPassword());

		if(f){
			//System.out.println("old password is correct");
			loginUser.setPassword(passwordEncoder.encode(newPass));
			UserDtls updatePasswordUser=userRepo.save(loginUser);
			if(updatePasswordUser!=null)
			{
                session.setAttribute("msg","Password Change successfully");
			
			}
			else{
				session.setAttribute("msg","Something went wrong");
			
			}
		}
		else
		{
			session.setAttribute("msg","OLD password incorrect");
			
			//System.out.println("please enter correct password");
		}

		return "redirect:changePass";
	}
} 

