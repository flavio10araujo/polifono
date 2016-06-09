package com.polifono.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polifono.domain.Login;
import com.polifono.domain.Player;
import com.polifono.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public final Login registerLogin(Player player) {
		Login login = new Login();
		login.setPlayer(player);
		login.setDtLogin(new Date());
		return loginRepository.save(login);
	}
	
	public final List<Login> findByPlayer(int playerId) {
		List<Login> list = loginRepository.findByPlayer(playerId); 
		
		if (list == null || list.size() == 0) {
			return null;
		}
		
		return list;
	}
}