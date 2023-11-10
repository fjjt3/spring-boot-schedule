package com.myweb.srpingboot.schedule.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AppController {

    @Value("${config.schedule.open}")
    private Integer opened;
    @Value("${config.schedule.close}")
    private Integer closed;

    @GetMapping({"/","/index"})
    public String index(Model model) {
        model.addAttribute("title", "Wilkomen Unseren Kunden");
        return "index";
    }

    @GetMapping("/closed")
    public String closed(Model model){
        StringBuilder mensaje = new StringBuilder("Cerrado, por favor visítenos desde las ");
        mensaje.append(opened);
        mensaje.append(" y las ");
        mensaje.append(closed);
        mensaje.append(" hrs. Gracias.");

        model.addAttribute("titulo", "Fuera del horario de atención");
        model.addAttribute("mensaje", mensaje);
        return "closed";
    }

}
