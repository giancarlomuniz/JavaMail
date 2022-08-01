package br.com.enviar_mail;








/**
 * Unit test for simple App.
 */
public class AppTest {
	

	
@org.junit.Test


public void testeEmail() throws Exception {
	
	StringBuilder builderTexto = new StringBuilder();
	
	builderTexto.append("Olá, <br/><br/>");
	builderTexto.append("Você está recebendo o acesso ao curso de Java.<br/><br/>");
	builderTexto.append("Para ter acesso clique no botão abaixo.<br/><br/>");
	
	builderTexto.append("<b>Login:</b> alex@jsjsjsj.com<br/>");
	builderTexto.append("<b>Senha:</b> sdss8s98s<br/><br/>");
	
	builderTexto.append("<a target=\"_blank\" href=\"http://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7; padding: 14px 25px; text-align:center; text-decoration: none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border : 3px solid green;background-color:#99DA39;\">Acessar Portal do Aluno</a><br/><br/>");
	
	builderTexto.append("<span style=\"font-size:8px\">Ass.: Alex do JDev Treinamento</span>");
	
	
	ObjetoEnviarEmail enviarEmail = new ObjetoEnviarEmail("munizgiancarlo@gmail.com", 
			"Giancarlo Muniz", 
			"Enviar email", 
			builderTexto.toString());
	
	enviarEmail.enviarEmail(true);
}

	
	
}
