package com.atm959.weirdandroidrpg.net;

import com.atm959.weirdandroidrpg.net.packets.clientpacket.HeartbeatPacket;
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
	public boolean isConnecting;
	public boolean isConnected;
	public byte clientID;
	public boolean ableToJoin;
	private InetAddress address;
	private final int serverPort;
	private final byte[] buff;
	private DatagramSocket socket;
	private HeartbeatThread heartbeatThread;

	public Server(String hostName, int port) {
		try {
			address = InetAddress.getByName(hostName);
			socket = new DatagramSocket();
			socket.setSoTimeout(1000);
			heartbeatThread = new HeartbeatThread(this);
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
		serverPort = port;
		buff = new byte[1024];
		isConnecting = false;
		isConnected = false;
	}

	public void connect() {
		(new ConnectThread(this)).start();
	}

	public void doConnect() {
		byte[] joinPacket = JoinPacket.build();
		sendPacket(joinPacket);

		byte[] serverResponse = receivePacket();
		if (serverResponse != null) {
			JoinAcknowledgementPacket joinAcknowledgementPacket = new JoinAcknowledgementPacket(serverResponse);
			clientID = joinAcknowledgementPacket.clientID;
			ableToJoin = joinAcknowledgementPacket.ableToJoin;
			Gdx.app.log("CLIENT_ID", Byte.toString(clientID));
			startHeartbeatThread();
			isConnecting = false;
			isConnected = true;
		} else {
			isConnecting = false;
			isConnected = false;
		}
	}

	public void disconnect() {
		isConnected = false;
		stopHeartbeatThread();
		byte[] leavePacket = LeavePacket.build(clientID);
		sendPacket(leavePacket);
		socket.close();
	}

	public void sendPacket(byte[] data) {
		try {
			DatagramPacket packet = new DatagramPacket(data, data.length, address, serverPort);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] receivePacket() {
		try {
			DatagramPacket packet = new DatagramPacket(buff, buff.length);
			socket.receive(packet);
			Gdx.app.log("PACKET", "Length: " + packet.getLength());
			return packet.getData();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void startHeartbeatThread() {
		heartbeatThread.start();
	}

	private void stopHeartbeatThread() {
		heartbeatThread.end();
	}
}

class ConnectThread extends Thread {
	Server server;

	public ConnectThread(Server server) {
		this.server = server;
	}

	public void run() {
		server.isConnecting = true;
		server.doConnect();
	}
}

class HeartbeatThread extends Thread {
	Server server;
	boolean keepRunning;

	public HeartbeatThread(Server server) {
		this.server = server;
		keepRunning = true;
	}

	public void run() {
		while (keepRunning) {
			try {
				byte[] heartbeatPacket = HeartbeatPacket.build(server.clientID);
				server.sendPacket(heartbeatPacket);

				byte[] ack = server.receivePacket();
				if(ack == null){
					server.isConnected = false;
				}
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void end() {
		keepRunning = false;
	}
}