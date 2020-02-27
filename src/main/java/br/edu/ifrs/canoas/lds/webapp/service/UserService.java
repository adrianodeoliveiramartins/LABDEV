package br.edu.ifrs.canoas.lds.webapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.webapp.domain.User;
import br.edu.ifrs.canoas.lds.webapp.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User save(User user) {
		User fetchedUser = this.getOne(user);
		if (fetchedUser == null) return null;

		fetchedUser.setName(user.getName());
		fetchedUser.setEmail(user.getEmail());
		fetchedUser.setSkill(user.getSkill());
		fetchedUser.setExperience(user.getExperience());
		return userRepository.save(fetchedUser);
	}

	public User getOne(User user) {
		Optional<User> optUser = userRepository.findById(user.getId());
		return optUser.isPresent()?optUser.get():null;
	}
}
