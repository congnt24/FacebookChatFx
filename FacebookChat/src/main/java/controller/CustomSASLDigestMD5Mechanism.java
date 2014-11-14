package controller;
import java.io.IOException;

import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;

import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.sasl.SASLMechanism;


public class CustomSASLDigestMD5Mechanism extends SASLMechanism {

	public CustomSASLDigestMD5Mechanism(SASLAuthentication saslAuthentication) {
		super(saslAuthentication);
		
	}
	
	//http://www.igniterealtime.org/builds/smack/docs/3.4.0/javadoc/org/jivesoftware/smack/sasl/SASLMechanism.html#authenticate%28java.lang.String,%20java.lang.String,%20java.lang.String,%20java.lang.String%29

		@Override
		public void authenticate(String username, String host, CallbackHandler cbh)
				throws IOException, XMPPException {
			String[] mechanisms={getName()};
			this.sc = Sasl.createSaslClient(mechanisms, null, "xmpp", host, null, cbh);
			super.authenticate(username, host, cbh);
		}


		@Override
		public void authenticate(String username, String host, String password)
				throws IOException, XMPPException {
			// TODO Auto-generated method stub
			this.authenticationId=username;
			this.password=password;
			this.hostname=host;
			String[] mechanisms={getName()};
			this.sc=Sasl.createSaslClient(mechanisms, null, "xmpp", host, null, this);
			super.authenticate(username, host, password);
		}

	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return "DIGEST-MD5";
	}

}
