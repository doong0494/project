package com.movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.service.MemberService;

@Controller
public class TheaterController {

   @RequestMapping("th_2D")
   public String th_2D(Model model, HttpServletRequest request) {
      return "theaters/th_2D";
   }
   
   @RequestMapping("th_4DX")
   public String th_4DX(Model model, HttpServletRequest request) {
      return "theaters/th_4DX";
   }
   
   @RequestMapping("th_goldclass")
   public String th_goldclass(Model model, HttpServletRequest request) {
      return "theaters/th_goldclass";
   }
   
   @RequestMapping("th_Imax")
   public String th_Imax(Model model, HttpServletRequest request) {
      return "theaters/th_Imax";
   }
   
   @RequestMapping("th_premium")
   public String th_premium(Model model, HttpServletRequest request) {
      return "theaters/th_premium";
   }
   
   @RequestMapping("th_starium")
   public String starium(Model model, HttpServletRequest request) {
      return "theaters/th_starium";
   }
   

}