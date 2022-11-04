package sri.business;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sri.dao.UsuarioQueryHelper;
import sri.model.Usuario;

@Stateless(mappedName = "LoginService")
public class LoginService {

	@PersistenceContext(unitName="primary")
	private EntityManager em;

	private UsuarioQueryHelper usuarioQueryHelper;

	// here I initialize the UsuarioQueryHelper with the EntityManager 
	// and guarantee it only create one instance 
	// per bean . I always execute ensureHelper first of all querys. The
	// idea is consume EntityManager in Helpers classes
	private UsuarioQueryHelper ensureHelper(){
		if (usuarioQueryHelper == null) {
			this.usuarioQueryHelper = new UsuarioQueryHelper(em);

		}
		return this.usuarioQueryHelper;
	}



	/*
	 * do login ; true for success
	 * @param String login
	 * @param String pass
	 * @return boolean
	 */
	public boolean doLogin(String login, String pass){
		Usuario usuario = ensureHelper().getUsuario(login, pass);
		if (usuario == null) {return false;}
		else {
			return true;
		}
	}

	/*
	 * **************************** DELETE ME ***************************
	 * SECURITY FLAW RISC ; THESE IS JUST FOR TESTING
	 * 
	 */
	public void createFirst(){
		System.out.println("\n\n\n CREATING USER TO CHECK ALL STUFFS!");
		Usuario usuario = new Usuario();
		usuario.setLogin("test"); // login
		usuario.setSenha("123"); // password
		getEm().persist(usuario);
		System.out.println("\n\n\n YOU CAN LOGIN FOR TEST "+usuario.getLogin() + " " + usuario.getSenha());
		System.out.println("********************************** IMPORTANT MESSAGE **********************************");
		System.out.println("*******************REMOVE createFirst() METHOD AND DELETE THESE USER*******************");
		System.out.println("********************************** IMPORTANT MESSAGE **********************************");
	}


	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}



}
