package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Authorities;
import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.repository.IAuthoritiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminRoleController {
    private IAuthoritiesRepository authoritiesRepository;

    public AdminRoleController(IAuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("/roles")
    public String showRoles(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = authentication.getName();
        var authorities = authoritiesRepository.findAllWithoutCurrentUser(username);
        model.addAttribute("authorities", authorities);

        return "authorities";
    }

    @RequestMapping("/addRole/{username}")
    public String addRole(@PathVariable(value="username") String username, Model model) {
        authoritiesRepository.save(username);

       return "redirect:/admin/roles";
    }

    @RequestMapping("/removeRole/{username}/{authority}")
    public String removeRole(@PathVariable(value="username") String username, @PathVariable(value="authority") String authority, Model model) {
        authoritiesRepository.removeAdminAuthorities(username,authority);

        return "redirect:/admin/roles";
    }
}
