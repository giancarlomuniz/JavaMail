package br.com.enviar_mail;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.MultipartDataSource;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
	
MimeBodyPart corpoEmail = new MimeBodyPart();

//PRIMEIRA PARTE DO EMAIL

	if (envioHtml) {
		corpoEmail.setContent(textoEmail, "text/html; charset = utf-8");
	}else {
		corpoEmail.setText(textoEmail);
	}
	
// 2ª parte que é o anexo de documentos no email
	
MimeBodyPart anexoEmail = new MimeBodyPart();

anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorPDF(), "application/pdf")));
anexoEmail.setFileName("anexoEmail.pdf");
	
	//JUNTAR AS PARTE DO EMAIL
	Multipart multipart = new MimeMultipart();
	
	multipart.addBodyPart(corpoEmail);//PRIMEIRA PARTE
	multipart.addBodyPart(anexoEmail);//SEGUNDA PARTE
	
	//JUNTANDO AS PARTE DO EMAIL
	message.setContent(multipart);

	//CLASSE RESPONSAVEL PARA TRANSPORTAR A MENSAGEM
	Transport.send(message);
		

	      System.out.println("Feito!!!");
		
		
	}
	
	
	//CRIANDO ARQUIVO PDF PARA ANEXO NO EMAIL
	
	public FileInputStream simuladorPDF()throws Exception{

		 Document document = new Document();//cria documento
		 File file = new File("fileAnexo.pdf");//pega o Arquivo
		file.createNewFile();//criar o arquivo
		 PdfWriter.getInstance(document, new FileOutputStream(file));//Pega o arquivo e joga no ducumento
		 document.open();
		 document.add(new Paragraph("Conteudo do anexo gerado pelo pdf"));
		 document.close();
		 
		 return new FileInputStream(file);
	
	}
	
	

}
