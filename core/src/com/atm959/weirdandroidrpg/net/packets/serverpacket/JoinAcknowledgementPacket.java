package com.atm959.weirdandroidrpg.net.packets.serverpacket;

public class JoinAcknowledgementPacket extends ServerPacket{
	public boolean ableToJoin;
	public byte clientID;

	public JoinAcknowledgementPacket(byte[] data){
		if(data[0] != 0x00) throw new RuntimeException("Bad packet ID");
		ableToJoin = (data[1] == 0x01) ? true : false;
		clientID = data[2];
	}
}
