package com.atm959.weirdandroidrpg.net.packets.clientpacket;

public class LeavePacket {
	public static byte[] build(byte clientID){
		return new byte[]{
			0x01, clientID
		};
	}
}
