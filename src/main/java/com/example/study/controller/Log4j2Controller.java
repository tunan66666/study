package com.example.study.controller;

import com.example.study.vo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
//@Slf4j
public class Log4j2Controller {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(Log4j2Controller.class);

    @GetMapping("/output")
    public Object Output() {
        System.out.println("start");

        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");

        /**
        log.trace("trace level");
        log.debug("debug level");
        log.info("info level");
        log.warn("warn level");
        log.error("error level");
         **/

        System.out.println("end");
        return new ResponseMessage<>();
    }

}
