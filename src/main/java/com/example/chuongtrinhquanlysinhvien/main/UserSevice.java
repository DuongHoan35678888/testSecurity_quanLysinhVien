package com.example.chuongtrinhquanlysinhvien.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UserSevice implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    public List<User> listAll(){
        return userRepository.findAll();
    }

    public String save(User user) {
        Long id = System.currentTimeMillis();
        user.setId(id);
        userRepository.save(user);
        return "redirect:/users";
    }

    public User get(Long id) throws UserNotFoundException{
        User result = userRepository.findDistinctById(id);
        if (Objects.nonNull(result)) {
            return result;
        }

        throw new UserNotFoundException("Could not find any users with Id" + id);
    }

    public void delete(Long id) throws UserNotFoundException {
        User result = userRepository.findDistinctById(id);
        if (Objects.isNull(result)) {
            throw new UserNotFoundException("Could not find nay users with ID" + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findDistinctById(Long.valueOf(username));
        if (foundUser == null) {
            return null;
        }
        String name = foundUser.getMaSinhVien();
        String pass = foundUser.getPassword();
        return (UserDetails) new User(name, pass);
    }
}
