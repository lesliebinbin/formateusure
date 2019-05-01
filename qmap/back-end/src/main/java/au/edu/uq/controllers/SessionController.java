package au.edu.uq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class SessionController {
    @Qualifier("webApplicationContext")
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/")
    public String process(Model model, HttpSession session) throws IOException {
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
        if (Objects.isNull(messages)) messages = new ArrayList<>();
        model.addAttribute("sessionMessages", messages);
        System.out.println(messages);
        return "redirect:/hello.html";
    }

    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
        List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (Objects.isNull(messages)) {
            messages = new ArrayList<>();
        }
        messages.add(msg);
        request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        return "redirect:/";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
