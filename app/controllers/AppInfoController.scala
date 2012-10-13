package controllers

import play.api.mvc._
import play.api.Logger
import play.api.libs.json._
import utils.Global

object AppInfoController extends Controller {

  def version() = Action {
    Ok(scalaxb_executor_client.BuildInfo.version)
  }
}