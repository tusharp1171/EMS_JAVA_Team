package com.example.usermanagement.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.repository.UserAdressesRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserAdressesService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserAdressesServiceImpl implements UserAdressesService {

	@Autowired
	UserAdressesRepository adressesRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserAdresses addUserAdresses(UserAdresses userAdresses) {

		return adressesRepository.save(userAdresses);
	}

	@Override
	public List<UserAdresses> getAlluserAdresses() {
		return adressesRepository.findAll();

	}

	@Override
	  @Transactional
	    public void deleteUserAdressesByUserId(Long userId) throws UserNotFoundException {
	        List<UserAdresses> addresses = adressesRepository.findByUserId(userId);
	        if (addresses.isEmpty()) {
	            throw new UserNotFoundException("No addresses found for user ID: " + userId);
	        }
	        for (UserAdresses address : addresses) {
	        	adressesRepository.deleteById(address.getId());
	        }
	    }

	public List<UserAdresses> getUserAdressesByUserId(Long userId) throws UserNotFoundException {
		try {
			return adressesRepository.findByUserId(userId);
		} catch (Exception e) {
			throw new UserNotFoundException("Incorrect UserId");
		}

	}

//	@Override
//	public List<UserAdresses> getUserAdressesByUserId(Long userId) {
//		List<UserAdresses> add= adressesRepository.findByUserId(userId);
//		for(UserAdresses userasd:add)
//		{
//			adressesRepository.deleteById(userasd.getId());
//		}
//	return null;
//    }

	// @Override
//	 public UserAdresses deleteUserAddress(Long userId) {
//        List<UserAdresses> userAddressesList = adressesRepository.findByUserId(userId);
//        if (!userAddressesList.isEmpty()) {
//            userAddressesRepository.deleteAll(userAddressesList);
//            return userAddressesList;
//        }
//        return null;
//        List<UserAdresses> userAddressesList = adressesRepository.findByUserId(userId);
//        if (!userAddressesList.isEmpty()) {
//        	adressesRepository.deleteByUserId(userId);
//            return userAddressesList;
//        }
//        return null;

}
