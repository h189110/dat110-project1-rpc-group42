package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		byte rpcid = (byte) Common.WRITE_RPCID;
		byte[] request = RPCUtils.marshallString(message);

		byte[] reply = rpcclient.call(rpcid, request);
		RPCUtils.unmarshallVoid(reply);
		
	}
}
