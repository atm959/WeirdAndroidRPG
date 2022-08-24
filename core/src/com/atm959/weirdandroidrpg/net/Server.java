package com.atm959.weirdandroidrpg.net;

import com.atm959.weirdandroidrpg.net.packets.clientpacket.JoinPacket;
import com.atm959.weirdandroidrpg.net.packets.clientpacket.LeavePacket;
import com.atm959.weirdandroidrpg.net.packets.serverpacket.JoinAcknowledgementPacket;
import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by atm959 on 8/22/2022.
 */

//A server that the game can connect to
public class Server {
	private InetAddress address;
	private int serverPort;
	private byte[] buff;
	private DatagramSocket socket;

	public byte clientID;
	public boolean ableToJoin;

	public Server(byte[] ipAddress, int port){
		try {
			address = InetAddress.getByAddress(ipAddress);
			socket = new DatagramSocket();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
		serverPort = port;
		buff = new byte[1024];
	}

	public Server(String hostName, int port){
		try {
			address = InetAddress.getByName(hostName);
			socket = new DatagramSocket();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
		serverPort = port;
		buff = new byte[1024];
	}

	public void connect(){
		byte[] joinPacket = JoinPacket.build();
		sendPacket(joinPacket);

		byte[] serverResponse = receivePacket();
		JoinAcknowledgementPacket joinAcknowledgementPacket = new JoinAcknowledgementPacket(serverResponse);
		clientID = joinAcknowledgementPacket.clientID;
		ableToJoin = joinAcknowledgementPacket.ableToJoin;
		Gdx.app.log("CLIENT_ID", Byte.toString(clientID));
	}

	public void disconnect(){
		byte[] leavePacket = LeavePacket.build(clientID);
		sendPacket(leavePacket);
		socket.close();
	}

	public void sendPacket(byte[] data){
		try {
			DatagramPacket packet = new DatagramPacket(data, data.length, address, serverPort);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] receivePacket(){
		try {
			DatagramPacket packet = new DatagramPacket(buff, buff.length);
			socket.receive(packet);
			Gdx.app.log("PACKET", "Length: " + Integer.toString(packet.getLength()));
			return packet.getData();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
