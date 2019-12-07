<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/7
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- enctype：控制表单类型 -->
<form method="post" action="upload" enctype="multipart/form-data">
    文件上传<input type="file" name="file" />
    <input type="submit" />
</form>

</body>
</html>
