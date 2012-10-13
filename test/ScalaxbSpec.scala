package com.nem

import org.specs2.Specification
import org.specs2.mock.Mockito
import scalaxb.SoapClients
import scalaxb._
import com.nem.web.client.scalaxb.EchoExecutorSoap
import com.nem.web.client.scalaxb._


class ScalaxbSpec extends Specification with Mockito {

  def is =
    "This spec verifies 'scalaxb' " ^
      p ^
      "'scalaxb' should " ^
      "Connect to remote asmx" ! test1 ^
      end

  def test1 = {
    val client = EchoExecutorClient()
    val response = client.proxy.execute(Some(EchoMsg(Some("Hi"))))
    response.isRight match {
      case true =>
        response.right.exists((resp:ExecuteResponse) =>{
          resp.executeResult match{
            case Some(msg) =>
              msg == "Hi from Server!"
            case None => false
          }
        })
      case false => false
    }
  }
}