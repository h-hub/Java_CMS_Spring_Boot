package com.hhub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hhub.model.User;
import com.hhub.model.dto.UserDto;
import com.hhub.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolesService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/* (non-Javadoc)
	 * @see com.hhub.service.IUserService#create(com.hhub.model.User)
	 */
	@Override
	public User create(User user) {
		
        return userRepository.save(user);
        
    }

	/* (non-Javadoc)
	 * @see com.hhub.service.IUserService#getAll()
	 */
	@Override
	public Iterable<User> getAll() {

        return userRepository.findAll();

	}

	/* (non-Javadoc)
	 * @see com.hhub.service.IUserService#changeStatus(java.lang.Integer, boolean)
	 */
	@Override
	public void changeStatus(Integer userId, boolean status) {
		
		userRepository.updateUserSetStatusById(status,userId);
		
	}

	/* (non-Javadoc)
	 * @see com.hhub.service.IUserService#getUserById(java.lang.Integer)
	 */
	@Override
	public Optional<User> getUserById(Integer userId) {

		return userRepository.findById(userId);
		
	}

	/* (non-Javadoc)
	 * @see com.hhub.service.IUserService#findUserByEmail(java.lang.String)
	 */
	@Override
	public User findUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
		
	}

	@Override
	public User createUser(UserDto userDto) {

		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		user.setRole(roleService.findByRole("EDITOR"));
		user.setStatus(true);
		
		return userRepository.save(user);
	}

}
