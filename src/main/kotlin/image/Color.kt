package image

enum class Color(val code: Int) {
    BLACK(0), WHITE(1), TRANSPARENT(2);

    fun match(pixelValue: Int?): Boolean {
        return code == pixelValue
    }

    fun fromCode(code: Int): Color? {
        return values().find { it.code == code }
    }

    override fun toString(): String {
        return when (this) {
            BLACK -> "."
            WHITE -> "#"
            TRANSPARENT -> " "
        }
    }
}