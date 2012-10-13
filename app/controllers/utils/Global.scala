package controllers.utils

object Global {
  val emptyFillString = "$EMPTY$"

  def replaceEmptyFillString(str: String): String = {
    if (str == emptyFillString)
      ""
    else
      str
  }
}
