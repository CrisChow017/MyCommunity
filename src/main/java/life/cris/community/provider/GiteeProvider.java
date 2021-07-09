package life.cris.community.provider;

import com.alibaba.fastjson.JSON;
import life.cris.community.dto.AccessTokenDTO;
import life.cris.community.dto.GiteeUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GiteeProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
//        Request request = new Request.Builder()
//                .url("https://gitee.com/login/oauth/access_token")
//                .post(body)
//                .build();
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code&code=" + accessTokenDTO.getCode() + "&client_id=b43b1475264e67a586af5c65ea2a347f3aed2d49e3c5e39907bee8cdc0db0851&redirect_uri=http://localhost:8081/callback&client_secret=3aa61f1cbb123a14ec282552b032cb2f0cf2d6dd7c8dc04f3055ba86c2553a24")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //从返回的token信息里抽取出token
            String token = string.split(",")[0].split("\"")[3];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GiteeUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://api.gitee.com/user")
//                .header("Authorization", "token " + accessToken)
//                .build();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GiteeUser giteeUser = JSON.parseObject(string, GiteeUser.class);
            return giteeUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
