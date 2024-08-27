package com.zqu.ordersystem.controller;

import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.zqu.ordersystem.utils.JwtUtils.getAudience;

@RestController
@Slf4j
@CrossOrigin
public class RoleController {

    @Autowired
    UserService userService;

    @GetMapping("/role")
    public Result RoleCheck(HttpServletRequest req){
        log.debug("role");
        String token = req.getHeader("Authorization");
        String audience = getAudience(token);
        Integer isAdmin = userService.checkAdmin(Integer.valueOf(audience));
        System.out.println(isAdmin);
        return new Result(isAdmin);
    }
}
