
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login</title>
</head>
<body>
	<form action="<c:url value="/signin/facebook" />" method="POST">
		<button type="submit">Login in with Facebook</button>
		<input type="hidden" name="scope"
			value="email,publish_stream,offline_access" />
	</form>

	<form action="<c:url value="/signin/google" />" method="POST">
		<button type="submit">Login in with Google</button>
		<input type="hidden" name="scope"
			value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email" />
	</form>


	<form action="<c:url value="/signin/github" />" method="POST">
		<button type="submit">Login in with github</button>
	</form>

</body>
</html>
