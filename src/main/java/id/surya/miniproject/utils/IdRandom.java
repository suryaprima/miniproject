package id.surya.miniproject.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Qualifier("randomId")
public class IdRandom implements RandomString{
    @Override
    public String random() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
