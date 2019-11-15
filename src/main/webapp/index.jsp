<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 10/12/2019
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="View.TableView" %>
<%@ page import="weka.core.Instances" %>
<%@ page import="java.text.DecimalFormat" %>

<html>
<body>
<% TableView tableView = new TableView(); %>

<table width="90%" border="1"
       align="center"
       cellpadding="2">

    <thead>
    <tr>
        <td> -
        </td>
        <% for (int i = 0; i < 48; i++) { %>
        <td BGCOLOR=<%="E3228A"%>><%= i/4+7%>:<%=(i%4)*15 %>
        </td>
        <%}%>
    </tr>
    </thead>
    <tbody>
    <%  tableView.getTimeData();
        for (int i = 0; i < 5; i++) { %>
    <tr>
        <% DecimalFormat decimalFormat = new DecimalFormat("#.0");
            double value = 0;
            try {
                value = Double.valueOf(decimalFormat.format(100 * tableView.getDayData().instance(i).value(tableView.getDayData().numAttributes() - 1)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String bgColor;
            if (value > 80.0) bgColor = "1b6eb5";
            else if (value > 60.0) bgColor = "228ae3";
            else if (value > 40.0) bgColor = "3895e5";
            else if (value > 20.0) bgColor = "64adeb";
            else bgColor = "90c4f1";
        %>
        <td BGCOLOR=<%=bgColor%>>
            <% if (i != 0) {%><%=i%><%}%>shanbe
            <%=value%>
        </td>
        <% for (int j = 9; j < 57; j++) {
            double rate = 0;
            try {
                rate = Double.valueOf(decimalFormat.format(100 * tableView.getTimeData().instance(i).value(j)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String color;
            if (rate > 90.0) color = "609e17";
            else if (rate > 80.0) color = "7ccc1e";
            else if (rate > 70.0) color = "8ae322";
            else if (rate > 60.0) color = "a1e84e";
            else if (rate > 50.0) color = "b8ee7a";

            else if (rate > 40.0) color = "f3908a";
            else if (rate > 30.0) color = "ef6a63";
            else if (rate > 20.0) color = "ec463d";
            else if (rate > 10.0) color = "d43f36";
            else color = "a5312a";
        %>
        <td BGCOLOR=<%=color%>>
            <%=rate%>%
        </td>
        <%}%>
    </tr>
    <%}%>
    </tbody>
</table>
<br>

<%--<% String[] tree = tableView.getTimeClassifierModel().getTree().toString().split("[\n]");--%>
<%--for (int i = 0; i < tree.length; i++) { %>--%>
<%--<br>--%>
<%--<%=tree[i]%>--%>
<%--<%}%>--%>
<%--<br>k value :--%>
<%--<%=tableView.getRandomTreeTimeClassifier().getTree().getKValue()%>--%>
</body>
</html>
