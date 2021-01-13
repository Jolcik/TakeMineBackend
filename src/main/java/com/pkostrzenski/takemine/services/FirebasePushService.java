package com.pkostrzenski.takemine.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.pkostrzenski.takemine.models.Location;
import com.pkostrzenski.takemine.models.Notifier;
import com.pkostrzenski.takemine.models.Product;
import com.pkostrzenski.takemine.models.User;
import com.pkostrzenski.takemine.repository.interfaces.UserDao;
import com.pkostrzenski.takemine.services.interfaces.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service("firebase")
public class FirebasePushService implements PushService {

    private static final Logger LOGGER= LoggerFactory.getLogger(FirebasePushService.class);

    final static String PRODUCT_ID_KEY = "product_id";
    final private String FIREBASE_DATABASE_URL = "https://takemine-70ba6-default-rtdb.europe-west1.firebasedatabase.app";
    final private String FIREBASE_CONFIG_URI = "takemine-70ba6-firebase-adminsdk-fvmqs-38868aefe3.json";

    final private String CREATE_EVENT_PUSH_TITLE = "Dodano przedmiot, który może Cię zainteresować!";
    final private String CREATE_EVENT_PUSH_BODY = "Kliknij, aby zobaczyć!";

    @Autowired
    private UserDao userDao;

    public FirebasePushService(){
        try{
            FileInputStream serviceAccount = new FileInputStream(FIREBASE_CONFIG_URI);
            //InputStream account = new ByteArrayInputStream(config.getBytes());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(FIREBASE_DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);
        }
        catch (Exception e){

        }
    }

    @Override
    public boolean sendEventToUsersByProduct(Product product) {
        List<String> tokens = userDao.findAll()
                                .stream()
                                .filter(user -> notifiersMatchProduct(user.getNotifiers(), product))
                                .map(User::getFirebaseToken)
                                .collect(Collectors.toList());

        if (tokens.size() > 0) {
            MulticastMessage messages = MulticastMessage.builder()
                    .putData(PRODUCT_ID_KEY, String.valueOf(product.getId()))
                    .setNotification(new Notification(CREATE_EVENT_PUSH_TITLE, CREATE_EVENT_PUSH_BODY))
                    .addAllTokens(tokens)
                    .build();

            try{
                BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(messages);
                LOGGER.info("Successfully sent message: " + response.toString());
            }
            catch (FirebaseMessagingException e){
                LOGGER.error(e.toString());
            }
        }

        return true;
    }

    private boolean notifiersMatchProduct(Set<Notifier> notifiers, Product product) {
        Set<Location> userLocations = notifiers
                .stream()
                .filter(notifier -> notifier.getItemType().getId() == product.getItemType().getId())
                .flatMap(notifier -> notifier
                        .getLocations()
                        .stream())
                .collect(Collectors.toSet());

        for (Location userLocation: userLocations)
            for (Location productLocation: product.getLocations())
                if (userLocation.daysOverlap(productLocation) &&
                        userLocation.hoursOverlap(productLocation) &&
                        userLocation.calculateDistance(productLocation) < 1000.0
                )
                    return true;

        return false;
    }
}
