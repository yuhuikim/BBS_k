<!-- 실제로 글쓰기를 눌러서 만들어주는 Action페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO"%>
<%@ page import="bbs.Bbs"%>
<%@ page import="notice.Notice"%>
<%@ page import="notice.NoticeDAO"%>
<%@ page import="java.io.PrintWriter"%>
<!-- 자바스크립트 문장사용 -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- 건너오는 모든 파일을 UTF-8로 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 사이트</title>
</head>
<body>
	<%
		String userID = null;
		// 로그인 된 사람은 회원가입페이지에 들어갈수 없다
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}

		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		}
		int noticeID = 0;
		if (request.getParameter("noticeID") != null) {
		    noticeID = Integer.parseInt(request.getParameter("noticeID"));
		}
		if (noticeID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다')");
			script.println("location.href = 'notice.jsp'");
			script.println("</script>");
		}
		Notice notice = new NoticeDAO().getNotice(noticeID);
		if (!userID.equals(notice.getUserID())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다')");
			script.println("location.href = 'notice.jsp'");
			script.println("</script>");

		} else {
		    NoticeDAO noticeDAO = new NoticeDAO();
			int result = noticeDAO.delete(noticeID);
			if (result == -1) { // 글 삭제에 실패했을 경우
				PrintWriter script = response.getWriter(); //하나의 스크립트 문장을 넣을 수 있도록.
				script.println("<script>");
				script.println("alert('글 삭제에 실패했습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else { // 글 삭제에 성공했을 경우
				PrintWriter script = response.getWriter();
				script.println("<script>");
				//성공적으로 수행한 경우 다시 게시판 메인 화면으로 돌아갈 수 있도록 사용자를 이동시켜 준다.
				script.println("location.href= 'notice.jsp'");
				script.println("</script>");
			}
		}
	%>
</body>
</html>
