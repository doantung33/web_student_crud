<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2/27/2021
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
    <style>
        body {
            color: #566787;
            background: #f5f5f5;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
        }

        /* Modal styles */
        .modal .modal-dialog {
            max-width: 400px;
        }
        .modal .modal-header, .modal .modal-body, .modal .modal-footer {
            padding: 20px 30px;
        }
        .modal .modal-content {
            border-radius: 3px;
            font-size: 14px;
        }
        .modal .modal-footer {
            background: #ecf0f1;
            border-radius: 0 0 3px 3px;
        }
        .modal .modal-title {
            display: inline-block;
        }
        .modal .form-control {
            border-radius: 2px;
            box-shadow: none;
            border-color: #dddddd;
        }
        .modal textarea.form-control {
            resize: vertical;
        }
        .modal .btn {
            border-radius: 2px;
            min-width: 100px;
        }
        .modal form label {
            font-weight: normal;
        }
    </style>
</head>
<body>
<a href="/students">List Student</a>
<form method="post">
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td>Name</td>--%>
<%--            <td><input type="text" name="name" value="${s.getName()}"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Age</td>--%>
<%--            <td><input type="text" name="age" value="${s.getAge()}"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Address</td>--%>
<%--            <td><input type="text" name="address" value="${s.getAddress()}"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><input type="submit" value="Edit"></td>--%>
<%--        </tr>--%>
    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Employee</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" name="name" value="${s.getName()}" required>
                        </div>
                        <div class="form-group">
                            <label>Age</label>
                            <input type="text" class="form-control" name="age" value="${s.getAge()}" required>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <input type="text" class="form-control" name="address" value="${s.getAddress()}" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>

    </table>
</form>

</body>
</html>
