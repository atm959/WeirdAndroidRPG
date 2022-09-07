package com.atm959.weirdandroidrpg.net.packets.serverpacket;

public class JoinAcknowledgementPacket extends ServerPacket {
	public boolean ableToJoin;
	public byte clientID;

	public JoinAcknowledgementPacket() {
		packetID = 0;
	}

	public JoinAcknowledgementPacket(byte[] data) {
		ableToJoin = data[1] == 0x01;
		clientID = data[2];
	}
}