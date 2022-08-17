package codeit.cakeN.domain.user.profileImg;

import codeit.cakeN.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FileRepository {

    private final Map<Long, File> store = new HashMap<>();

    public File save(User user, File file) {
        Long userId = user.getUserId();
        file.setId(userId);
        store.put(userId, file);

        return file;
    }


    public File findById(Long id) {
        return store.get(id);
    }
}
