package com.nem

import org.specs2.Specification
import org.specs2.mock.Mockito


class JsonSpec extends Specification with Mockito {

  def is =
    "This spec verifies 'scalaxb' " ^
      p ^
      "'scalaxb' should " ^
      "Connect to remote asmx" ! test1 ^
      end

  def test1 = {
    true
  }
}