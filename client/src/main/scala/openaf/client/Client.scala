package openaf.client

import org.scalajs.dom.html

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("openaf.client.Client")
object Client {

  @JSExport
  def main(target: html.Div): Unit = {
    target.textContent = "there"
  }
}
