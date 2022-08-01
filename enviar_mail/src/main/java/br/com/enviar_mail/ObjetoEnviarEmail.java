package br.com.enviar_mail;


import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ObjetoEnviarEmail {
	//DECLARAÇÃO DE VARIAVEIS
	private String userName ="giancarlomuniz12@gmail.com";
	private String senha = "ngbiishdaifzgdty";
	
	private String listaDestinatarios ="";
	private String nomeRemetetnte ="";
	private String assuntoEmail ="";
	private String textoEmail ="";
	// CONSTRUTOR COM PARAMETRO
	public ObjetoEnviarEmail(String listaDestinatarios,String nomeRemetetnte,String assuntoEmail,String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetetnte = nomeRemetetnte;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}
	// 1º METODO PROPRIEDADE DE ENVIO, DECLARAÇÃO DE CONEXAO COM SERVIDOR DE SMTP
	public void enviarEmail(boolean envioHtml) throws Exception {
	
		
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*");//Propriedade de segurança
		properties.put("mail.smtp.auth", "true");/*Autorização*/
		properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
		properties.put("mail.smtp.host", "smtp.gmail.com"); /*Sercidor gmail Google*/
		properties.put("mail.smtp.port", "465");/*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao SMTP*/
		
		
		//2º SESSÃO DE AUTETNTICAÇÃO DE CONEXAO, VERIFICANDO O USUARIO COM SENHA
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);// Usuario e senha do remetente
			}
		});
		// 3º ROTINA DE ESTABELECER DESTINATARIOS
	Address []toUser = InternetAddress.parse(listaDestinatarios);
// 4º CORPO DO EMAIL
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(userName,nomeRemetetnte));
	message.setRecipients(Message.RecipientType.TO, toUser);
	message.setSubject(assuntoEmail);
	
	if (envioHtml) {
		message.setContent(textoEmail, "text/html; charset = utf-8");
	}else {
		message.setText(textoEmail);
	}
	

	//CLASSE RESPONSAVEL PARA TRANSPORTAR A MENSAGEM
	Transport.send(message);
		

	      System.out.println("Feito!!!");
		
		
	}
	
	
	

}
