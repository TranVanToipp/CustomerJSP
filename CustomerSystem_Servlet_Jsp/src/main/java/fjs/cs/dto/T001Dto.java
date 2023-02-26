package fjs.cs.dto;

public class T001Dto {
	public String USERID, PASSWORD;

	public T001Dto() {

	}

	public T001Dto(String uSERID, String pASSWORD) {
		super();
		USERID = uSERID;
		PASSWORD = pASSWORD;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
}
