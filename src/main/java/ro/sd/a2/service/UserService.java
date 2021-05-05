package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.Role;
import ro.sd.a2.entity.User;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.repository.UserRepository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * This method adds a user to the database.
     * @param user The user that has to be inserted.
     */
    public void addUser(User user) {
        userRepository.save(user);
        log.info("User has been successfully added!");
    }

    /**
     * This method updates a user in the database.
     * @param userDto The user that has to be updated.
     */
    public void updateUser(UserDto userDto) {
        userRepository.save(getUser(userDto.getId()));
        log.info("User has been successfully updated!");
    }

    /**
     *
     * @param username The username of a user.
     * @return This method returns the user with the provided username.
     * @throws NullPointerException if there is no user with the provided username.
     */
    public UserDto getUserByUsername(String username) throws NullPointerException{
        log.info("Method getUserByUsername has been called!");
        return UserMapper.entityToDto(userRepository.findByUsername(username));
    }

    /**
     *
     * @param username The username of a user.
     * @param password The password of a user.
     * @return his method returns the user with the provided username and password.
     * @throws NoResultException if there is no user with the provided username and password.
     */
    public UserDto getUserByUsernameAndPassword(String username, String password) throws NoResultException {
        User user = userRepository.findByUsernameAndPassword(username, password);
        log.info("Method getUserByUsernameAndPassword has been called!");
        return UserMapper.entityToDto(user);
    }

    /**
     *
     * @return This method return the users that has the role CLIENT.
     */
    public List<UserDto> getClients(){
        List<User> userList = userRepository.findByRole(Role.CLIENT);
        List<UserDto> userDtos = new ArrayList<>();
        for(User user: userList){
            userDtos.add(UserMapper.entityToDto(user));
        }
        log.info("Clients has been returned!");
        return userDtos;
    }

    /**
     * This method removes a user from the database.
     * @param id The id of the user that has to be removed.
     */
    public void removeUser(String id){
        userRepository.delete(userRepository.getOne(id));
        log.info("User has been successfully deleted!");
    }

    /**
     *
     * @param id The id of a user.
     * @return This method returns the UserDto that has the provided id.
     */
    public UserDto getUserById(String id){
        log.info("Method getUserById has been called!");
        return UserMapper.entityToDto(userRepository.getOne(id));
    }

    /**
     *
     * @param id The id of a user.
     * @return This method returns the user that has the provided id.
     */
    public User getUser(String id){
        log.info("Method getUser has been called!");
        return userRepository.getOne(id);
    }
}
