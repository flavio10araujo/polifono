package com.polifono.util;

import org.apache.commons.mail.MultiPartEmail;

public class EmailSendUtil {
	
	public static void main(String args[]) {
		try {
			String[] args2 = new String[3];
			args2[0] = "João Tavares";
			args2[1] = "joaotavares@gmail.com";
			args2[2] = "666999";
			EmailSendUtil.sendHtmlMail(1, "flavio10araujo@yahoo.com.br", args2);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendHtmlMail(int messageType, String to, String[] args) throws Exception {
		String from = "", subject = "", message = "";
		
		if (messageType == 1) {
			from = "new@polifono.com";
			subject = "Confirme seu cadastro na Polifono.com";
			message = "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"550\" summary=\"\">"
					+ "<thead><tr><td align=\"center\"><font color=\"#ff8533\" face=\"arial\" size=\"+2\"><b>POLIFONO.COM</b></font><hr size=\"3\" color=\"#ff8533\"></td></tr></thead><tbody><tr><td>"
					+ "<p><font color=\"#ff8533\" face=\"arial\" size=\"+1\"><b>Olá {0},</b></font></p>"
					+ "<p><font face=\"arial\" size=\"2\">Você iniciou seu cadastro na plataforma Polifono.com com o e-mail <a href=\"#\">{1}</a>. Para finalizá-lo, precisamos que você valide seu email.</font></p>"
					+ "<p><font face=\"arial\" size=\"2\">Para validar:</font></p>"
					+ "<p><font face=\"arial\" size=\"2\">- Acesse <a href=\"http://www.polifono.com/emailconfirmation\">www.polifono.com/emailconfirmation</a></font></p>"
					+ "<p><font face=\"arial\" size=\"2\">- Informe o e-mail cadastrado;</font></p>"
					+ "<p><font face=\"arial\" size=\"2\">- Informe o seguinte código de ativação: <b>{2}</b></font></p>"
					+ "</td></tr><tr><td>"
					+ "<font face=\"arial\" size=\"-1\"><br>Atenciosamente,<br>Equipe Polifono.com<br /><br /><strong>Polifono.com</strong> - A vida sem música seria um engano.</font>"
					+ "</td></tr><tr><td align=\"center\">"
					+ "<hr size=\"2\" color=\"#EFEFEF\"><font face=\"arial\" size=\"-1\">DÚVIDAS? Acesse <a href=\"http://www.polifono.com/faq\">www.polifono.com/faq</a></font>"
					+ "</td></tr><tr><td align=\"center\"><hr size=\"2\" color=\"#EFEFEF\"><font face=\"arial\" size=\"-1\">Este é um e-mail automático disparado pelo sistema. Favor não respondê-lo, pois esta conta não é monitorada.</font>"
					+ "</td></tr></tbody></table>";

			message = replaceParamsMessage(message, args);
		}
		else if (messageType == 2) {
			from = "resetpassword@polifono.com";
			subject = "Sua soliticação de alteração de senha na Polifono.com";
			message = "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"550\" summary=\"\">"
					+ "<thead><tr><td align=\"center\"><font color=\"#ff8533\" face=\"arial\" size=\"+2\"><b>POLIFONO.COM</b></font><hr size=\"3\" color=\"#ff8533\"></td></tr></thead><tbody><tr><td>"
					+ "<p><font color=\"#ff8533\" face=\"arial\" size=\"+1\"><b>Olá {0},</b></font></p>"
					+ "<p><font face=\"arial\" size=\"2\">Você (ou alguém) solicitou a alteração da senha na plataforma Polifono.com para o e-mail <a href=\"#\">{1}</a>.</font></p>"
					+ "<p><font face=\"arial\" size=\"2\">Para dar continuidade na alteraração da senha:</font></p>"
					+ "<p><font face=\"arial\" size=\"2\">- Acesse <a href=\"http://www.polifono.com/passwordreset\">www.polifono.com/passwordreset</a></font></p>"
					+ "<p><font face=\"arial\" size=\"2\">- Informe o e-mail cadastrado;</font></p>"
					+ "<p><font face=\"arial\" size=\"2\">- Informe o seguinte código de confirmação da alteração da senha: <b>{2}</b></font></p>"
					+ "<p><font face=\"arial\" size=\"2\">- Informe a nova senha desejada.</font></p>"
					+ "<p><font face=\"arial\" size=\"2\"><br /><br />Caso a alteração de senha não seja mais necessária, apenas ignore este e-mail.</font></p>"
					+ "</td></tr><tr><td>"
					+ "<font face=\"arial\" size=\"-1\"><br />Atenciosamente,<br />Equipe Polifono.com<br /><br /><strong>Polifono.com</strong> - A vida sem música seria um engano.</font>"
					+ "</td></tr><tr><td align=\"center\">"
					+ "<hr size=\"2\" color=\"#EFEFEF\"><font face=\"arial\" size=\"-1\">DÚVIDAS? Acesse <a href=\"http://www.polifono.com/faq\">www.polifono.com/faq</a></font>"
					+ "</td></tr><tr><td align=\"center\"><hr size=\"2\" color=\"#EFEFEF\"><font face=\"arial\" size=\"-1\">Este é um e-mail automático disparado pelo sistema. Favor não respondê-lo, pois esta conta não é monitorada.</font>"
					+ "</td></tr></tbody></table>";
			
			message = replaceParamsMessage(message, args);
		}
		else if (messageType == 3) {
			from = "polifono@polifono.com";
			subject = "Sua compra de créditos foi confirmada na Polifono.com";
			message = "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"550\" summary=\"\">"
					+ "<thead><tr><td align=\"center\"><font color=\"#ff8533\" face=\"arial\" size=\"+2\"><b>POLIFONO.COM</b></font><hr size=\"3\" color=\"#ff8533\"></td></tr></thead><tbody><tr><td>"
					+ "<p><font color=\"#ff8533\" face=\"arial\" size=\"+1\"><b>Olá {0},</b></font></p>"
					+ "<p><font face=\"arial\" size=\"2\">A sua compra foi confirmada e os créditos adquiridos já estão disponíveis em sua conta.</font></p>"
					+ "<p><font face=\"arial\" size=\"2\"><br />O total de créditos adquiridos foi de: <b>{1}</b></font></p>"
					+ "<p><font face=\"arial\" size=\"2\">Obs.: caso esteja logado no sistema, é necessário deslogar e logar novamente para que os novos créditos sejam mostrados.</b></font></p>"
					+ "<p><font face=\"arial\" size=\"2\"><br /><br />Não perca tempo e comece a estudar agora mesmo.</font></p>"
					+ "</td></tr><tr><td>"
					+ "<font face=\"arial\" size=\"-1\"><br />Atenciosamente,<br />Equipe Polifono.com<br /><br /><strong>Polifono.com</strong> - A vida sem música seria um engano.</font>"
					+ "</td></tr><tr><td align=\"center\">"
					+ "<hr size=\"2\" color=\"#EFEFEF\"><font face=\"arial\" size=\"-1\">DÚVIDAS? Acesse <a href=\"http://www.polifono.com/faq\">www.polifono.com/faq</a></font>"
					+ "</td></tr><tr><td align=\"center\"><hr size=\"2\" color=\"#EFEFEF\">"
					+ "</td></tr></tbody></table>";
			
			message = replaceParamsMessage(message, args);
		}
		
		new EmailSendUtil.MailThread(from, subject, message, to).start();
	}
	
	static class MailThread extends Thread {

		private String senderAddress;
		private String recipientAddress;
		private String subject;
		private String message;

		public MailThread(String senderAddress, String subject, String message, String recipientAddress) {
			this.senderAddress = senderAddress;
			this.subject = subject;
			this.message = message;
			this.recipientAddress = recipientAddress;
		}

		@Override
		public void run() {
			try	{
				MultiPartEmail hm = new MultiPartEmail();
				
				hm.setHostName("smtp.gmail.com");
				hm.setSslSmtpPort("587");
				hm.setSmtpPort(Integer.parseInt("587"));
				hm.setAuthentication("flavio10araujo@gmail.com", "pW1985google");
				hm.setTLS(true);
				hm.setCharset("UTF-8");
				
				hm.setSubject(subject);
				hm.setFrom(senderAddress);
				hm.addTo(recipientAddress);
				
				message = HTMLEntitiesUtil.unhtmlentities(message);
				message = HTMLEntitiesUtil.htmlentities(message);
				
				hm.addPart(message, org.apache.commons.mail.Email.TEXT_HTML);
				
				hm.send();
				
				System.out.println("Email enviado!");
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("An email was not sent: ", e);
			}
		}
	}
	
	/**
	 * This method get an email and replaces the strings into the email by the args strings.<br>
	 * Eg:<br>
	 * {0} will be replaced by the first argument into the array<br>
	 * {1} will be replaced by the second argument into the array
	 *
	 * @param bodyText the message
	 * @param args the string to replace into the email string.
	 * @return The new string email with the correct messages.
	 */
	public static String replaceParamsMessage(String message, String[] args) {
		for (int i = 0; i < args.length; i++) {
			message = message.replace("{"+i+"}", args[i]);
		}
		
		return message;
	}
}