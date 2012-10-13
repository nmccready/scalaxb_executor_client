package com.nem.web.client.scalaxb


import scalaxb.{DispatchHttpClients, SoapClients}

case class EchoExecutorClient(){
  val proxy =
    ( new EchoExecutorSoap12Bindings with
      scalaxb.SoapClients with
      scalaxb.DispatchHttpClients {}).service
}
