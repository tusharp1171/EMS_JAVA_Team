package com.example.usermanagement.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.model.UserCredential;
import com.example.usermanagement.model.UserEducationDetails;
import com.example.usermanagement.model.UserRoleMapper;
import com.example.usermanagement.model.UserType;
import com.example.usermanagement.model.Users;
import com.example.usermanagement.repository.UserAdressesRepository;
import com.example.usermanagement.repository.UserCredentialRepository;
import com.example.usermanagement.repository.UserEducationDetailsRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.repository.UserRoleMapperRepository;
import com.example.usermanagement.repository.UserTypeRepository;
import com.example.usermanagement.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private UserAdressesRepository userAddressesRepository;

	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@Autowired
	private UserEducationDetailsRepository userEducationDetailsRepository;

	@Autowired
	private UserRoleMapperRepository userRoleMapperRepository;

	@Override
	// @Transactional(readOnly = true)
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	// @Transactional(readOnly = true)
	public Users getUserById(int id) throws UserNotFoundException {
		Optional<Users> user = userRepository.findById(id);
		if (user.isPresent()) {
			Users users = user.get();
			return users;
		} else {
			throw new UserNotFoundException("User does not exist");
		}
	}

	@Override
	public Users createUser(@Valid Users user) {
				handleUserType(user);
		return this.userRepository.save(user);
	}

	@Override
	@Transactional
	public Users updateUser(Users user, int id) {
		Optional<Users> existingUserOptional = userRepository.findById(id);
		if (existingUserOptional.isPresent()) {
			Users existingUser = existingUserOptional.get();
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setDob(user.getDob());

			handleUserType(user);
			existingUser.setUserType(user.getUserType());

			existingUser.setEducationDetails(user.getEducationDetails());
			existingUser.setUserRoleMapper(user.getUserRoleMapper());
			existingUser.setUserCredential(user.getUserCredential());
			existingUser.setUserAdresses(user.getUserAdresses());

			return userRepository.save(existingUser);
		} else {
			throw new IllegalArgumentException("User with ID " + id + " not found");
		}
	}

	@Override
	@Transactional
	public Users deleteUserRecord(int id) {
		Optional<Users> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			Users user = userOptional.get();
			userRepository.delete(user);
			return user;
		} else {
			throw new IllegalArgumentException("User with ID " + id + " not found");
		}
	}

	@Transactional
	public UserAdresses createUserAddress(@Valid UserAdresses userAddresses) {
		return userAddressesRepository.save(userAddresses);
	}

	@Transactional
	public UserCredential createUserCredential(@Valid UserCredential userCredential) {
		return userCredentialRepository.save(userCredential);
	}

	@Transactional
	public UserEducationDetails createUserEducationDetails(@Valid UserEducationDetails userEducationDetails) {
		return userEducationDetailsRepository.save(userEducationDetails);
	}

	@Transactional
	public UserRoleMapper createUserRoleMapper(@Valid UserRoleMapper userRoleMapper) {
		return userRoleMapperRepository.save(userRoleMapper);
	}

	public void handleUserType(@Valid Users user) {
	    UserType userType = user.getUserType();
	    if (userType != null) {
	        // Ensure UserType is managed by the session
	        if (userType.getId() != 0) {
	            // Fetch the existing UserType from the database
	            userType = userTypeRepository.findById(userType.getId())
	                .orElseThrow(() -> new RuntimeException("UserType not found"));
	        } else {
	            // Save the new UserType
	            userType = userTypeRepository.save(userType);
	        }
	        user.setUserType(userType); // Attach the managed UserType to the Users entity
	    }

	    // Save associated entities if needed
	    List<UserAdresses> userAdresses = user.getUserAdresses();
	    if (userAdresses != null) {
	        userAddressesRepository.saveAll(userAdresses);
	    }

	    List<UserCredential> userCredential = user.getUserCredential();
	    if (userCredential != null) {
	        userCredentialRepository.saveAll(userCredential);
	    }

	    List<UserEducationDetails> userEducationDetails = user.getEducationDetails();
	    if (userEducationDetails != null) {
	        userEducationDetailsRepository.saveAll(userEducationDetails);
	    }

	    List<UserRoleMapper> userRoleMapper = user.getUserRoleMapper();
	    if (userRoleMapper != null) {
	        userRoleMapperRepository.saveAll(userRoleMapper);
	    }
	}
}