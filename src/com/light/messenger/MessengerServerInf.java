package com.light.messenger;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.light.vo.DiaVO;
import com.light.vo.FdVO;
import com.light.vo.GbkVO;
import com.light.vo.HpVO;
import com.light.vo.ImgVO;
import com.light.vo.MemVO;
import com.light.vo.MsgVO;



public interface MessengerServerInf extends Remote {
	
	public void contactList (String id, MessengerClientInf client) throws RemoteException; //로그인성공시 접속자리스트
	public boolean loginCheck(String id, String pw) throws RemoteException;//로그인체크
	public List<String> search(String id, String user) throws RemoteException; //회원검색
	public List<FdVO> getUserList(String id) throws RemoteException; //친구목록
	public void setMsg(String snedUser,String receiveUser, String content) throws RemoteException; //쪽지
	
	
	
	public ArrayList<MessengerClientInf> clientList(String userId)throws RemoteException;
	
	public ArrayList<String> userOnOff(String UserId) throws RemoteException; //유저 온오프
	
	public void userlogOut(String userId) throws RemoteException; //로그아웃 
	
    public List<String> connectionUser(String userId) throws RemoteException; // 접속한 유저 리스트
	
	public void inviate(String sendId, String receveId, int ChatIdx)throws RemoteException; // 채팅초대
	
	public int makeNewChat(String myId, String friendId) throws RemoteException; //채팅방만들기
	
	public void joinChat(int check, int newChatIdx, String friendId, ArrayList<MessengerClientInf> chatList, ArrayList<String> chatUserList ) throws RemoteException; // 채팅방참여
	
	public void setChatMsg(int newChatIdx, String msg) throws RemoteException; //채팅메세지
	
	public int  chatIdx(String id) throws RemoteException; //채팅방번호
	
	public void chatExit(String userId,int chatIdx) throws RemoteException; //채팅종료
	
	
	
	
	
	
	
	public boolean nicCheak(String nic) throws RemoteException;//닉네임체크
	public boolean idCheak(String id)throws RemoteException;//id체크
	public void insertData(String id , String pw, String nic, String email , String name,
			String address, String hp, String bir, String gen)throws RemoteException; //회원가입
	public String idReturn(String email, String name)throws RemoteException; //id보내기
	public String pwReturn(String email, String name, String id)throws RemoteException; // pw보내기
	public void userSave(String id, String user)throws RemoteException; //친구저장
	public List<MemVO> selectMem() throws RemoteException;// 회원 전체목록 선택
	public List<MemVO> selectMem(String values) throws RemoteException; // 선택 회원목록출력
	public void update(MemVO vo) throws RemoteException;//회원정보 업데이트
	public void deleteDia(String num) throws RemoteException; //다이어리 삭제
	public void deletegbk(String num) throws RemoteException; //방명록 삭제
	public void deleteimg(String num) throws RemoteException; //사진첩 삭제
	public List<DiaVO> setTabledata(DiaVO vo) throws RemoteException; //선택 다이어리 목록 
	public List<GbkVO> setTabledata(GbkVO vo) throws RemoteException; // 선택 방명록 목록
	public List<ImgVO> setTabledata(ImgVO vo) throws RemoteException; // 선택 사진첩 목록
	public List<DiaVO> setTabledataDia() throws RemoteException; // 전체 다이어리 출력
	public List<GbkVO> setTabledataGbk() throws RemoteException; // 전체 방명록 출력
	public List<ImgVO> setTabledataimg() throws RemoteException; // 전체 사진첩 출력
	public List<HpVO> selectHp(HpVO vo) throws RemoteException; // 미니홈페이지~~~
	public List<MemVO> selectmemList(MemVO vo)throws RemoteException; // 선택 멤버정보 리스트
	public void update(HpVO vo) throws RemoteException;//홈페이지정보 업데이트
	public void update(GbkVO vo) throws RemoteException;//방명록정보 업데이트
	public void update(DiaVO vo) throws RemoteException;//다이어리정보 업데이트
	public void update(ImgVO vo) throws RemoteException;//사진첩정보 업데이트
	public void insert(GbkVO vo) throws RemoteException;//방명록 등록
	public void insert(DiaVO vo) throws RemoteException;//다이어리 등록
	public void insert(ImgVO vo) throws RemoteException;//사진첩 등록
	public void fileset(String id) throws RemoteException;
	public String fileinsert() throws RemoteException;
	public void fileUpdate(String id) throws RemoteException;
	public List<MsgVO> selectMsgList(MsgVO vo) throws RemoteException; // 쪽지불러오기
	public void deleteMsg(String num) throws RemoteException; // 쪽지삭제
	public void updateMsg(MsgVO vo) throws RemoteException; // 쪽지보관함 업데이트
	public void deleteFd(String fdRegnum) throws RemoteException; // 친구삭제
	
	//수남이형이 한거
	public void fileboo2(String UserId, String path) throws RemoteException;
	public void fileboo(String name) throws RemoteException;
	public String filenamert() throws RemoteException;
	public void sendImg(String filepath, final int rnd) throws RemoteException;
	
	
}
