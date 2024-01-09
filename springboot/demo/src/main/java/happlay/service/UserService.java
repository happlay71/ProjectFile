package happlay.service;

import happlay.entity.User;
import happlay.util.R;

public interface UserService {
    User select(int id);

    R login(String username, String password);

    R register(String username, String password);

    R delete(String username);

    R update(String oldusername, String username, String password);
}
