package openaf.server.html

import scalatags.Text.all._

object IndexPage {
  val page: String = {
    val start = "openaf.client.Client.main(document.getElementById('target'))"
    "<!DOCTYPE html>" + html(
      head(
        meta(charset := "UTF-8"),
        title := "OpenAF",
        script(`type` := "text/javascript", src := "/client-fastopt.js")
      ),
      body(
        onload := start,
        div("Hello"),
        div(id := "target")
      )
    ).render
  }
}
