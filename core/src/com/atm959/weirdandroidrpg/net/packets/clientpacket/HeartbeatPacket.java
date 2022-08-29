package com.atm959.weirdandroidrpg.net.packets.clientpacket;

public class HeartbeatPacket {
	public static byte[] build(byte clientID) {
		return new byte[]{
			0x02, clientID
		};
	}
}
