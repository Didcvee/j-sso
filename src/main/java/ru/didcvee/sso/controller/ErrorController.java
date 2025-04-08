package ru.didcvee.sso.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        System.out.println(request);
        // можно логировать или возвращать кастомную страницу
        return "error"; // имя view-шаблона
    }
}

