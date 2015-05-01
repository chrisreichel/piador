package br.tur.reservafacil.piador.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeguidorDomainImpl
		implements SeguidorDomain {

	private Map<String, List<String>> repoSeguidores = new HashMap<>();
	
	public SeguidorDomainImpl(Map<String, List<String>> repoSeguidores) {
		super();
		this.repoSeguidores = repoSeguidores;
	}
	
	@Override public void seguir(String username, String seguidor) {
		if(!repoSeguidores.containsKey(username)){
			repoSeguidores.put(username, new ArrayList<String>());
		}
		repoSeguidores.get(username).add(seguidor);
	}

	@Override public List<String> listarSeguidores(String username) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Usuario inválido");
		}
		return repoSeguidores.get(username);
	}
}
