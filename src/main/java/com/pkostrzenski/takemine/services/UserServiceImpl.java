package com.pkostrzenski.takemine.services;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.Notifier;
import com.pkostrzenski.takemine.models.User;
import com.pkostrzenski.takemine.repository.interfaces.UserDao;
import com.pkostrzenski.takemine.repository.jpa.NotifierJpaRepository;
import com.pkostrzenski.takemine.services.interfaces.UserService;
import com.pkostrzenski.takemine.utils.PasswordValidator;
import com.pkostrzenski.takemine.utils.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    NotifierJpaRepository notifierJpaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(@Qualifier("UserJPA") UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User register(String email, String password) throws ServiceException {

        if(userDao.findByEmail(email).isPresent())
            throw new ServiceException("This e-mail is already taken", EMAIL_ALREADY_TAKEN);

        String username = UsernameGenerator.generateUsername();
        while(userDao.findByUsername(username).isPresent())
            username = UsernameGenerator.generateUsername();

        User createdUser = new User(username, email, passwordEncoder.encode(password));
        userDao.save(createdUser);

        return createdUser;
    }

    @Override
    public User changePassword(Integer userId, String currentPassword, String newPassword) throws ServiceException {
        Optional<User> userOptional = userDao.findById(userId);
        if(!userOptional.isPresent())
            throw new ServiceException("Did not find user with provided ID", USER_NOT_FOUND);

        User user = userOptional.get();
        if(!passwordEncoder.matches(currentPassword, user.getPassword()))
            throw new ServiceException("Provided current password is incorrect", INCORRECT_CURRENT_PASSWORD);

        if(!PasswordValidator.validatePassword(newPassword))
            throw new ServiceException("Provided new password does not meet the requirements", PASSWORD_DOES_NOT_MEET_REQUIREMENTS);


        user.setPassword(passwordEncoder.encode(newPassword));
        return userDao.save(user);
    }

    @Override
    public void setFirebaseToken(String username, String firebaseToken) throws ServiceException {
        Optional<User> user = userDao.findByUsername(username);
        if (!user.isPresent())
            throw new ServiceException("Did not find user with provided ID", USER_NOT_FOUND);

        user.get().setFirebaseToken(firebaseToken);
        userDao.save(user.get());
    }

    @Override
    public Notifier addNotifier(Notifier notifier) {
        notifier.getLocations().forEach(location -> location.setNotifier(notifier));
        return notifierJpaRepository.save(notifier);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username) {
        return userDao.findByUsernameOrEmail(username);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }
}
