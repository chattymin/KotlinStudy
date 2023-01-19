package Classes.Spice
/*
Practice Time
You used object in the previous lesson and quiz.

And now that you know about enums, here's the code for Color as an enum:

enum class Color(val rgb: Int) {
   RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}
In SpiceColor, change the type of color from String to the Color class, and set the appropriate color in YellowSpiceColor.

Hint: The color code for yellow is YELLOW(0xFFFF00)
Make Spice a sealed class.

What is the effect of doing this?
Why is this useful?
 */

enum class Color(val rgb: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF), YELLOW(0xFFFF00);
}

interface SpiceColor {
    val color: Color
}

object YellowSpiceColor : SpiceColor {
    override val color = Color.YELLOW
}

sealed class Spice1{
    class Curry(var name: String, var spciness: String) : Spice1()
    class Pepper(var name: String, var spciness: String) : Spice1()
    class Salt(var name: String, var spciness: String) : Spice1()
    class RedCurry(var name: String, var spciness: String) : Spice1()
}
// What is the effect of doing this? : sealed는 일종의 추상 클래스이다. 그렇기 때문에 세부 값이 일부 조정되더라도 새로 만들어 줄 필요 없이
// 해당 부분을 수정해서 선언하기만 하면 된다.
// Why is this useful? : sealed 클래스의 서브 클래스 각각에 대해 여러 개의 인스턴스 생성이 가능하다.
// 그렇기 때문에 상태값을 유동적으로 변경할 수 있다.