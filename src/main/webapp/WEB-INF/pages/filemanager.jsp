<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>File Manager</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/filemanager.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filemanager.js"></script>

</head>
<body>
<table id="fileItems" style="width:100%;">
    <c:forEach var="item"
               items="${items}">
        <tr class="open" id="${item.path}">
            <td><span class="toggle open"></span>${item.name}</td>
            <td>${item.extension}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>