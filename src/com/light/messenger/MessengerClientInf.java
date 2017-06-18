package com.light.messenger;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MessengerClientInf extends Remote {
	public void setMsg(String snedUser, String receiveUser, String content) throws RemoteException; //쪽지
	
	public void chatInvite(int newChatIdx, String myId, String UserId, ArrayList<MessengerClientInf> chatList, ArrayList<String> chatUserList) throws RemoteException; //채팅 초대
	
	public void setChatMsg(String msg) throws RemoteException; //채팅메세지
	
	public void chatLoad(int chatIdx)throws RemoteException;
	
	public void resivefile(String run) throws RemoteException;
	
	public void getUserList() throws RemoteException;
}
