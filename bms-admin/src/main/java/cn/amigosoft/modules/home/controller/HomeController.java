package cn.amigosoft.modules.home.controller;

import cn.amigosoft.modules.home.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专家表
 *
 * @author LiuHT
 * @since 1.0.0 2020-08-21
 */
@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @Autowired
    private HomeService homeService;

}