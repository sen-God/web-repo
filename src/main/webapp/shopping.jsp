<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物界面</title>
    <link href="shopping.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<img id="background" alt="背景"/>
<img id="itembackground" alt="物品背景"/>
<a href=""> <button id="gotocart" name="gotocart">购物车</button></a>
<form action="gencookies" method="post">
    <div id="bicycle"><a href=""><img src="购物图片/自行车.jpg" width="300" height="300"  alt="自行车"/></a><br></div>
    <div id="button1"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="bicycle">
</form>
<form action="gencookies" method="post">
    <div id="wukong"><a href=""><img src="购物图片/西游记.jpg" width="300" height="300" alt="西游记"/></a></div>
    <div id="button2"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="wukong">
</form>
<form action="gencookies" method="post">
    <div id="pc"><a href=""><img src="购物图片/电脑.jpg" width="300" height="300" alt="电脑"/></a></div>
    <div id="button3"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="pc">
</form>
<form action="gencookies" method="post">
    <div id="phone"><a href=""><img src="购物图片/手机.jpg" width="300" height="300" alt="手机"/></a></div>
    <div id="button4"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="phone">
</form>
<form action="gencookies" method="post">
    <div id="wine"><a href=""><img src="购物图片/红酒.jpg" width="300" height="300" alt="红酒"/></a></div>
    <div id="button5"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="wine">
</form>
<form action="gencookies" method="post">
    <div id="spirit"><a href=""><img src="购物图片/烧酒.jpg" width="300" height="300" alt="烧酒"/></a></div>
    <div id="button6"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="spirit">
</form>
<form action="gencookies" method="post">
    <div id="facewash"><a href=""><img src="购物图片/洗面奶.jpg" width="300" height="300" alt="洗面奶"/></a></div>
    <div id="button7"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="facewash">
</form>
<form action="gencookies" method="post">
    <div id="bath_cream"><a href=""><img src="购物图片/沐浴露.jpg" width="300" height="300" alt="沐浴露"/></a></div>
    <div id="button8"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="bath_cream">
</form>
<form action="gencookies" method="post">
    <div id="sanguo"><a href=""><img src="购物图片/三国演义.jpg" width="300" height="300" alt="三国演义"/></a></div>
    <div id="button9"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="sanguo">
</form>
<form action="gencookies" method="post">
    <div id="shoes"><a href=""><img src="购物图片/跑鞋.jpg" width="300" height="300" alt="跑鞋"/></a></div>
    <div id="button10"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <input type="hidden" name="item" value="shoes">
</form>
<a href="deletecookies"> <button>删掉所有cookie</button></a>

</body>
</html>
