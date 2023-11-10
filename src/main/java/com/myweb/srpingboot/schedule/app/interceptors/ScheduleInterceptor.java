package com.myweb.srpingboot.schedule.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("schedule")
public class ScheduleInterceptor implements HandlerInterceptor {

    @Value("${config.schedule.opened}")
    private Integer opened;
    @Value("${config.schedule.closed}")
    private Integer closed;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= opened && hour <  closed) {
            StringBuilder mensaje = new StringBuilder("Bienvenidos al horario de atencion a clientes");
            mensaje.append(", atendemos desde las ");
            mensaje.append(opened);
            mensaje.append("hrs. ");
            mensaje.append("hasta las ");
            mensaje.append(closed);
            mensaje.append("hrs. ");
            mensaje.append("Gracias por su visita.");
            request.setAttribute("mensaje", mensaje.toString());
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/closed"));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String mensaje = (String) request.getAttribute("mensaje");
        if(modelAndView != null && handler instanceof HandlerMethod) {
            modelAndView.addObject("Schedule", mensaje);
        }
    }
}
