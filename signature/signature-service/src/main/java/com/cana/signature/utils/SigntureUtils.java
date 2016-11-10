package com.cana.signature.utils;

import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.Signature;

public class SigntureUtils {

	
	public static Session initSignSession() throws PKIException{
		final String deviceName = JCrypto.JSOFT_LIB;
		JCrypto.getInstance().initialize(deviceName, null);
		Session session = JCrypto.getInstance().openSession(deviceName);
		
		return session;
	}
	
	public static Signature initSignture(){
		
		
		final Signature util = new Signature();
		return util;
	}
}
