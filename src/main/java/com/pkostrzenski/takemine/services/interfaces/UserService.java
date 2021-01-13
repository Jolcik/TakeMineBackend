package com.pkostrzenski.takemine.services.interfaces;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.Notifier;
import com.pkostrzenski.takemine.models.User;

import java.util.Optional;

public interface UserService {

    int USER_NOT_FOUND = 0;
    int PASSWORD_DOES_NOT_MEET_REQUIREMENTS = 2;
    int EMAIL_ALREADY_TAKEN = 3;
    int USERNAME_ALREADY_TAKEN = 4;
    int INCORRECT_CURRENT_PASSWORD = 5;

    User register(String email, String password) throws ServiceException;
    User changePassword(Integer userId, String currentPassword, String newPassword) throws ServiceException;
    void setFirebaseToken(String username, String firebaseToken) throws ServiceException;
    Notifier addNotifier(Notifier notifier);
    Optional<User> findByUsernameOrEmail(String username);
    Optional<User> findById(Integer id);
}
