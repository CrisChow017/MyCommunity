package life.cris.community.community.controller;

import life.cris.community.community.dto.AccessTokenDTO;
import life.cris.community.community.dto.GiteeUser;
import life.cris.community.community.mapper.UserMapper;
import life.cris.community.community.model.User;
import life.cris.community.community.provider.GiteeProvider;
import life.cris.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GiteeProvider giteeProvider;

    @Value("${gitee.client.id}")
    private String clientId;
    @Value("${gitee.secret}")
    private String clientSecret;
    @Value("${gitee.redirect.uri}")
    private String redirectUri;


    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        System.out.println(giteeUser);
        if (giteeUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(giteeUser.getName());
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setAvatarUrl(giteeUser.getAvatar_url());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            //登录成功，写入cookie 和 session
            request.getSession().setAttribute("user", giteeUser);
            return "redirect:/";
        }else {
            //登录失败， 重新登录
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
