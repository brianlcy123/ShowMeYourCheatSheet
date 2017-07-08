/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Owner
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(HttpServletRequest request,HttpServletResponse response,Model model) {
        System.out.println("home");
        return "index.html";
    }
    
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        System.out.println(name);
        model.addAttribute("name", name);
        return "imgur.html";
    }
}
