package com.atm959.weirdandroidrpg.net.packets.serverpacket;

import java.util.ArrayList;

public class ServerPacket {
	public static ArrayList<ServerPacket> SERVER_PACKET_TYPES;
	public byte packetID;

	public ServerPacket() {
		packetID = -1;
	}

	public static void InitServerPacketTypes() {
		SERVER_PACKET_TYPES = new ArrayList<>();
		SERVER_PACKET_TYPES.add(new JoinAcknowledgementPacket());
		SERVER_PACKET_TYPES.add(new HeartbeatAcknowledgementPacket());
	}
}
