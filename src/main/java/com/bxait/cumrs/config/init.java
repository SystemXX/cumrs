package com.bxait.cumrs.config;
import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.model.User;
import com.bxait.cumrs.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class init implements ApplicationRunner{

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @Autowired
    UserRepo userRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userRepo.findUserByUserName(username);
        if(user != null){
            userRepo.delete(user);
        }
            User admin = new User();
            admin.setUserName(username);
            admin.setPassWord(password);
            admin.setIdentity(Const.USER_IDENTITY_ADMIN);
            userRepo.save(admin);
    }
}
