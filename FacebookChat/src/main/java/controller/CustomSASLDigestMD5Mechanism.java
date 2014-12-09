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
