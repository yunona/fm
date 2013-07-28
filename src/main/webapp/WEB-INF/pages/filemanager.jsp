<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>File Manager</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-filetype.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/filemanager.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filemanager.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#fileItems').fileManager({rootDirectory:''});
        });</script>

</head>

<body>
<div id="fileItems" style="width:100%;">

</div>

</body>
</html>