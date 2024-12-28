<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物界面</title>
    <link href="shopping.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<img id="backbackground">
<img id="background"/>
<div id="welcome"><h1>欢迎购物 &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;Welcome to the shop</h1></div>
<img id="itembackground"/>
<a href="link1横幅广告链接" ><img id="ad_banner" src="https://tse3-mm.cn.bing.net/th/id/OIP-C.8tlwbGsJvlNZwGAGL36W8AHaFS?w=248&h=180&c=7&r=0&o=5&dpr=2.3&pid=1.7" alt="横幅广告"></a>
<a href="link2左侧广告链接" ><img id="ad_left_fixed" src="https://tse3-mm.cn.bing.net/th/id/OIP-C.8tlwbGsJvlNZwGAGL36W8AHaFS?w=248&h=180&c=7&r=0&o=5&dpr=2.3&pid=1.7" alt="横幅广告"></a>
<a href="link3右下角广告链接"><img id="ad_right_bottom_fixed" src="https://tse3-mm.cn.bing.net/th/id/OIP-C.8tlwbGsJvlNZwGAGL36W8AHaFS?w=248&h=180&c=7&r=0&o=5&dpr=2.3&pid=1.7" alt="横幅广告"></a>
<script>
    function x(){
        alert('结账完成，共消费${sessionScope.cart[0][1]*300+
        sessionScope.cart[1][1]*99+
        sessionScope.cart[2][1]*5000+
        sessionScope.cart[3][1]*3500+
        sessionScope.cart[4][1]*200+
        sessionScope.cart[5][1]*49+
        sessionScope.cart[6][1]*29+
        sessionScope.cart[7][1]*50+
        sessionScope.cart[8][1]*89+
        sessionScope.cart[9][1]*269}元')
    }
</script>
<a href="checkout"> <button id="checkout" onclick="x()">总价:${sessionScope.cart[0][1]*300+
        sessionScope.cart[1][1]*99+
        sessionScope.cart[2][1]*5000+
        sessionScope.cart[3][1]*3500+
        sessionScope.cart[4][1]*200+
        sessionScope.cart[5][1]*49+
        sessionScope.cart[6][1]*29+
        sessionScope.cart[7][1]*50+
        sessionScope.cart[8][1]*89+
        sessionScope.cart[9][1]*269}<br>点击结账</button></a>
<form action="gencookies" method="post">
    <div id="bicycle"><a href=""><img src="购物图片/自行车.jpg" width="300" height="300"  alt="自行车"/></a><br></div>
    <div id="button1"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_bicycle">公路自行车 500元</div>
    <div id="num_bicycle"><c:if test="${sessionScope.cart[0][1]!='0'}">购物车中已有${sessionScope.cart[0][1]}件</c:if></div>
    <div id="buttondelete1"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[0][1]=='0'}"> disabled </c:if>>
    </div>
    <input type="hidden" name="item" value="bicycle">
</form>
<form action="gencookies" method="post">
    <div id="wukong"><a href=""><img src="购物图片/西游记.jpg" width="300" height="300" alt="西游记"/></a></div>
    <div id="button2"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_wukong">西游记套书 99元</div>
    <div id="num_wukong"><c:if test="${sessionScope.cart[1][1]!='0'}">购物车中已有${sessionScope.cart[1][1]}件</c:if></div>
    <div id="buttondelete2"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[1][1]=='0'}"> disabled </c:if>>
    </div>
    <input type="hidden" name="item" value="wukong">
</form>
<form action="gencookies" method="post">
    <div id="pc"><a href=""><img src="购物图片/电脑.jpg" width="300" height="300" alt="电脑"/></a></div>
    <div id="button3"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_pc">笔记本电脑 5000元</div>
    <div id="num_pc"><c:if test="${sessionScope.cart[2][1]!='0'}">购物车中已有${sessionScope.cart[2][1]}件</c:if></div>
    <div id="buttondelete3"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[2][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="pc">
</form>
<form action="gencookies" method="post">
    <div id="phone"><a href=""><img src="购物图片/手机.jpg" width="300" height="300" alt="手机"/></a></div>
    <div id="button4"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_phone">手机 3499元</div>
    <div id="num_phone"><c:if test="${sessionScope.cart[3][1]!='0'}">购物车中已有${sessionScope.cart[3][1]}件</c:if></div>
    <div id="buttondelete4"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[3][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="phone">
</form>
<form action="gencookies" method="post">
    <div id="wine"><a href=""><img src="购物图片/红酒.jpg" width="300" height="300" alt="红酒"/></a></div>
    <div id="button5"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_wine">进口葡萄酒 200元</div>
    <div id="num_wine"><c:if test="${sessionScope.cart[4][1]!='0'}">购物车中已有${sessionScope.cart[4][1]}件</c:if></div>
    <div id="buttondelete5"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[4][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="wine">
</form>
<form action="gencookies" method="post">
    <div id="spirit"><a href=""><img src="购物图片/烧酒.jpg" width="300" height="300" alt="烧酒"/></a></div>
    <div id="button6"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_spirit">日本烧酒 49元</div>
    <div id="num_spirit"><c:if test="${sessionScope.cart[5][1]!='0'}">购物车中已有${sessionScope.cart[5][1]}件</c:if></div>
    <div id="buttondelete6"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[5][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="spirit">
</form>
<form action="gencookies" method="post">
    <div id="facewash"><a href=""><img src="购物图片/洗面奶.jpg" width="300" height="300" alt="洗面奶"/></a></div>
    <div id="button7"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_facewash">洗面奶 29元</div>
    <div id="num_facewash"><c:if test="${sessionScope.cart[6][1]!='0'}">购物车中已有${sessionScope.cart[6][1]}件</c:if></div>
    <div id="buttondelete7"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[6][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="facewash">
</form>
<form action="gencookies" method="post">
    <div id="bath_cream"><a href=""><img src="购物图片/沐浴露.jpg" width="300" height="300" alt="沐浴露"/></a></div>
    <div id="button8"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_bath_cream">沐浴露 50元</div>
    <div id="num_bath_cream"><c:if test="${sessionScope.cart[7][1]!='0'}">购物车中已有${sessionScope.cart[7][1]}件</c:if></div>
    <div id="buttondelete8"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[7][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="bath_cream">
</form>
<form action="gencookies" method="post">
    <div id="sanguo"><a href=""><img src="购物图片/三国演义.jpg" width="300" height="300" alt="三国演义"/></a></div>
    <div id="button9"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_sanguo">三国演义套书 89元</div>
    <div id="num_sanguo"><c:if test="${sessionScope.cart[8][1]!='0'}">购物车中已有${sessionScope.cart[8][1]}件</c:if></div>
    <div id="buttondelete9"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[8][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="sanguo">
</form>
<form action="gencookies" method="post">
    <div id="shoes"><a href=""><img src="购物图片/跑鞋.jpg" width="300" height="300" alt="跑鞋"/></a></div>
    <div id="button10"><input type="submit" value="加入购物车" name="add_to_cart"></div>
    <div id="price_shoes">专业运动跑鞋 269元</div>
    <div id="num_shoes"><c:if test="${sessionScope.cart[9][1]!='0'}">购物车中已有${sessionScope.cart[9][1]}件</c:if></div>
    <div id="buttondelete10"><input type="submit" value="移除购物车"  name="delete_from_cart"
    <c:if test="${sessionScope.cart[9][1]=='0'}"> disabled </c:if>></div>
    <input type="hidden" name="item" value="shoes">
</form>
<div id="deletecookies"><a href="deletecookies"> <button>删掉所有cookie(测试用)</button></a></div>

</body>
</html>
