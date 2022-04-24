<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List my Rooms</title>
    <script src="actiuni.js" type="text/javascript" ></script>
    <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
</head>
<body>

<%

    HttpSession s = request.getSession(); // citesc sesiunea curenta
    Object o = s.getAttribute("id"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii
    Object email = s.getAttribute("email");
    if(o==null)
    {
        response.sendRedirect("login.html"); // il trimit la login, nici nu se executa ce e mai jos
    }
%>

Welcome <b><%=email%></b> !
</p>
<br> Grand Hotel is situated in the Old City Center of Bucharest and offers 10 rooms with King size beds.</p>
    Please fill in the below fields whether you prefer DOUBLE or SINGLE room, the check-in date and check-out date.</br>
    Kindly be informed that by pressing the "New Request" button, your request is registered in our system and
    one of our dedicated sales agents will contact you.</p>
    The room availability and price and total amount is communicated by the sales agent.</br>
    <br> <i> Our members benefit of discounts on different occasions and special tariffs!</i> </br>
    <br> <i> English Breakfast is included in the room price. </i> </br>

</p>

</p>

<%--<input type="text" placeholder="Search" onkeyup="search(this.value)">
</p>
<%--<input type="button" id="delete" value="Delete all" onClick="deleteAll()" /> NU AM NEVOIE DE BUTON DE DELETE--%>

<script>
    loadToDo();
</script>

</p>
<input type="text" id="roomType" placeholder="tip camera" />

<input type="text" id="dateIn" placeholder="date in" />

<input type="text" id="dateOut" placeholder="date out" />


<input type="button" id="add" value="Add Request" onClick="newToDo()" />

</p>
<p><strong> Below you can view all registered requests: </strong></p>

<div id="listOfToDo">
    <table border="1">
        <thead>
        <tr>
            <th>RoomType</th>
            <th>Date IN</th>
            <th>Date OUT</th>
            <th>Price per night </th>
        </tr>
        </thead>
        <tbody id="obiect">

        </tbody>


    </table>
</div>
</p>
<br> <i> All the offer requests from above are now registered!</i> </br>
<br> <i> Please note that these are not confirmed reservations, you will be contacted by us for your final reservation! </i>
</p>
<a href ="logout.jsp">Logout</a>

</body>
</html>