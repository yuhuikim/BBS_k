package bbs;

public class Bbs {
    private int bbsID;
    private String bbsTitle;
<<<<<<< HEAD
    private String userID; //학번
    
    private String userDept; //학과
    
    private String bbsDate;
    private String bbsContent;
    private int bbsAvailable;
/*    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
*/
    public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

    public int getBbsID() {
        return bbsID;
    }

	public void setBbsID(int bbsID) {
=======
    private String userID;
    private String bbsDate;
    private String bbsContent;
    private int bbsAvailable;
/*    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
*/
    public int getBbsID() {
        return bbsID;
    }

    public void setBbsID(int bbsID) {
>>>>>>> branch 'master' of https://github.com/yuhuikim/BBS_k.git
        this.bbsID = bbsID;
    }

    public String getBbsTitle() {
        return bbsTitle;
    }

    public void setBbsTitle(String bbsTitle) {
        this.bbsTitle = bbsTitle;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBbsDate() {
        return bbsDate;
    }

    public void setBbsDate(String bbsDate) {
        this.bbsDate = bbsDate;
    }

    public String getBbsContent() {
        return bbsContent;
    }

    public void setBbsContent(String bbsContent) {
        this.bbsContent = bbsContent;
    }

    public int getBbsAvailable() {
        return bbsAvailable;
    }

    public void setBbsAvailable(int bbsAvailable) {
        this.bbsAvailable = bbsAvailable;
    }

}