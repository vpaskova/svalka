//package com.ddev.svalka.controlers;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ErorrHandlerControler implements ErrorController {
//
//    @RequestMapping("/error")
//    public ResponseEntity<?> error(){
//        return ResponseEntity.badRequest().body("Въвели сте грешен URL линк!");
//    }
//
//
//    @Override
//    public String getErrorPath() {
//        return null;
//    }
//
//
//}
