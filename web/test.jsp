<%--
  Created by IntelliJ IDEA.
  User: shtil
  Date: 16.06.14
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/TestDB">
    select id, name from department
</sql:query>

<html>
<head>
    <title>DB Test</title>
</head>
<body>

<h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
    id ${row.id}<br/>
    name ${row.name}<br/>
</c:forEach>

</body>
</html>