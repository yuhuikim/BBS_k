package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
    private Connection conn; // DB에 접근하는 객체
    private ResultSet rs; // DB data를 담을 수 있는 객체

    // db랑 연결해주기
    public BbsDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
            String dbID = "root";
            String dbPassword = "rootpw";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 현재시간을 가져오는 함수
    public String getDate() {
        String SQL = "SELECT NOW()"; // 현재시간을 나타내는 mysql
        try {
            // BbsDAO함수는 여러 개의 함수가 사용되기 때문에 각각 함수끼리 데이터베이스 접근에 있어서 마찰이 일어나지 않도록 하기 위해
            // PreparedStatement를 안쪽에 넣어준다.
            // 현재 연결되어있는 객체를 이용해서 SQL 문장을 실행준비단계로 만들어준다.
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            // rs로 실행 결과를 가져온다.
            rs = pstmt.executeQuery();

            // 결과가 있는 경우
            if (rs.next()) {
                // 현재의 날짜를 그대로 반환할 수 있게 해준다.
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ""; // 데이터베이스 오류
    }

    // 게시글 번호
    public int getNext() {
        // 내림차순으로 가장 마지막에 쓰인 것을 가져온다
        String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 그 다음 게시글의 번호
                return rs.getInt(1) + 1;
            }
            return 1; // 첫 번째 게시물인 경우
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    // 게시글 작성함수
<<<<<<< HEAD
    public int write(String bbsTitle, String userID, String userDept,String bbsContent) {
        String SQL = "INSERT INTO BBS VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext());
            pstmt.setString(2, bbsTitle);
            pstmt.setString(3, userID);
            pstmt.setString(4, userDept);
            pstmt.setString(5, getDate());
            pstmt.setString(6, bbsContent);
            pstmt.setInt(7, 1); // available이니까 처음에 글을 작성했을 때 보여지는 형태가 되어야 하기 때문에 1을 넣어주어야 한다.

            // insert가 성공적으로 수행했다면 0이상의 값이 반환된다.
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    public ArrayList<Bbs> getList(int pageNumber) {
        String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10"; // 내림차순으로 //
                                                                                                            // 마지막에 쓰인

        ArrayList<Bbs> list = new ArrayList<Bbs>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Bbs bbs = new Bbs();
                bbs.setBbsID(rs.getInt(1));
                bbs.setBbsTitle(rs.getString(2));
                bbs.setUserID(rs.getString(3));
                bbs.setUserDept(rs.getString(4));
                bbs.setBbsDate(rs.getString(5));
                bbs.setBbsContent(rs.getString(6));
                bbs.setBbsAvailable(rs.getInt(7));

                // 리스트에 해당 인스턴스를 담아준다.
                list.add(bbs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // getList를 실행했을 때 특정한 페이지에 맞는 게시글 리스트가 담겨서 반환이 된다.
        return list;
    }

    // 페이징 처리를 위한 함수 - 10단위로 게시글이 끊긴다고 하면 다음 페이지, 이전 페이지 버튼을 만들어주기 위해서
    public boolean nextPage(int pageNumber) {
        String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 다음 페이지로 넘어갈 수 있다는 것을 알려줌
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 다음 페이지로 넘어가지 않아도 된다는 것을 알려줌
        return false;
    }

    // 하나의 글 내용을 불러오는 함수를 추가해준다.
    public Bbs getBbs(int bbsID) // 특정한 아이디에 해당하는 게시글을 그대로 가져올 수 있도록 한다.
    {
        String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, bbsID);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                Bbs bbs = new Bbs();

                bbs.setBbsID(rs.getInt(1));
                bbs.setBbsTitle(rs.getString(2));
                bbs.setUserID(rs.getString(3));
                bbs.setUserDept(rs.getString(4));
                bbs.setBbsDate(rs.getString(5));
                bbs.setBbsContent(rs.getString(6));
                bbs.setBbsAvailable(rs.getInt(7));
=======
    public int write(String bbsTitle, String userID, String bbsContent) {
        String SQL = "INSERT INTO BBS VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext());
            pstmt.setString(2, bbsTitle);
            pstmt.setString(3, userID);
            pstmt.setString(4, getDate());
            pstmt.setString(5, bbsContent);
            pstmt.setInt(6, 1); // available이니까 처음에 글을 작성했을 때 보여지는 형태가 되어야 하기 때문에 1을 넣어주어야 한다.

            // insert가 성공적으로 수행했다면 0이상의 값이 반환된다.
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    public ArrayList<Bbs> getList(int pageNumber) {
        String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10"; // 내림차순으로 //
                                                                                                            // 마지막에 쓰인

        ArrayList<Bbs> list = new ArrayList<Bbs>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Bbs bbs = new Bbs();
                bbs.setBbsID(rs.getInt(1));
                bbs.setBbsTitle(rs.getString(2));
                bbs.setUserID(rs.getString(3));
                bbs.setBbsDate(rs.getString(4));
                bbs.setBbsContent(rs.getString(5));
                bbs.setBbsAvailable(rs.getInt(6));

                // 리스트에 해당 인스턴스를 담아준다.
                list.add(bbs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // getList를 실행했을 때 특정한 페이지에 맞는 게시글 리스트가 담겨서 반환이 된다.
        return list;
    }

    // 페이징 처리를 위한 함수 - 10단위로 게시글이 끊긴다고 하면 다음 페이지, 이전 페이지 버튼을 만들어주기 위해서
    public boolean nextPage(int pageNumber) {
        String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 다음 페이지로 넘어갈 수 있다는 것을 알려줌
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 다음 페이지로 넘어가지 않아도 된다는 것을 알려줌
        return false;
    }

    // 하나의 글 내용을 불러오는 함수를 추가해준다.
    public Bbs getBbs(int bbsID) // 특정한 아이디에 해당하는 게시글을 그대로 가져올 수 있도록 한다.
    {
        String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, bbsID);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                Bbs bbs = new Bbs();

                bbs.setBbsID(rs.getInt(1));
                bbs.setBbsTitle(rs.getString(2));
                bbs.setUserID(rs.getString(3));
                bbs.setBbsDate(rs.getString(4));
                bbs.setBbsContent(rs.getString(5));
                bbs.setBbsAvailable(rs.getInt(6));
>>>>>>> branch 'master' of https://github.com/yuhuikim/BBS_k.git

                // 6개의 변수를 다 받은 다음에 bbs인스턴스에 넣어서 getBbs함수를 불러낸 대상한테 반환해준다.
                return bbs;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 해당 글이 존재하지 않는 경우에는 null을 반환한다.
    }

    // 게시글 수정하는 update함수
    public int update(int bbsID, String bbsTitle, String bbsContent) {

        // 특정한 아이디에 해당하는 내용과 제목을 바꿔주겠다는 것임
        String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, bbsTitle);
            pstmt.setString(2, bbsContent);
            pstmt.setInt(3, bbsID);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    // 게시글 삭제하는 delete함수
    public int delete(int bbsID) {
        // 글을 삭제하더라도 내용이 남아 있을 수 있게 bbsAvailable= 0으로만 바꿔도 delete 효과를 낼 수 있다.
        String SQL = "UPDATE BBS SET bbsAvailable = 0  WHERE bbsID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, bbsID);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

}
