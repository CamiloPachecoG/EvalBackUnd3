package com.everis.prueba3.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.prueba3.models.Usuario;
import com.everis.prueba3.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuRepo;
	
	public Usuario save(Usuario usuario) {
		
		String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		usuario.setPassword(hashed);
		
		return usuRepo.save(usuario);
	}
	
	public boolean autenticacion(String email,String password) {
		Usuario usuario = usuRepo.findByEmail(email);
		
		if(usuario == null) {
			return false;
		}else {
			if(BCrypt.checkpw(password, usuario.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
	}

}
