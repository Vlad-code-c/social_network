package com.example.social_network.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error/500";
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Logger logger = LoggerFactory.getLogger(this.getClass());

        if (status != null){
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == 404){
                return "error/404";
            } else if (statusCode == 500){
                return "error/500";
            }

        }


        return "error/404";
    }
}
