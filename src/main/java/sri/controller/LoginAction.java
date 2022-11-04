package sri.controller;

import sri.model.Usuario;
import sri.exceptions.UsuarioNAutorizado;

public class LoginAction extends BaseAction{

	private String login;
	
	private String senha;
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String login(){
		if (getLoginService().doLogin(getLogin(), getSenha())) {
			return "valid";
		}
		else {
			addActionError("INVALID LOGIN / PASSWORD");
			return "invalid";
		}
		
	}
	
	
}
