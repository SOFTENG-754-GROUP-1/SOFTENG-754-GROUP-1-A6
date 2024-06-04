package nz.ac.auckland.se754.web.service;

public interface Database {

    String getPassword(String name);
    Boolean checkUsernameExists(String username);
}
