package org.example.book.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.book.Dao.UserDao;
import org.example.book.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userRepository;


    @RequestMapping("/")
    public String index() {
        return "usersignin";
    }

    @RequestMapping("/list")
    public String list(Model model, HttpSession session){
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/user/login";
        }

        model.addAttribute("users", userRepository.findAll());

        return "userlist";
    }

    @RequestMapping("/login")
    public String login() {
        return "usersignin";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loggedUser", null);

        return "redirect:/user/login";
    }


    @RequestMapping(value="/signin", method= RequestMethod.POST)
    public String postLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        User user = userRepository.login(username, password);

        if(user == null)
            return "redirect:/user/login";
        else {
            session.setAttribute("loggedUser", user);

            return "redirect:/book/";
        }
    }

    @GetMapping("/signup")
    //@RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signup(User user) {
        return "usersignup";
    }

    @PostMapping("/signup")
    public String postSignup(@Valid User user, BindingResult bindingResult, Model model, HttpSession session){

        if(bindingResult.hasErrors()){
            return "usersignup";
        }

        userRepository.save(user);
        session.setAttribute("loggedUser", user);


        model.addAttribute("msg", "Informazioni salvate");
        return "redirect:/user/detail/"+user.getId();
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id) {
        User user = userRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (user != null){
            modelAndView.setViewName("userdetail");
            modelAndView.addObject("user", user);
            return modelAndView;
        }else {
            return null; //"error/404";
        }
    }


    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") long id) {
        userRepository.deleteById(id); //delete from Utenti where id = :id
        return "redirect:/user/list";
    }

}

